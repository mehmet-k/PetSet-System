package backend.util.db.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import backend.models.Items;
import backend.util.db.hibernate.HibernateUtility;

public class itemsRepository {
	
	public static void insertItem (Items item) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
            session.persist(item);
            tx.commit();
            session.close();
		}
	}
	
	public static void removeItem(Item item) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
			session.createQuery("UPDATE USER SET status = 0", Item.class);
			tx.commit();
			session.close();
		}
	}
	
	
	public static void hardDeleteItem() {
		
	}
	
}
