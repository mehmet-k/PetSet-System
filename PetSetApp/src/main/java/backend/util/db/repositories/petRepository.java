package backend.util.db.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
			session.createNativeQuery("UPDATE PET SET status = 0 WHERE id=:petID").
						setParameter("petID", pet.getId())
						.executeUpdate();
			tx.commit();
			session.close();
		}
	}
	
	
	public static void hardDeleteInactivePets() {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "DELETE FROM Pet WHERE status = 0";
			
	        session.createNativeQuery(nativeSQL).executeUpdate();
	        
	        tx.commit();
	        session.close();
		}
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
	        
			String nativeSQL = "SELECT p "
					+ "FROM Pet p, userHasThisPet uhtp, User u "
					+ "WHERE p.id = uhtp.petID AND u.id = uhtp.userID AND u.address LIKE :city "
					+ "AND p.isAdopted = 0 AND p.status = 1 "
					+ "INTERSECT "
					+ "SELECT p "
					+ "FROM Pet p, userHasThisPet uhtp, User u "
					+ "WHERE p.id = uhtp.petID AND u.id = uhtp.userID AND p.petType = :pet_type "
					+ "AND p.isAdopted = 0 AND p.status = 1";
			
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
			        
			session.createNativeQuery(nativeSQL)
				.setParameter("petid", pet.getId())
				.executeUpdate();
			        
			tx.commit();
		}
	}
	
	public static Pet getPetByID(int id) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			
			Transaction tx = session.beginTransaction();
			        
			String nativeSQL = "SELECT p FROM Pet p WHERE p.id = :petid";
			        
			Pet pet = session.createQuery(nativeSQL,Pet.class)
						.setParameter("petid", id)
						.getSingleResult();
			        
			tx.commit();
			return pet;
		}
	}
	
	
	

}
