package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class ExcelFileException extends Exception {
	public ExcelFileException(String str) {
		super(str);
	}
}

public class ExcelReader {
	
	private Workbook testData = null;
	
	private Map<String, Map<String, List<String>>> excelFileData;
	
	private Workbook getWorkBook(String filePath) throws ExcelFileException {
		File file = new File(filePath);
		
		try {
			FileInputStream fis = new FileInputStream(file);
			
			testData = new XSSFWorkbook(fis);
		}
		catch(FileNotFoundException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
			throw new ExcelFileException(e.getMessage());
		}
		catch(IOException e) {
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return testData;
	}
	
	private Sheet getSheetByIndex(String filePath, int sheetNo) throws ExcelFileException {
		Sheet sheet = getWorkBook(filePath).getSheetAt(sheetNo);
		return sheet;
	}
	
	private Sheet getSheetByName(String filePath, String sheetName) throws ExcelFileException {
		Sheet sheet = getWorkBook(filePath).getSheet(sheetName);
		return sheet;
	}
	
	private Map<String, List<String>> read(Sheet sheet) throws ExcelFileException {
		if(sheet == null) {
			throw new ExcelFileException("The excel workbook with the specified sheet name or sheet number doesn't exist.");
		}
		int noOfRows = sheet.getLastRowNum()+1;
		Map<String, List<String>> rowData = new HashMap<String, List<String>>();
		String scenarioName="";
		boolean scenarioDataCaptured = false;
		List<String> scenarioData = new ArrayList<>();
		StringBuffer formattedData = new StringBuffer();
//		System.out.println(sheet.getSheetName());
//		System.out.println("noOfRows:"+noOfRows);
		
		for(int i=0;i<noOfRows;i++) {
			Row row = sheet.getRow(i);
//			System.out.println("i:"+i);
			if(row!=null) {
				if(row.getCell(0)==null) {
					int noOfColumns = row.getLastCellNum();
//					System.out.println("noOfCols:"+noOfColumns);
					formattedData.delete(0, formattedData.length());
					formattedData.append("|");
					for(int j=1;j<noOfColumns;j++) {
						Cell cell =  row.getCell(j);
						if(cell != null) {
							if(cell.getCellType() == CellType.STRING) {
								formattedData.append(cell.getStringCellValue()+"|");
							}
							if(cell.getCellType() == CellType.NUMERIC) {
								formattedData.append((int)cell.getNumericCellValue()+"|");
							}
						}
						else {
							formattedData.append("|");
						}
					}
					scenarioData.add(formattedData.toString());
					if(i==noOfRows-1) {
//						System.out.println("Flag 2");
//						System.out.println(scenarioName+", "+scenarioData);
						rowData.put(scenarioName, scenarioData);
					}
				}
				else {
					if(i>0) {
						scenarioDataCaptured = true;
					}
					if(scenarioDataCaptured) {
//						System.out.println("Flag 1");
//						System.out.println(scenarioName+", "+scenarioData);
						rowData.put(scenarioName, scenarioData);
						scenarioData = new ArrayList<>();
						scenarioDataCaptured=false;
					}
					scenarioName = row.getCell(0).getStringCellValue();
//					System.out.println("@"+scenarioName);
				}
			}
		}
		//closing the workbook therby releasing the underlying resources
		try {
			sheet.getWorkbook().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rowData; 
	}
	
	public Map<String, List<String>> getData(String filePath, int sheetNo) throws ExcelFileException {
		Sheet sheet = getSheetByIndex(filePath, sheetNo);
		return read(sheet);
	}
	
	public Map<String, List<String>> getData(String filePath, String sheetName) throws ExcelFileException {
		Sheet sheet = getSheetByName(filePath, sheetName);
		return read(sheet);
	}
	
	public List<String> getScenarioData(String filePath, String sheetName, String scenarioName) throws ExcelFileException{
		Map<String, List<String>> data;
		int sheetNo = 0;
		boolean sheetNoGiven = false;
		try {
			sheetNo = Integer.parseInt(sheetName);
			sheetNoGiven = true;
		}
		catch(NumberFormatException e) {
			
		}
		if(excelFileData==null) {
			if(sheetNoGiven) {
				data = getData(filePath, sheetNo);
			}
			else {
				data = getData(filePath, sheetName);
			}
			excelFileData = new HashMap<String, Map<String,List<String>>>();
			excelFileData.put(sheetName, data);
		}
		else {
			if(excelFileData.containsKey(sheetName)) {
				return excelFileData.get(sheetName).get(scenarioName);
			}
			else {
				if(sheetNoGiven) {
					data = getData(filePath, sheetNo);
				}
				else {
					data = getData(filePath, sheetName);
				}
				excelFileData = new HashMap<String, Map<String,List<String>>>();
				excelFileData.put(sheetName, data);
			}
		}
		return data.get(scenarioName);
	}
	
	public List<String> getScenarioSpecificData(String filePath, String sheetName, String scenarioName) throws ExcelFileException {
		return getScenarioData(filePath, sheetName, scenarioName);
	}
	
	public List<String> getScenarioSpecificData(String filePath, int sheetNo, String scenarioName) throws ExcelFileException {
		return getScenarioData(filePath, String.valueOf(sheetNo), scenarioName);
	}

	public static void main(String[] args) {
		ExcelReader reader = new ExcelReader();
		String scenarioName = "To send email using gmail";
		List<String> data;
		try {
			data = reader.getScenarioSpecificData("src/test/java/dataProvider/TestData.xlsx", "Sheet2", scenarioName);
		
			if(data!=null) {
				for(String str: data) {
					System.out.println(str);
				}
			}
		} catch (ExcelFileException e) {
			System.err.println(e.getMessage());
		}
	}
}
