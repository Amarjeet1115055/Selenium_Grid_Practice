package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderPlacedPage extends BasePage {
	
	public OrderPlacedPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(css=".hero-primary")
	WebElement msg;
	
	public String OrderConfirmationPage()
	{
		String msgSuccess=msg.getText();
		return msgSuccess;
	}
}
