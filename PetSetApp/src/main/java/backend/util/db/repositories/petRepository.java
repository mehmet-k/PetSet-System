package backend.util.db.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;

import backend.models.Pet;
import backend.util.db.hibernate.HibernateUtility;

public class petRepository {
	
	public static void insertPet (Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
            session.persist(pet);
            tx.commit();
            session.close();
		}
	}
	
	
	public static void removePet(Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
			session.createQuery("UPDATE USER SET status = 0", Pet.class);
			tx.commit();
			session.close();
		}
	}
	
	
	public static void hardDeletePet() {
			
	}

}
