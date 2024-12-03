package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager{
	
	public String repName;
	
	public String path() {
		
		return repName;
	}
	
	public ExtentReports getReportObject() 
	{ 
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-" + timeStamp+ ".html";
		
//		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//		repName="Test-Report-" + timeStamp+ ".html";
		
	//ExtentReports, ExtentSparkReporter 
	
	
	ExtentSparkReporter reporter = new ExtentSparkReporter(".\\reports\\"+repName); 
	
	reporter.config().setReportName("Web Automation Results");
	reporter.config().setDocumentTitle("Test Results"); 
	 
	ExtentReports extent = new ExtentReports(); 
	extent.attachReporter(reporter); 
	
	extent.setSystemInfo("Tester", "Amar"); 
	return extent; 
	 
	} 


}
