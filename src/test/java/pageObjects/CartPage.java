package pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//li/button[@routerlink='/dashboard/cart']//child::*")
	WebElement btnCart;

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartItem;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement btnCheckout;

	public void gotoCartPage() throws InterruptedException {
		//btnCart.click();
		Thread.sleep(1000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", btnCart); 
	}

	public boolean addedItemInCart(String productName) {
		boolean flag = false;
		for (WebElement itmC : cartItem) {
			if (itmC.getText().equalsIgnoreCase(productName)) {
				flag = true;
			}
		}
		return flag;
	}
	
	public void Checkout()
	{
		btnCheckout.click();
	}

}
