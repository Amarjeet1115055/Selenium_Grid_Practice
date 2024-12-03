package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testBase.BaseClass;

public class Listeners extends BaseClass implements ITestListener {

	ExtentTest test;
	ExtentReportManager erm= new ExtentReportManager();
	ExtentReports extent =erm.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();  //Thread safe

	public void onStart(ITestContext testContext) {

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestStart(ITestResult result) {
//		test = extent.createTest(result.getTestClass().getName());
//		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
//		extentTest.set(test);  //unique thread id (ErrorValidationTest)->test
		
		// Create a unique test in the Extent Report for each test method
	    String testName = result.getMethod().getMethodName(); // Use method name for uniqueness
	    test = extent.createTest(testName);

	    // Add categories (groups) to the test if any
	    test.assignCategory(result.getMethod().getGroups()); 

	    // Map this test to the current thread for thread-safe operations
	    extentTest.set(test); 
	    
	}

	public void onTestSuccess(ITestResult result) {

		extentTest.get().log(Status.PASS, result.getName() + " got successfully executed");

	}

	public void onTestFailure(ITestResult result) {

		extentTest.get().log(Status.FAIL, result.getName() + " got failed");
		extentTest.get().log(Status.INFO, result.getThrowable().getMessage());
		
		try{ 
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()); 
			}catch (Exception e1) { 
			e1.printStackTrace(); 
			} 


		try {
			String imgPath = getScreenshot(result.getMethod().getMethodName(), driver);
			extentTest.get().addScreenCaptureFromPath(imgPath, result.getMethod().getMethodName());
			

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
//		try {
//			String imgPath = new BaseClass().getScreenshot(result.getName(),driver);
//			test.addScreenCaptureFromPath(imgPath);
//			
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}

	public void onTestSkipped(ITestResult result) {

		extentTest.get().log(Status.SKIP, result.getName() + " got skipped");
		extentTest.get().log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {

		extent.flush();
		extentTest.remove();

		//Code to automatically open the report.

		
		//String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+erm.path();
		
		//File extentReport = new File(System.getProperty("user.dir")+"\\reports\\"+erm.path());
		File extentReport = new File(System.getProperty("user.dir") + File.separator + "reports" + File.separator + erm.path());
		if (extentReport.exists()) {
		    try {
		        Desktop.getDesktop().browse(extentReport.toURI());
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		} else {
		    System.err.println("Report file not found: " + extentReport.getPath());
		}




	}

}
