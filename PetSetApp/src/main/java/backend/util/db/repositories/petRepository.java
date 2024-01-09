package backend.util.db.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.UnknownSqlResultSetMappingException;

import backend.models.Items;
import backend.models.Pet;
import backend.models.PetType;
import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;
import jakarta.persistence.NoResultException;

public class petRepository {

	public petRepository() {
		
	}
	
	public static boolean isPetExists(int ID) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			String nativeSQL = "SELECT p FROM Pet p WHERE p.id = :ID";
			Pet pet =	(Pet)session.createQuery(nativeSQL,Pet.class)
						.setParameter("ID", ID)
						.getSingleResult();
		    return true;
		} 
		catch (NoResultException e) {
		    // Handle case where no result is found
		    return false;
		} 
	}
	
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
			session.createQuery("UPDATE PET SET status = 0 WHERE id=:petID", Pet.class).
						setParameter("petID", pet.getId());
			tx.commit();
			session.close();
		}
	}
	
	
	public static void hardDeletePet() {
			
	}
	
	public static List<Pet> getAllPets() {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT p FROM Pet p WHERE p.status = 1";
			
	        List<Pet> pets =(List<Pet>)session.createQuery(nativeSQL,Pet.class)
			                .getResultList();

	        tx.commit();
	        return pets;
		}
	}
	
	public static List<Pet> getAllPetsByCityAndPetType(PetType petType, String city){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT p"
					+ "FROM pet p, user_has_this_pet uhtp, users u"
					+ "WHERE p.id = uhtp.petid AND u.id = uhtp.userid AND u.address LIKE '%:city%'"
					+ "INTERSECT"
					+ "SELECT p"
					+ "FROM pet p, user_has_this_pet uhtp, users u"
					+ "WHERE p.id = uhtp.petid AND u.id = uhtp.userid AND p.pettype = :pet_type";
			
			 List<Pet> pets = (List<Pet>)session.createQuery(nativeSQL,Pet.class)
								 	.setParameter("city", city)
								 	.setParameter("pet_type", petType.getPetType())
					                .getResultList();

	        tx.commit();
	        return pets;
		}
	}
	
	public static void setPetAsNotAdopted(Pet pet) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
					
			Transaction tx = session.beginTransaction();
			        
			String nativeSQL = "UPDATE Pet SET isadopted=0 WHERE petID=:petid";
			        
			session.createQuery(nativeSQL,Pet.class)
				.setParameter("petid", pet.getId());
			        
			tx.commit();
		}
	}
	
	

}
