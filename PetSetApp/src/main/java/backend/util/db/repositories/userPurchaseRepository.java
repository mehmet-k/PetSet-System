package backend.util.db.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import backend.models.Items;
import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;

public class userPurchaseRepository {
	
	public static void insertIntoUserOwnershipTable(User user,Items item) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
	        String nativeSQL = "SELECT insert_into_user_purchases(:userID,:itemID)";
	        session.createQuery(nativeSQL)
	                .setParameter("userID", user.getId())
	                .setParameter("itemID", item.getId())
	                .uniqueResult();

	        tx.commit();
			
		}
	}

}
