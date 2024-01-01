package backend.util.db.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.hibernate.cfg.Configuration;

import backend.models.Admin;
import backend.models.ItemType;
import backend.models.Pet;
import backend.models.PetType;
import backend.models.User;
import backend.models.adoptionRequest;
import backend.models.userHasThisPet;
import backend.models.userPurchase;

public class HibernateUtility {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(User.class)
            .addAnnotatedClass(Pet.class)
            .addAnnotatedClass(Item.class)
            .addAnnotatedClass(ItemType.class)
            .addAnnotatedClass(PetType.class)
            .addAnnotatedClass(userHasThisPet.class)
            .addAnnotatedClass(Admin.class)
            .addAnnotatedClass(userPurchase.class)
            .addAnnotatedClass(adoptionRequest.class); // Add the User entity mapping

            return configuration.buildSessionFactory(
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build()
            );
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
