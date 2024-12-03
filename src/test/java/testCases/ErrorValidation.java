package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class ErrorValidation extends BaseClass {
	
	@Test (groups= {"regression"})
	public void LoginErrorValidation() throws InterruptedException
	{
		LoginPage LP=new LoginPage(driver);
		LP.loginApplication("test@gmail.com", "adminn");
		Assert.assertEquals("Incorrect email or password..", LP.getErrorMessage());
		
	}
	
	@Test (groups= {"regressionn"})
	public void verifyProductAddedToCartOrNot() throws InterruptedException
	{
		String productName = "ZARA COAT 3";
		LoginPage hm = new LoginPage(driver);
		HomePage hp = hm.loginApplication("test@gmail.com", "admin");
		System.out.println("productlist");
		hp.productList();
		System.out.println("product list finish");
		hp.AddToCart(productName);
		CartPage cp = new CartPage(driver);
		cp.gotoCartPage();
		Boolean match = cp.addedItemInCart(productName);
		Assert.assertTrue(match);
	}
}

