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
	private User user;
	private EntityManager entityManager;
	
	public userRepository(User user) {
		super();
		this.user = user;
	}

	public void insertUser (User user) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
            session.close();
		}
	}
	
	public void removeUser(User user) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
			session.createQuery("UPDATE USER SET status = 0", User.class);
			tx.commit();
			session.close();
		}
	}
	
	public void hardDeleteUser() {
		
	}
	
	public List<Pet> getUserPets(User user) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			
			String nativeSQL = "SELECT p.*"
					+ "FROM USER_HAS_THIS_PET up,USERS u, PET"
					+ "WHERE up.userid = u.id AND p.id = petid";
			
			Query query = entityManager.createNativeQuery(nativeSQL,Pet.class);
			query.setParameter("userID", user.getId());
			
			List<Pet> pets = query.getResultList();
			return pets;
		}
	}
	
	public void addPetToUser(User user,Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
		
		}
	}
	
	
}
