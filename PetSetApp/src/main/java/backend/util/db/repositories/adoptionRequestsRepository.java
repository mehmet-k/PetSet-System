package backend.util.db.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import backend.models.Pet;
import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;


public class adoptionRequestsRepository {
	
	public static void addUserToAdoptionRequest(User applicant,Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
	        String nativeSQL = "SELECT insert_into_adoption_requests(:userID,:petID)";
	        session.createQuery(nativeSQL)
	                .setParameter("userID", applicant.getId())
	                .setParameter("petID", pet.getId())
	                .uniqueResult();
	        
	        tx.commit();
	        session.close();
		}
	}
	
	public static void removeUserFromAdoptionRequest(User applicant,Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
	        String nativeSQL = "UPDATE adoptionRequest SET status=0 WHERE userID=:userID AND petID=:petID";
	        session.createNativeQuery(nativeSQL)
	                .setParameter("userID", applicant.getId())
	                .setParameter("petID", pet.getId())
	                .executeUpdate();
	        
	        tx.commit();
	        session.close();
		}
	}
	
	public static List<User> getAdoptionRequests(Pet pet){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT u FROM User u, Pet p, adoptionRequest ar "
					+"WHERE ar.petID = :petID AND u.id = ar.applicantID AND ar.status = 1";
			
	        List<User> users =(List<User>)session.createQuery(nativeSQL,User.class)
			                .setParameter("petID", pet.getId())
			                .getResultList();
	        tx.commit();
	        return users;
		}
	}
		
	public static List<Pet> getAdoptionRequestsOfAnUser(User user){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT p FROM Pet p, adoptionRequest ar WHERE p.id = ar.petID AND "
					+ "ar.applicantID =: userid AND ar.status = 1";
			
	        List<Pet> pets =(List<Pet>)session.createQuery(nativeSQL,Pet.class)
	        				.setParameter("userid", user.getId())
			                .getResultList();
	        tx.commit();
	        return pets;
		}
	}
	
	public static int getCountOfPetAdoptionRequestsByPet(Pet pet){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT count(*) "
					+ "FROM userHasThisPet uhtp, Pet p "
					+ "WHERE uhtp.petID = :petid ";
			
	        Integer i = (Integer)session.createQuery(nativeSQL,Integer.class)
	        					.setParameter("petid", pet.getId())
	        					.getSingleResult();
	        tx.commit();
	        return i;
		}
	}
	
	public static void hardDeleteinaciveAdoptionRequests() {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "DELETE FROM adoption_requests WHERE status = 0";
			
	        session.createNativeQuery(nativeSQL).executeUpdate();
	        
	        tx.commit();
	        session.close();
		}
	}
	
}
