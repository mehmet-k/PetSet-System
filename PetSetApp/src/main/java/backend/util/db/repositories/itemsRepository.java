package backend.util.db.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import backend.models.ItemType;
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
	
	public static List<Integer> getCountOfItemsByPrice(int lowerBound, int upperBound){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT i.price , count(*) itemCount FROM Items i "
					+ "GROUP BY i.price HAVING i.price > :lowerbound AND i.price < :upperbound";
			
	        List<Integer> priceCount =(List<Integer>)session.createQuery(nativeSQL,Integer.class)
	        				.setParameter("lowerbound", lowerBound)
	        				.setParameter("upperbound", upperBound)
			                .getResultList();

	        tx.commit();
	        return priceCount;
		}
	}
	
	public static List<Items> getItemsByItemsPrice(int lowerBound,int upperBound){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT i FROM Items i "
					+ "WHERE i.price > :lowerbound AND i.price < :upperbound "
					+ "AND i.status = 1";
			
	        List<Items> items =(List<Items>)session.createQuery(nativeSQL,Items.class)
	        				.setParameter("lowerbound", lowerBound)
	        				.setParameter("upperbound", upperBound)
			                .getResultList();

	        tx.commit();
	        return items;
		}
	}
	
	public static List<Items> getItemsByItemType(ItemType itemType){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT i FROM Items i WHERE i.itemType =:itemtype AND i.status = 1";
			
	        List<Items> items =(List<Items>)session.createQuery(nativeSQL,Items.class)
	        				.setParameter("itemtype", itemType.getItemType())
			                .getResultList();

	        tx.commit();
	        return items;
		}
	}
	
	public static List<Items> getItemsByPriceAndItemType(ItemType itemType, Integer lowerBound, 
			Integer upperBound){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT i FROM Items i "
					+ "WHERE i.price > :lowerbound AND i.price < :upperbound "
					+ "AND i.itemType = :itemtype";

			
	        List<Items> items =(List<Items>)session.createQuery(nativeSQL,Items.class)
	        				.setParameter("lowerbound", lowerBound)
	        				.setParameter("upperbound", upperBound)
	        				.setParameter("itemtype", itemType.getItemType())
			                .getResultList();

	        tx.commit();
	        return items;
		}
	}
	
	public static Items getItemByID(int id) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT i FROM Items i WHERE i.id = :itemid";

	        Items item = session.createQuery(nativeSQL,Items.class)
	        				.setParameter("itemid", id)
	        				.getSingleResult();

	        tx.commit();
	        return item;
		}
	}
	
	
	
}
