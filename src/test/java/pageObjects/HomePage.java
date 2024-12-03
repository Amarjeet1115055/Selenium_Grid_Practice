package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='row']/div[contains(@class,'ng-star-inserted')]")
	List<WebElement> products;

	By product = By.xpath("//div[@class='row']/div[contains(@class,'ng-star-inserted')]");
	By toastMessage = By.cssSelector("#toast-container");
	By addToCart = By.xpath(".//div/button[2]");
	By prodNamePath = By.xpath(".//div/h5/b");

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	public void AddToCart(String productName) {

		WaitForElementToAppear(product);

		for (WebElement prod : products) {
			String prodName = prod.findElement(prodNamePath).getText();
			if (prodName.equalsIgnoreCase(productName)) {
				prod.findElement(addToCart).click();
				break;
			}
		}

		WaitForVisibilityOfElement(toastMessage);
		// WaitForInvisibilityOfElement(spinner);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	}

	public void productList() {
		for (WebElement prod : products) {
			System.out.println(prod.findElement(prodNamePath).getText());
		}
	}
	
	public Boolean VerifyProductAvailableOrNot(String productName)
	{
		WaitForElementToAppear(product);

		for (WebElement prod : products) {
			String prodName = prod.findElement(prodNamePath).getText();
			if (prodName.equalsIgnoreCase(productName)) {
				return true;
			}
		}
		return false;
	}

}
