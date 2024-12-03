package utilities;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Setup_dockergrid {
	

		@BeforeTest(groups= {"regression", "purchase"})
		void startDockerGrid() throws IOException, InterruptedException
		{
		Runtime.getRuntime().exec("cmd /c srart start_dockergrid.bat");
		Thread.sleep(15000);
		}

		@AfterTest(groups= {"regression", "purchase"})
		void stopDockerGrid() throws IOException, InterruptedException
		{
		Runtime.getRuntime().exec("cmd /c start stop_dockergrid.bat");
		Thread.sleep(5000);

		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");	//closes command prompt
		}

}
