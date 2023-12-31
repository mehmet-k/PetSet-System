package backend.util.db.repositories;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import backend.models.Pet;
import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;


public class petOwnershipRepository {
	
	public petOwnershipRepository() {
		
	}
	
	public static void insertIntoUserOwnershipTable(User user,Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
	        String nativeSQL = "SELECT insert_into_user_has_this_pet(:userID,:petID)";
	        session.createQuery(nativeSQL)
	                .setParameter("userID", user.getId())
	                .setParameter("petID", pet.getId())
	                .uniqueResult();

	        tx.commit();
			
		}
	}
	
	public static List<Pet> getUserPets(User user){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT p " +
	                "FROM User u,userHasThisPet up, Pet p " +
	                "WHERE u.id = up.userID AND u.id = :userID AND p.id = up.petID";
	        
	        List<Pet> pets =(List<Pet>)session.createQuery(nativeSQL,Pet.class)
			                .setParameter("userID", user.getId())
			                .getResultList();

	        tx.commit();
	        return pets;
		}
	}
	
}
