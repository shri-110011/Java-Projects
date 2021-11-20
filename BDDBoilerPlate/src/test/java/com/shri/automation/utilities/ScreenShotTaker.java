package com.shri.automation.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;


public class ScreenShotTaker {

	public static Scenario currentScenario;
	
	public void takeScreenShot(WebDriver driver, String filename) throws IOException {
		Date date = new Date();
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File SrcFile = screenShot.getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(SrcFile);
		String destinationFilename = "src\\test\\resources\\screenshots\\"+filename+"_"+date.toString().replace(" ", "_").replace(":", "-")+".png";
		try {
			FileUtils.copyFile(SrcFile, new File(destinationFilename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		currentScenario.attach(fileContent, "image/png", "abc");
	}
	
}
