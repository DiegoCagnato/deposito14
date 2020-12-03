package daoservices;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entity.Category;
import hibernateutil.HibernateUtil;

public class GenericDao<T> implements DaoGenerics<T> {

	
	@Override
	public void insert(T object) {
		
		EntityManager em = HibernateUtil.getEM();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(object);
		et.commit();
		em.close();
		
	}
	
	@Override
	public void cancel(T object, String idObject){
		
		Class<?> objectClass = object.getClass();
		EntityManager em = HibernateUtil.getEM();
		EntityTransaction et = em.getTransaction();
		et.begin();
		@SuppressWarnings("unchecked")
		T obj = (T)em.find(objectClass, idObject);
		em.remove(obj);
		et.commit();
		em.close();
	}

	@Override
	public T getById(T object, String id) {
		
		Class<?> objectClass = object.getClass();
		EntityManager em = HibernateUtil.getEM();
		EntityTransaction et = em.getTransaction();
		et.begin();
		@SuppressWarnings("unchecked")
		T obj = (T)em.find(objectClass, id);
		et.commit();
		em.close();
		return obj;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(T object) {
		
		Class<?> objectClass = object.getClass();
		EntityManager em = HibernateUtil.getEM();
		CriteriaBuilder cb = HibernateUtil.getCB();
		EntityTransaction et = em.getTransaction();
		et.begin();
		CriteriaQuery<?> query = cb.createQuery(objectClass); 
		query.from(objectClass);
		List<?> objects = em.createQuery(query).getResultList();
		et.commit();
		em.close();
		return (List<T>) objects;
	}
	

}
