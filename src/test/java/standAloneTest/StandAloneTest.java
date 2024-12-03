package standAloneTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();

		// test@gmail.com, admin
		//String productName = "ZARA COAT 3";
		String productName = "ADIDAS ORIGINAL";
		
		WebElement email = driver.findElement(By.id("userEmail"));
		email.sendKeys("test@gmail.com");
		WebElement pswd = driver.findElement(By.xpath("//input[@type='password']"));
		pswd.sendKeys("admin");

		WebElement btnLogin = driver.findElement(By.xpath("//*[@id='login']"));
		btnLogin.click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='row']/div[contains(@class,'ng-star-inserted')]")));

		List<WebElement> products = driver
				.findElements(By.xpath("//div[@class='row']/div[contains(@class,'ng-star-inserted')]"));
		
		for (WebElement prod : products) {
			System.out.println(prod.findElement(By.xpath("//b")).getText());
			}
		

//		for (WebElement prod : products) {
//			if (prod.findElement(By.xpath("//b")).getText().equalsIgnoreCase(productName)) {
//				prod.findElement(By.xpath("//button[2]")).click();
//				break;
//			}
//		}
		
		for (WebElement product : products) {
		    // Find the text inside the <b> tag and check if it matches "ZARA COAT 3"
		    String prodName = product.findElement(By.cssSelector("b")).getText();
		    if (prodName.equals(productName)) {
		        // If found, find and click the last button in the card-body
		        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		        break; // Stop after finding and clicking the first matching product
		    }
		}

//		List<WebElement> products= driver.findElements(By.xpath("//div[@class='row']/div[contains(@class,'ng-star-inserted')]"));
//		WebElement prod = products.stream().filter(product->
//		product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
//		
//		prod.findElement(By.xpath("//div/button[2]")).click();		

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		WebElement btnCart = driver.findElement(By.xpath("//li/button[@routerlink='/dashboard/cart']//child::*"));
		btnCart.click();

		List<WebElement> itmCart = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

		for (WebElement itmC : itmCart) {
			if (itmC.getText().equalsIgnoreCase(productName)) {
				Assert.assertEquals(true, true);
				break;
			} else {
				Assert.assertTrue(false);
			}
		}

		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

		WebElement crdNum = driver
				.findElement(By.xpath("//div[contains(text(),'Credit Card Number ')]/parent::div/input"));
		crdNum.clear();
		crdNum.sendKeys("787689872320");
		WebElement expMonth = driver.findElement(By.xpath("(//select[@class='input ddl'])[1]"));
		// expMonth.click();
		Select expMon = new Select(expMonth);
		expMon.selectByVisibleText("05");

		WebElement expDate = driver.findElement(By.xpath("(//select[@class='input ddl'])[2]"));
		// expDate.click();

		Select expDt = new Select(expDate);
		expDt.selectByVisibleText("27");

		driver.findElement(By.xpath("(//div[text()='CVV Code ']/following::input)[1]")).sendKeys("890");
		driver.findElement(By.xpath("//div[text()='Name on Card ']/parent::div/input")).sendKeys("Amarjeet");

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		driver.findElement(By.xpath("(//input[@placeholder='Select Country']/parent::div/section/button/span)[2]"))
				.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		WebElement placeOrderbtn = driver.findElement(By.xpath("//a[text()='Place Order ']"));
		// placeOrderbtn.click(); //not working here
		js.executeScript("arguments[0].click()", placeOrderbtn);

		String msgSuccess = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(msgSuccess, "THANKYOU FOR THE ORDER.");

		driver.close();

	}

}
