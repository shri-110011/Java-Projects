package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FeatureFileParser {
	
	private ExcelReader excelReader; 
	
	public FeatureFileParser() {
		excelReader = new ExcelReader();
	}
	
	public void addDataFromExcelFile(String featurFilePath) throws IOException {
		BufferedReader reader = null;
		PrintWriter writer = null;
		
		File orgFile = new File(featurFilePath);
		File tempFile = new File(orgFile.getParent()+"\\Temp.feature");
		
		try {
			reader = new BufferedReader(new FileReader(orgFile));
			writer = new PrintWriter(new FileWriter(tempFile));
			String line, scenarioName="", excelFilePath, excelSheetName;
			String[] excelFileInfo;
			int noOfLines=0, excelSheetNo;
			boolean columnHeaderToBeEncountered = false;
			
			while((line=reader.readLine())!=null) {
				noOfLines++;
				if(noOfLines>1) {
					if((!line.startsWith("|") && !line.endsWith("|")) || columnHeaderToBeEncountered) {
						writer.write("\n"+line);
					}
				}
				else {
					writer.write(line);
				}
				
				if(line.trim().startsWith("Scenario Outline:")) {
					scenarioName = line.trim().substring(17).trim();
					columnHeaderToBeEncountered = true;
					System.out.println(scenarioName);
				}
				if(line.trim().startsWith("##@data@")) {
					excelFileInfo = line.trim().substring(8).trim().split("@");
					excelFilePath = excelFileInfo[0];
					excelSheetName = excelFileInfo[1];
					columnHeaderToBeEncountered = false;
					try {
						excelSheetNo = Integer.parseInt(excelSheetName);
						System.out.println("Excel workbook path: "+excelFilePath+" | "+"Excel sheet no: "+excelSheetNo);
					}
					catch(NumberFormatException e) {
						System.out.println("Excel workbook path: "+excelFilePath+" | "+"Excel sheet name: "+excelSheetName);
					}
					
					List<String> data = excelReader.getScenarioSpecificData(excelFilePath, excelSheetName, scenarioName);
					for(String str: data) {
//						System.out.println(str);
						writer.write("\n"+str);
					}
					
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(reader!=null) {
				reader.close();
			}
			if(writer!=null) {
				writer.close();
			}
			boolean fileDeleted = orgFile.delete();
//			System.out.println(fileDeleted);
//			if(fileDeleted) {
////				System.out.println(orgFile.getAbsolutePath()+" got deleted!");
//			}
			tempFile.renameTo(orgFile);
//			System.out.println(tempFile.getParent());
		}
	}
	
	public void scanFolderAndAddDataToFeatureFiles(String folderPath) throws IOException {
		File folder = new File(folderPath);
		
		FilenameFilter featureFileFilter = new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				if(name.endsWith(".feature")) {
					return true;
				}
				else {
					return false;
				}
			}
			
		};
		File[] files = folder.listFiles(featureFileFilter);
		
		for(File file: files) {
//			System.out.println(file.getPath());
			addDataFromExcelFile(file.getAbsolutePath());
		}

	}

	public static void main(String[] args) throws IOException {
		FeatureFileParser featureFileParser = new FeatureFileParser();
		featureFileParser.scanFolderAndAddDataToFeatureFiles("src\\test\\resources\\features");
	}

}
