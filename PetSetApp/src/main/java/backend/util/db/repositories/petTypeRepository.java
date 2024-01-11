package backend.util.db.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import backend.models.Items;
import backend.models.Pet;
import backend.models.PetType;
import backend.models.User;
import backend.util.db.hibernate.HibernateUtility;
import jakarta.persistence.NoResultException;

public class petTypeRepository {
	
	public static boolean isPetTypeExists(String pettype) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			String nativeSQL = "SELECT pt FROM PetType pt WHERE pt.petType = :pettype AND pt.status = 1";
			PetType petType =	(PetType)session.createQuery(nativeSQL,PetType.class)
						.setParameter("pettype", pettype)
						.getSingleResult();
		    return true;
		} 
		catch (NoResultException e) {
		    // Handle case where no result is found
		    return false;
		} 
	}
	
	public static void insertPetType (PetType petType) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
            session.persist(petType);
            tx.commit();
            session.close();
		}
	}
	
	public static void removePetType(PetType petType) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
			session.createNativeQuery("UPDATE PetType SET status = 0 WHERE id =:petTypeID")
								.setParameter("petTypeID", petType.getId())
								.executeUpdate();
			tx.commit();
			session.close();
		}
	}
	
	public static List<PetType> getAllPetTypes(){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT pt FROM PetType pt WHERE pt.status = 1";
			
	        List<PetType> petTypes =(List<PetType>)session.createQuery(nativeSQL,PetType.class)
			                .getResultList();

	        tx.commit();
	        return petTypes;
		}
	}
	
	public static List<Pet> getPetsByGivenPetType(PetType petType){
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "SELECT p FROM Pet p WHERE petTypeID=:pettypeid AND p.isadopted = 0 AND p.status = 1";
			
	        List<Pet> pets =(List<Pet>)session.createQuery(nativeSQL,Pet.class)
	        				.setParameter("pettypeid",petType.getId())
			                .getResultList();

	        tx.commit();
	        return pets;
		}
	}
	
	public static PetType returnPetTypeByPetTypeName(String petTypeName) {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
			String nativeSQL = "SELECT pt FROM PetType pt WHERE pt.petType =:pet_type";
			PetType petType = (PetType) session.createQuery(nativeSQL, PetType.class)
									.setParameter("pet_type", petTypeName)
									.uniqueResult();
			tx.commit();
			session.close();
			return petType;
		}	
	}
	
	public static void hardDeleteInactivePetTypes() {
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
	        
			String nativeSQL = "DELETE FROM Pet_Types WHERE status = 0";
			
	        session.createNativeQuery(nativeSQL).executeUpdate();
	        
	        tx.commit();
	        session.close();
		}
	}
	
	

}
