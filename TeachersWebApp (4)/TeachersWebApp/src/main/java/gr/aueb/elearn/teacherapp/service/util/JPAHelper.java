package gr.aueb.elearn.teacherapp.service.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * A generic Helper class that provides access to JPA.
 *  
 */
public final class JPAHelper {

    private static EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();
    
    /**
	 * No instances of this Utility class should be available.
	 */
    private JPAHelper() {
    	
    }
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("teacherPU");
        }
        return emf;
    }
    
    public static EntityManager getEntityManager() {      
        EntityManager em = threadLocal.get();         
        if (em  == null || !em.isOpen()) {
            em = getEntityManagerFactory().createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }
    
    public static void closeEntityManager() { 
    	if (getEntityManager() != null) {
    		getEntityManager().close();
    	}  
    }
    
    public static void beginTransaction() {      
        getEntityManager().getTransaction().begin();
    }
    
    public static void commitTransaction() {      
    	getEntityManager().getTransaction().commit();
    }
    
    public static void rollbackTransaction() {      
        getEntityManager().getTransaction().rollback();
    }
    
    public static void closeEMF() {
    	emf.close();
    }
}
