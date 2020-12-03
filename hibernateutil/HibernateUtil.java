package hibernateutil;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

public class HibernateUtil implements Serializable {

	
	private static final long serialVersionUID = -400996810416833003L;
	
	public static final EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("EShop3");
	
	
	public static EntityManager getEM(){
		
		EntityManager em = emf.createEntityManager();
		return em;
	}
	
	public static CriteriaBuilder getCB(){
		
		CriteriaBuilder cb = emf.getCriteriaBuilder();
		return cb;
	}
	
	public static void closeEMF(){
		
		emf.close();
	}

}
