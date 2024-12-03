package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testBase.BaseClass;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.OrderPlacedPage;
import pageObjects.PaymentPage;
import utilities.Retry;

public class ProductOrder extends BaseClass {

	@Test(dataProvider = "getData", groups = { "purchase" }, priority = 1)
	public void verifyProductAvailableOrNot(HashMap<String, String> input) {
		// String productName = "ZARA COAT 3";
		LoginPage hm = new LoginPage(driver);
		HomePage hp = hm.loginApplication(input.get("email"), input.get("password"));
		System.out.println("productlist");
		hp.productList();
		System.out.println("product list finish");
		boolean avai = hp.VerifyProductAvailableOrNot(input.get("product"));
		Assert.assertTrue(avai);
	}

	@Test(dataProvider = "getData", groups = { "purchase" }, retryAnalyzer = Retry.class, priority = 2)
	// public void submitOrder(String email, String password, String productName)
	// throws InterruptedException, IOException //when not using hashmap
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// String productName = "ZARA COAT 3";
		LoginPage hm = new LoginPage(driver);
		HomePage hp = hm.loginApplication(input.get("email"), input.get("password"));
		System.out.println("productlist");
		hp.productList();
		System.out.println("product list finish");
		hp.AddToCart(input.get("product"));
		CartPage cp = new CartPage(driver);
		cp.gotoCartPage();
		cp.addedItemInCart(input.get("product"));
		Assert.assertTrue(cp.addedItemInCart(input.get("product")));
		cp.Checkout();
		PaymentPage pp = new PaymentPage(driver);
		pp.cardDetails();
		OrderPlacedPage op = new OrderPlacedPage(driver);
		Assert.assertEquals(op.OrderConfirmationPage(), "THANKYOU FOR THE ORDER.");
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		// 1st Approach
//		return new Object [][] {{"test@gmail.com", "admin","ADIDAS ORIGINAL"},
//            {"wait@gmail.com", "admin", "ZARA COAT 3"} }; 

		// 2nd Approach

//            HashMap<Object, Object> map = new HashMap<Object, Object>(); 
//            map.put("email", "test@gmail.com"); 
//            map.put("password", "admin"); 
//            map.put("product", "ZARA COaT 3"); 
//        
//            HashMap<Object, Object> map1 = new HashMap<Object, Object>(); 
//            map1.put("email", "wait@gmail.com"); 
//            map1.put("password", "admin"); 
//            map1.put("product", "ADIDAS ORIGINAl"); 

		// 3rd Approach
		List<HashMap<String, String>> data = getJsonDataToMap("./src//test//resources//loginData.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
}
