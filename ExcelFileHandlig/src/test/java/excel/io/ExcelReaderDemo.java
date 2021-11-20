package excel.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderDemo {

	public static void main(String[] args) {
		Workbook testData = null;
		
		try {
			File file = new File("src/test/java/dataprovider/TestData.xlsx");
			
			FileInputStream fis = new FileInputStream(file);
		
			testData = new XSSFWorkbook(fis);
			
			Sheet sheet = testData.getSheetAt(0);
			
			int noOfRows = sheet.getLastRowNum()+1;
			System.out.println("\n>>No of rows: "+noOfRows+"\n");
			
			for(int i=0;i<noOfRows;i++) {
				Row row = sheet.getRow(i);
				if(row!=null) {
					int noOfColumns = row.getLastCellNum();
					System.out.println(">>No of column: "+noOfColumns);
					for(int j=0;j<noOfColumns;j++) {
						Cell cell = row.getCell(j);
						System.out.print(cell.getStringCellValue()+" ");
					}
					System.out.println("\n");
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(testData!=null) {
				try {
					testData.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
