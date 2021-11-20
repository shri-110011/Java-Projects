package excel.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	private Workbook testData = null;
	
	private Workbook getWorkBook(String filePath) {
		File file = new File(filePath);
		
		try {
			FileInputStream fis = new FileInputStream(file);
			
			testData = new XSSFWorkbook(fis);
		}
		catch(FileNotFoundException e) {
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return testData;
	}
	
	private Sheet getSheetByIndex(String filePath, int sheetNo) {
		Sheet sheet = getWorkBook(filePath).getSheetAt(sheetNo);
		return sheet;
	}
	
	private Sheet getSheetByName(String filePath, String sheetName) {
		Sheet sheet = getWorkBook(filePath).getSheet(sheetName);
		return sheet;
	}
	
	private Row getRow(Sheet sheet, int rowNum) {
		return sheet.getRow(rowNum);
	}
	
	private List<String> read(Sheet sheet) {
		int noOfRows = sheet.getLastRowNum()+1;
		
		List<String> rowData = new ArrayList<>();
		System.out.println("noOfRows:"+noOfRows);
		
		for(int i=0;i<noOfRows;i++) {
			Row row = sheet.getRow(i);
			if(row!=null) {
				int noOfColumns = row.getLastCellNum();
				System.out.println("noOfCols:"+noOfColumns);
				for(int j=0;j<noOfColumns;j++) {
					Cell cell =  row.getCell(j);
					rowData.add(cell.getStringCellValue());
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
	
	public List<String> getData(String filePath, int sheetNo) {
		Sheet sheet = getSheetByIndex(filePath, sheetNo);
		return read(sheet);
	}
	
	public List<String> getData(String filePath, String sheetName) {
		Sheet sheet = getSheetByName(filePath, sheetName);
		return read(sheet);
	}

	public static void main(String[] args) {
		ExcelReader reader = new ExcelReader();
		
		List<String> data = reader.getData("src/test/java/dataprovider/TestData.xlsx", 0);
		if(data!=null) {
			for(String str:data) {
				System.out.println(str);
			}
		}
	}
}
