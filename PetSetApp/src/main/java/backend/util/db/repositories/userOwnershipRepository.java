package backend.util.db.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import backend.models.Pet;
import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;

public class userOwnershipRepository {
	
	
	public static void addPetToUser(User user,Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
	        String nativeSQL = "SELECT insert_into_user_has_this_pet(:userID,:petID)";
	        session.createQuery(nativeSQL)
	                .setParameter("userID", user.getId())
	                .setParameter("petID", pet.getId())
	                .uniqueResult();
	
	        tx.commit();
			session.close();
		}
	}
	
	public static void removePetFromUser(User user,Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "UPDATE userHasThisPet SET status=0 WHERE userID=:userid AND petID=:petid";
	        	session.createNativeQuery(nativeSQL)
	        			.setParameter("userid", user.getId())
	        			.setParameter("petid", pet.getId())
	        			.executeUpdate();
	        
	        petRepository.setPetAsNotAdopted(pet);
	        
	        tx.commit();
	        session.close();
		}
	}
	
	public static List<Pet> getUserPets(User user){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT p " +
	                "FROM User u,userHasThisPet up, Pet p " +
	                "WHERE u.id = up.userID AND u.id = :userID AND p.id = up.petID AND up.status = 1 "
	                + "AND up.isAdopted = 0";
	        
	        List<Pet> pets =(List<Pet>)session.createQuery(nativeSQL,Pet.class)
			                .setParameter("userID", user.getId())
			                .getResultList();
	
	        tx.commit();
	        return pets;
		}
	}
	
	public static List<Pet> getUserAdoptedPets(User user){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT p " +
	                "FROM User u,userHasThisPet up, Pet p " +
	                "WHERE u.id = up.userID AND u.id = :userID AND p.id = up.petID AND up.status = 1 "
	                + "AND up.isAdopted = 1";
	        
	        List<Pet> pets =(List<Pet>)session.createQuery(nativeSQL,Pet.class)
			                .setParameter("userID", user.getId())
			                .getResultList();
	
	        tx.commit();
	        return pets;
		}
	}
	

	public static void setPetOwner(Pet pet, User newOwner) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "UPDATE user_Has_This_Pet SET userID=:newOwnerID WHERE petID=:petID ";
			
	        session.createNativeQuery(nativeSQL)
			                .setParameter("newOwnerID", newOwner.getId())
			                .setParameter("petID", pet.getId())
			                .executeUpdate();
	        
	        tx.commit();
	        session.close();
		}
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        String nativeSQL = "UPDATE Pet SET isAdopted = 1 WHERE id=:petid";
	        session.createNativeQuery(nativeSQL)
	        			.setParameter("petid", pet.getId())
	        			.executeUpdate();
	        tx.commit();
	        session.close();
		} 
	}
	
	public static void hardDeleteInactiveOwnerships() {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "DELETE FROM user_has_this_pet WHERE status = 0";
			
	        session.createNativeQuery(nativeSQL).executeUpdate();
	        
	        tx.commit();
	        session.close();
		}
	}
	
	public static User getOwner(Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT u FROM userHasThisPet uhtp, User u "
					+ "WHERE u.id = uhtp.userID AND uhtp.petID = :petid";
			
			User user = session.createQuery(nativeSQL,User.class)
						.setParameter("petid", pet.getId())		
						.getSingleResult();
	        
	        tx.commit();
	        return user;
		}
	}
	
}
