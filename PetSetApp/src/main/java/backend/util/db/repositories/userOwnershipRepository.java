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
	
	public static void removePetFromUser(User user) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "UPDATE userHasThisPet SET status=0 WHERE userID=:userid";
	        Pet pet =	session.createQuery(nativeSQL,Pet.class)
	        			.setParameter("userid", user.getId()).getSingleResult();
	        
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
	                "WHERE u.id = up.userID AND u.id = :userID AND p.id = up.petID AND up.status = 1";
	        
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
	        
			String nativeSQL = "UPDATE userHasThisPet SET userID=:newOwnerID WHERE petID=:petID ";
			
	        session.createQuery(nativeSQL)
			                .setParameter("newOwnerID", newOwner.getId())
			                .setParameter("petID", pet.getId())
			                .uniqueResult();
	        pet.setIsAdopted(1);//SHOULD TRIGGER TO CLOSE AD OF THIS PET
	        session.save(pet);
	        tx.commit();
		} 
	}
	
	public static void hardDeleteInactiveOwnerships() {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "DELETE FROM userHasThisPet WHERE status = 0";
			
	        session.createQuery(nativeSQL,Integer.class);
	        
	        tx.commit();
	        session.close();
		}
	}
	
}
