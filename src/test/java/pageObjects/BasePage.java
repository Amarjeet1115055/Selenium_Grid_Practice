package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver;
	public WebDriverWait wait;
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	public void WaitForElementToAppear(By findby)
	{
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(findby));
	}
	public void WaitForVisibilityOfElement(By findby)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	public void WaitForInvisibilityOfElement(WebElement element)
	{
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	public void WaitForVisibilityOfElement(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
