package backend.util.db.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import backend.models.Items;
import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;
import jakarta.persistence.NoResultException;

public class itemsRepository {
	
	public static boolean isItemExists(String itemname) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			String nativeSQL = "SELECT i FROM Items i WHERE i.itemName = :itemname";
			Items items =	(Items)session.createQuery(nativeSQL,Items.class)
						.setParameter("itemname", itemname)
						.getSingleResult();
		    return true;
		} 
		catch (NoResultException e) {
		    // Handle case where no result is found
		    return false;
		} 
	}
	public static void insertItem (Items item) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
            session.persist(item);
            tx.commit();
            session.close();
		}
	}
	
	public static void removeItem(Items item) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
			session.createNativeQuery("UPDATE Items SET status = 0 WHERE id = :itemID")
						.setParameter("itemID",item.getId())
						.executeUpdate();
			tx.commit();
			session.close();
		}
	}
		
	public static void hardDeleteInactiveItems() {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "DELETE FROM Items WHERE status = 0";
			
	        session.createNativeQuery(nativeSQL).executeUpdate();
	        
	        tx.commit();
	        session.close();
		}
	}
	
	public static List<Items> getAllItems() {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT i FROM Items i WHERE i.status = 1";
			
	        List<Items> items =(List<Items>)session.createQuery(nativeSQL,Items.class)
			                .getResultList();

	        tx.commit();
	        return items;
		}
	}
	
	
	
}
