package services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import daoservices.ProductDaoService;
import entity.Product;
import entity.User;

public class CartService {

	@Inject
	private ProductDaoService pds;
	
	@Inject
	private PurchaseService ps;
	
	@Inject
	private ProductServicesImp psi;
	
	private Integer price;
	private Set<Product> cart;
	private Map<String, Product> cartMap;
	private User user;
	private Integer idCart;
	
	public CartService(){
		
		cart = new HashSet<>();
		cartMap = new HashMap<>();
		price = 0;
	}
	
	public void setUser(User user){
		
		this.user = user;
	}
	
	public User getUser(){
		
		return user;
	}
	
	public void setIdCart(Integer idCart){
		
		this.idCart= idCart;
	}
	
	public Integer getIdCart(){
		
		return idCart;
	}
	
	public void setPrice(Integer price){
		
		this.price = price;
	}
	
	public Integer getPrice(){
		
		return price;
	}
	
	public void getProductIntoCart(Product product){
		
		Product p = pds.getById(product, product.getIdProduct());
		cartMap.put(p.getIdProduct(), p);
		cart.add(p);
	}
	
	public void removeProductFromCart(Product product){
		
		Product p = cartMap.get(product.getIdProduct());
		cart.remove(p);
	}
	
	public void purchaseCart(){
		
		
		for(Product product : cart){
			
			ps.purchase(product, product.getIdProduct(), user, idCart);
			price = price + product.getPrice();
		}
		
		System.out.println("Costo totale carrello: " + price);
	}
	
	public void printCartProduct(){
		
		for(Product product : cart){
			
			psi.print(product);
		}
	}
}
