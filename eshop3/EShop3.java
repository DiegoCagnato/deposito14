package eshop3;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import daoservices.CategoryDaoService;
import daoservices.ProductDaoService;
import daoservices.UserDaoService;
import entity.Category;
import entity.Product;
import entity.User;
import hibernateutil.HibernateUtil;
import services.CartService;
import services.CategoryServicesImp;
import services.ProductServicesImp;
import services.UserServicesImp;

public class EShop3 {

	public static void main(String[] args) {
		
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		
		Category caffetteria = new Category("ca", "caffetteria");
		Category piante = new Category("pi", "piante");
		Category telefoni = new Category("te", "telefoni");
		
		CategoryDaoService cds = container.select(CategoryDaoService.class).get();
		
		cds.insert(caffetteria);
		cds.insert(piante);
		cds.insert(telefoni);
		
		
		Product tazzine = new Product("ca001", caffetteria, "tazzine pregiate", 40, "tazzine");
		Product moka = new Product("ca002", caffetteria, "moka Bialetti", 20, "moka");
		Product cactus = new Product("pi001", piante, "cactus pungente", 35, "cactus");
		Product baobab = new Product("pi002", piante, "baobab gigante", 50, "baobab");
		Product nokia3310 = new Product("te001", telefoni, "telefono antico", 15, "nokia 3310");
		Product IPhone = new Product("te002", telefoni, "telefono moderno", 100, "iPhone");
		
		ProductDaoService pds = container.select(ProductDaoService.class).get();
		
		pds.insert(tazzine);
		pds.insert(moka);
		pds.insert(cactus);
		pds.insert(baobab);
		pds.insert(nokia3310);
		pds.insert(IPhone);
		
        CategoryServicesImp csi = container.select(CategoryServicesImp.class).get();
		ProductServicesImp psi = container.select(ProductServicesImp.class).get();
		
		psi.findAndModify(baobab, "pi002", "bonsai", 23, "jappo bonsai");
	
		
		System.out.println("CAREGORIE NELLO SHOP: ");
		csi.printAll();
		System.out.println("PRODOTTI NELLO SHOP: ");
		psi.printAll();
		
		User giovanni = new User("jhonny", "Giovanni");
		User maria= new User("mary", "Maria");
		User roberto = new User("bob", "Roberto");
		
		UserDaoService uds = container.select(UserDaoService.class).get();
		
		uds.insert(giovanni);
		uds.insert(maria);
		uds.insert(roberto);
		
		UserServicesImp usi = container.select(UserServicesImp.class).get();
		
		System.out.println("UTENTI NELLO SHOP: ");
		usi.printAll();
		
		CartService cart = container.select(CartService.class).get();
		
		cart.setUser(maria);
		cart.setIdCart(1);
		
		cart.getProductIntoCart(cactus);
		cart.getProductIntoCart(moka);
		cart.getProductIntoCart(baobab);
		cart.removeProductFromCart(moka);
		
		System.out.println("PRODOTTI NEL CARRELLO: ");
		cart.printCartProduct();
		
		cart.purchaseCart();
		
		HibernateUtil.closeEMF();
		
		container.shutdown();

	}

}
