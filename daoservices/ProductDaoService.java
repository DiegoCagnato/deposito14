package daoservices;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entity.Category;
import entity.Product;
import hibernateutil.HibernateUtil;

public class ProductDaoService extends GenericDao<Product> {

	
	@Override
	public void insert(Product product){
		
		EntityManager em = HibernateUtil.getEM();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(product);
		Category category = em.find(Category.class, product.getCategory().getIdCategory());
		category.setNProduct(category.getNProduct() + 1);
		et.commit();
		em.close();
	}
	
	
	
	public void modify(Product product, String idProduct, String productName, 
    		Integer price, String description){
		
		EntityManager em = HibernateUtil.getEM();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Product p = em.find(Product.class, idProduct);
		p.setProductName(productName);
		p.setPrice(price);
		p.setDescription(description);
		et.commit();
		em.close();
		
	}
	
}
