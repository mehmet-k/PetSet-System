package backend.util.db.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import backend.models.Pet;
import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;
import backend.util.getModels.getUser;

public class adoptionRequestsRepository {
	
	public static void addUserToAdoptionRequest(User owner,User applicant,Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
	        String nativeSQL = "SELECT insert_into_adoption_requests(ownerID,:userID,:petID)";
	        session.createQuery(nativeSQL)
	                .setParameter("userID", applicant.getId())
	                .setParameter("petID", pet.getId())
	                .setParameter("ownerID",owner.getId());

	        tx.commit();
	        session.close();
		}
	}
	
	public static void removeUserFromAdoptionRequest(User applicant,Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
	        String nativeSQL = "UPDATE adoptionRequest SET status=0 WHERE userid=:userID AND petid=:petID";
	        session.createQuery(nativeSQL)
	                .setParameter("userID", applicant.getId())
	                .setParameter("petID", pet.getId());
	        
	        tx.commit();
	        session.close();
		}
	}
	
	public static List<User> getAdoptionRequests(Pet pet){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT u FROM User u, Pet p, adoptionRequest ar"
					+"WHERE p.id = ar.petid AND u.id = ar.applicantid";
			
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
	        
			String nativeSQL = "SELECT p FROM Pet p, adoptionRequest ar WHERE p.id = ar.petid AND ar.userID =: userid";
			
	        List<Pet> pets =(List<Pet>)session.createQuery(nativeSQL,Pet.class)
	        				.setParameter("userid", user.getId())
			                .getResultList();
	        tx.commit();
	        return pets;
		}
	}
	
}
