package backend.util.db.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import backend.models.Items;
import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;

public class userPurchaseRepository {
	
	public static void addItemToUser(User user,Items item) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
	        String nativeSQL = "SELECT insert_into_user_purchases(:userID,:itemID)";
	        session.createQuery(nativeSQL)
	                .setParameter("userID", user.getId())
	                .setParameter("itemID", item.getId())
	                .uniqueResult();

	        tx.commit();
	        session.close();
			
		}
	}
	
	public static List<Items> getUserItems(User user){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT p " +
	                "FROM User u,userPurchase up, Items i " +
	                "WHERE u.id = up.userID AND u.id = :userID AND i.id = up.itemID AND up.status = 1";
	        
	        List<Items> items =(List<Items>)session.createQuery(nativeSQL,Items.class)
			                .setParameter("userID", user.getId())
			                .getResultList();

	        tx.commit();
	        return items;
		}
	}
	
	public static void hardDeleteInactivePurchases() {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "DELETE FROM userPurchase WHERE status = 0";
			
	        session.createQuery(nativeSQL,Integer.class);
	        
	        tx.commit();
	        session.close();
		}
	}
}
