package backend.util.db.repositories;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;
import jakarta.persistence.NoResultException;

public class userRepository {

	
	public static boolean isUserExists(String usermame) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			String nativeSQL = "SELECT u FROM User u WHERE u.userName = :username";
			User user =	(User)session.createQuery(nativeSQL,User.class)
						.setParameter("username", usermame)
						.getSingleResult();
		    return true;
		} 
		catch (NoResultException e) {
		    // Handle case where no result is found
		    return false;
		} 
	}
 
	public static boolean areCredientialsCorrect(String username, String Password) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			String nativeSQL = "SELECT u FROM User u WHERE u.userName = :username AND u.password=:Password";
			User user =	(User)session.createQuery(nativeSQL,User.class)
							.setParameter("username", username)
							.setParameter("Password", Password)
							.getSingleResult();
			return true;
		} 
		catch (NoResultException e) {
		    // Handle case where no result is found
			e.printStackTrace();
		    return false;
		} 
	}

	public static boolean isAdmin(User user) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			String nativeSQL = "SELECT a FROM Admin WHERE a.id = :ID";
			User admin = (User)session.createQuery(nativeSQL,User.class)
							.setParameter("ID", user.getId())
							.getSingleResult();
			return true;
		} 
		catch (NoResultException e) {
		    // Handle case where no result is found
		    return false;
		} 
	}
	
	public static void insertUser (User user) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
            session.close();
		}
	}
	
	public static void removeUser(User user) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
			session.createQuery("UPDATE USER SET status = 0 WHERE id=:userID", User.class)
								.setParameter("userID", user.getId());
			tx.commit();
			session.close();
		}
	}
	
	public static User getUserByUserID(int id){
		
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
			User user = session.get(User.class, id);
			tx.commit();
			return user;
		}
	}

	
	public static void hardDeleteUser(User user) {
		
	}

		
	public static List<User> getAllUsers(){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT u FROM Users u WHERE u.status = 1";
			
	        List<User> users =(List<User>)session.createQuery(nativeSQL,User.class)
			                .getResultList();

	        tx.commit();
	        return users;
		}
	}
	

}
