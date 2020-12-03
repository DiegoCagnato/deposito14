package services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import daoservices.ProductDaoService;
import entity.Product;

public class ProductServicesImp implements Services<Product> {
	
	@Inject
    private ProductDaoService productDao;
	
	
	@Override
	public void findAndPrint(Product product, String idProduct){
		
		String id = idProduct;
		Product p = productDao.getById(product, (String)id);
	    print(p);
	}
	
	@Override
    public void findAndModify(Product product, String idProduct, String productName, 
    		Integer price, String description){
		
		productDao.modify(product, idProduct, productName, price, description);
	}
	
	@Override
	public void printAll(){
		
		List<Product> products = new ArrayList<>();
		Product p = new Product();
		products = productDao.getAll(p);
		for(Product product : products){
			print(product);
		}
	}
	
	public void print(Product p){
		
		System.out.println("Prodotto: " + p.getProductName());
	    System.out.println("della categoria: " + p.getCategory().getCategoryName());
	    System.out.println("Prezzo: " + p.getPrice());
	    System.out.println("Descrizione: " + p.getDescription());
	    System.out.println("------------------------------------");
	}

}
