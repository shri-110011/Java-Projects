package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.messages.internal.com.google.common.io.Files;

public class ScreenShotTaker {
	
	public void takeScreenShot(WebDriver driver, String filename) {
		Date date = new Date();
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File SrcFile = screenShot.getScreenshotAs(OutputType.FILE);
		
		//Please don't change this path inside the folderName
		String folderName = "src\\test\\resources\\screenshots\\";
		String destinationFilename = folderName+filename+"_"+date.toString().replace(" ", "_").replace(":", "-")+".png";
		try {
			Files.copy(SrcFile, new File(destinationFilename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
