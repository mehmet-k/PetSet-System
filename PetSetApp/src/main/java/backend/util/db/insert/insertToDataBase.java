package backend.util.db.insert;

import org.hibernate.Session;
import org.hibernate.Transaction;

import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;

public class insertToDataBase {
	
	public static void insertUser (User user) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
		}
	}
	
}
