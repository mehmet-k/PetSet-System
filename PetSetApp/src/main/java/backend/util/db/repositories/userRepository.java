package backend.util.db.repositories;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.sqm.mutation.internal.temptable.PersistentTableInsertStrategy;

import backend.models.Pet;
import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class userRepository {
	private static EntityManager entityManager;

	public userRepository() {
		super();
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
			session.createQuery("UPDATE USER SET status = 0", User.class);
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
	
	public static List<Pet> getUserPets(User user) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			
			String nativeSQL = "SELECT DISTINCT p " +
                    "FROM USER_HAS_THIS_PET up, USERS u, Pet p " +
                    "WHERE up.userid = :userID AND p.id = up.petid AND up.status = 1";

			
			List<Pet> pets = session.createQuery(nativeSQL,Pet.class)
								.setParameter("userID", user.getId())
								.getResultList();

			return pets;
		}
	}
	
	public static void addPetToUser(User user,Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			String nativeSQLString = "INSERT INTO USER_HAS_THIS_PET(userid,petid,adoptiondate)"
								+ "VALUES(:userid,:petid,:adoptiondate)";
			
			Query query = entityManager.createNativeQuery(nativeSQLString);
			query.setParameter("userid", user.getId());
			query.setParameter("petid", pet.getId());
			//query.setParameter("adoptiondate", date);
			
			
		}
	}
	
	
}
