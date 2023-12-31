package backend.util.db.repositories;


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
	
}
