package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import io.cucumber.messages.internal.com.google.common.io.Files;

public class ScreenShotTaker {//file://D:/Java%20EE%20Workspace/Cucumber-TestNg/src/test/resources/screenshots/scr1_Sun_Jul_18_12-50-25_IST_2021.png
	
	public void takeScreenShot(WebDriver driver, String filename) {
		Date date = new Date();
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File SrcFile = screenShot.getScreenshotAs(OutputType.FILE);
		final byte[] scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		File destinationFile = new File("src\\test\\resources\\screenshots\\"+filename+"_"+date.toString().replace(" ", "_").replace(":", "-")+".png");
		String filePath = destinationFile.toString();
		System.out.println("#############"+filePath);
//		Reporter.log("<br><img src='"+destinationFile+"' height='300' width='300'/><br>");
		Reporter.log("<a href='"+ destinationFile.getAbsolutePath() + "'> <img src='"+ destinationFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
		try {
			Files.copy(SrcFile, destinationFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
