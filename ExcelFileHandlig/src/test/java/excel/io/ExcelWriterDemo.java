package excel.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriterDemo {

	public static void main(String[] args) {
		Workbook testData = null;
		
		
		try {
			
			testData = new XSSFWorkbook();
			
			Sheet sheet = testData.createSheet();
			
			Row row;
			
			// This data needs to be written (Object[])
	        Map<String, Object[]> studentData
	            = new TreeMap<String, Object[]>();
	  
	        studentData.put(
	            "1",
	            new Object[] { "Roll No", "NAME", "Year" });
	  
	        studentData.put("2", new Object[] { "128", "Aditya",
	                                            "2nd year" });
	  
	        studentData.put(
	            "3",
	            new Object[] { "129", "Narayana", "2nd year" });
	  
	        studentData.put("4", new Object[] { "130", "Mohan",
	                                            "2nd year" });
	        
	        Set<String> keyid = studentData.keySet();
	        
	        int rowid = 0;
	  
	        // writing the data into the sheets...
	  
	        for (String key : keyid) {
	  
	            row = sheet.createRow(rowid++);
	            Object[] objectArr = studentData.get(key);
	            int cellid = 0;
	  
	            for (Object obj : objectArr) {
	                Cell cell = row.createCell(cellid++);
	                cell.setCellValue((String)obj);
	            }
	        }
	        
	        FileOutputStream out = new FileOutputStream(
	                new File("D:\\Java EE Workspace\\ExcelFileHandlig\\src\\test\\java\\dataprovider\\TestData1.xlsx"));
	      
            testData.write(out);
            out.close();

		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
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
