package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(id="userEmail")
	WebElement inputEmail;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement inputPswd;
	
	@FindBy(xpath="//*[@id='login']")
	WebElement btnLogin ;
	
	@FindBy(id="toast-container")
	WebElement errorMsg;
	
	public HomePage loginApplication(String email, String password)
	{
		inputEmail.sendKeys(email);
		inputPswd.sendKeys(password);
		btnLogin.click();
		HomePage hp=new HomePage(driver);
		return hp;
	}
	
	public String getErrorMessage() throws InterruptedException {
		Thread.sleep(1000);
		return errorMsg.getText();
	}
	
	

}
