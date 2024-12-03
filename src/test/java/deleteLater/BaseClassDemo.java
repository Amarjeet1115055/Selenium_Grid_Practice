//package deleteLater;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Properties;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.Platform;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Optional;
//import org.testng.annotations.Parameters;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class BaseClassDemo {
//
//	public static WebDriver driver;
//	public String browser;
//	public String os;
//	public Properties config;
//	public WebDriverWait wait;
//
//	@BeforeMethod(groups = "purchase")
//	@Parameters({ "os", "browser" })
//	public void setUp(@Optional("") String osParam, @Optional("") String browserParam) {
//		try {
//			// Load properties
//			config = new Properties();
//			try (FileInputStream fis = new FileInputStream("./src/test/resources/config.properties")) {
//				config.load(fis);
//			}
//
//			// Determine browser and OS
//			this.browser = (browserParam != null && !browserParam.isEmpty()) ? browserParam.toLowerCase()
//					: (config.getProperty("browser") != null ? config.getProperty("browser").toLowerCase() : "chrome");
//
//			this.os = (osParam != null && !osParam.isEmpty()) ? osParam.toLowerCase()
//					: (config.getProperty("os") != null ? config.getProperty("os").toLowerCase() : "windows");
//
//			if (!isValidBrowser(this.browser)) {
//				throw new IllegalArgumentException("Unsupported browser: " + this.browser);
//			}
//
//            if (!isValidOS(this.os)) {
//                throw new IllegalArgumentException("Unsupported OS: " + this.os);
//            }
//
//			// Determine execution environment
//			String executionEnv = config.getProperty("execution_env").toLowerCase();
//			if (executionEnv.equals("local")) {
//				driver = initializeLocalDriver(this.browser);
//			} else if (executionEnv.equals("remote")) {
//				driver = initializeRemoteDriver(this.os, this.browser, config.getProperty("remote_url"));
//			} else {
//				throw new IllegalArgumentException("Unsupported execution environment: " + executionEnv);
//			}
//
//			// Configure WebDriver
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//			driver.get(config.getProperty("appURL"));
//			driver.manage().window().maximize();
//			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//		} catch (IOException e) {
//			System.err.println("Failed to load configuration file: " + e.getMessage());
//			throw new RuntimeException(e);
//		}
//	}
//
//	private WebDriver initializeLocalDriver(String browser) {
//		switch (browser) {
//		case "chrome":
//
//			return new ChromeDriver();
//		case "chromeheadless":
//			ChromeOptions options=new ChromeOptions();
//			options.addArguments("--headless");
//			return new ChromeDriver(options);
//		case "edge":
//
//			return new EdgeDriver();
//		case "firefox":
//
//			return new FirefoxDriver();
//		default:
//			throw new IllegalArgumentException("Unsupported browser: " + browser);
//		}
//	}
//
//	private WebDriver initializeRemoteDriver(String os, String browser, String remoteUrl) throws IOException {
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//
////		 Set OS
//        switch (os) {
//            case "windows":
//                capabilities.setPlatform(Platform.WINDOWS);
//                break;
//            case "mac":
//                capabilities.setPlatform(Platform.MAC);
//                break;
//            default:
//                throw new IllegalArgumentException("Unsupported OS: " + os);
//        }
//
//		// Set Browser
//		switch (browser) {
//		case "chrome":
//			capabilities.setBrowserName("chrome");
//			break;
//		case "edge":
//			capabilities.setBrowserName("MicrosoftEdge");
//			break;
//		case "firefox":
//			capabilities.setBrowserName("firefox");
//			break;
//		default:
//			throw new IllegalArgumentException("Unsupported browser: " + browser);
//		}
//
//		return new RemoteWebDriver(new URL(remoteUrl), capabilities);
//	}
//
//	private boolean isValidBrowser(String browser) {
//		return browser.equals("chrome") || browser.equals("edge") || browser.equals("firefox");
//	}
//
//    private boolean isValidOS(String os) {
//        return os.equals("windows") || os.equals("mac");
//    }
//
//	@AfterMethod(groups = "purchase")
//	public void tearDown() {
//		if (driver != null) {
//			driver.quit();
//		}
//	}
//
//	// code to take screenshot
//	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
//		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//		String targetFilePath = "./screenshot/reports/" + testCaseName + "_" + timeStamp + ".png";
//		File targetFile = new File(targetFilePath);
//		//Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//		sourceFile.renameTo(targetFile);
//
//		return targetFilePath;
//		
//		
//	}
//
//	// code to convert Json Data to HashMap
//	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		return mapper.readValue(new File(filePath), new TypeReference<List<HashMap<String, String>>>() {
//		});
//	}
//}
