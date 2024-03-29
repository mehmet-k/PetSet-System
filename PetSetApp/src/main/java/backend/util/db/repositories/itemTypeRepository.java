package backend.util.db.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import backend.models.ItemType;
import backend.models.Items;
import backend.models.Pet;
import backend.models.PetType;
import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;
import jakarta.persistence.NoResultException;

public class itemTypeRepository {
	
	public static boolean isItemTypeExists(String itemtype) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			String nativeSQL = "SELECT it FROM ItemType WHERE it.itemType = :itemtype";
			ItemType itemType =	(ItemType)session.createQuery(nativeSQL,ItemType.class)
								.setParameter("itemtype",itemtype)
								.getSingleResult();
		    return true;
		} 
		catch (NoResultException e) {
		    // Handle case where no result is found
		    return false;
		} 
	}
	
	public void insertItemType (ItemType itemType) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
            session.persist(itemType);
            tx.commit();
            session.close();
		}
	}
	
	public static ItemType getItemTypeByString(String itemTypeString) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
            
			String nativeSQLString = "SELECT it FROM ItemType it WHERE it.itemType= :itemtype";
			
			ItemType itemType =  session.createQuery(nativeSQLString,ItemType.class)
								.setParameter("itemtype", itemTypeString)
								.getSingleResult();
				
            tx.commit();
            return itemType;
		}
	}
	
	public void removeItemType(ItemType itemType) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
			session.createNativeQuery("UPDATE ItemType SET status = 0 WHERE id =:petTypeID")
								.setParameter("petTypeID", itemType.getId())
								.executeUpdate();
			tx.commit();
			session.close();
		}
	}
	
	public static List<ItemType> getAllItemTypes() {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT it FROM ItemType it WHERE it.status = 1";
			
	        List<ItemType> itemTypes =(List<ItemType>)session.createQuery(nativeSQL,ItemType.class)
			                .getResultList();

	        tx.commit();
	        return itemTypes;
		}
	}
	
	public static List<Items> getItemsByGivenItemType(ItemType itemType){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT i FROM Items i WHERE itemTypeID = :itemtypeid AND it.status = 1";
			
	        List<Items> items =(List<Items>)session.createQuery(nativeSQL,Items.class)
	        				.setParameter("itemtypeid",itemType.getId())
			                .getResultList();

	        tx.commit();
	        return items;
		}
	}
	
	
	public static void hardDeleteInactiveItemTypes() {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "DELETE FROM Item_Types WHERE status = 0";
			
	        session.createNativeQuery(nativeSQL).executeUpdate();
	        
	        tx.commit();
	        session.close();
		}
	}
}
