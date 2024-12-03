package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PaymentPage extends BasePage {

	public PaymentPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[contains(text(),'Credit Card Number ')]/parent::div/input")
	WebElement crdNum;

	@FindBy(xpath = "(//select[@class='input ddl'])[1]")
	WebElement expMonth;

	@FindBy(xpath = "(//select[@class='input ddl'])[2]")
	WebElement expDate;

	@FindBy(xpath = "(//div[text()='CVV Code ']/following::input)[1]")
	WebElement cvv;

	@FindBy(xpath = "//div[text()='Name on Card ']/parent::div/input")
	WebElement cardHolderName;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".ta-results")
	WebElement cntryElement;

	@FindBy(xpath = "(//input[@placeholder='Select Country']/parent::div/section/button/span)[2]")
	WebElement cntrySecondElement;

	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement placeOrderbtn;

	public void cardDetails() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// crdNum.clear();
		js.executeScript("arguments[0].setAttribute('value','787656545654')", crdNum);
		// crdNum.sendKeys("787689872320");
		Select expMon = new Select(expMonth);
		expMon.selectByVisibleText("05");
		Select expDt = new Select(expDate);
		expDt.selectByVisibleText("27");
		js.executeScript("arguments[0].setAttribute('value','980')", cvv);
		// cvv.sendKeys("980");
		js.executeScript("arguments[0].setAttribute('value','Amar')", cardHolderName);
		// cardHolderName.sendKeys("Amar");
		// js.executeScript("arguments[0].setAttribute('value','Ind')", country);
		Thread.sleep(1000);
		country.sendKeys("Ind");
		WaitForVisibilityOfElement(cntryElement);
		js.executeScript("arguments[0].click()", cntrySecondElement);
		// cntrySecondElement.click();
		PlaceOrder();

	}

	public void PlaceOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		// placeOrderbtn.click(); //not working here
		js.executeScript("arguments[0].click()", placeOrderbtn);
	}

}
