import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileManipulator {
//	int lineNum, colNum;
	List<FileComparisonData> data = new ArrayList<FileComparisonData>();
	
	public boolean checkIfFileExists(File file) {
		if(file.exists()) {
			System.out.println("File: "+ file.getName() + " already exists!");
			return true;
		}
		else {
			System.out.println("File: "+ file.getName() + " doesn't exist!");
			return false;
		}
	}
	public void createFile(File file) {
		try {
			if(file.createNewFile()) {
				System.out.println("New file: "+ file.getName() + " created!");
			}
			else {
				System.out.println("File: "+ file.getName() + " already exists!");
			}
		}
		catch(IOException e) {
			System.out.println("An error occurred while creating the file: "+ file.getName());
		}
	}
	public void writeToFile(String filename, String content) {
		try {
			System.out.println("Writing to file: "+ filename);
			FileWriter fileWriter = new FileWriter(filename);
			fileWriter.write(content);
			fileWriter.close();
		}
		catch(IOException e) {
			System.out.println("An error occurred while writing to the file: "+ filename);
		}
	}
	public void readAFile(File file) {
		Scanner sc = null;
		try {
			System.out.println("Reading from file: "+ file.getName());
			sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String data = sc.nextLine();
				System.out.println(data);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
		finally {
			if(sc!=null) {
				sc.close();
			}
		}
	}
	public boolean compareTextFiles(File file1, File file2) {
		boolean areSame = true;
		try {
			Scanner sc1 = new Scanner(file1);
			Scanner sc2 = new Scanner(file2);
			String line1 = "", line2 = "";
			try {
				line1 = sc1.nextLine();
			}
			catch(NoSuchElementException e) {
				System.out.println("File1 is empty");
//				e.printStackTrace();
			}
			try {
				line2 = sc2.nextLine();
			}
			catch(NoSuchElementException e) {
				System.out.println("File2 is empty");
//				e.printStackTrace();
			}
			int lineNum = 1;
			while((line1 !=  null) || (line2 != null)) {
				if(line1 == null || line2 == null) {
					areSame = false;
					break;
				}
				else {
					if(!line1.equals(line2)) {
						areSame = false;
//						System.out.println("File1, line "+lineNum+": "+line1);
//						System.out.println("File2, line "+lineNum+": "+line2);
						this.data.add(new FileComparisonData(lineNum, this.getIndeOfFirstDifference(line1, line2)));
//						break;
					}
				}
				try {
					line1 = sc1.nextLine();
				}
				catch(NoSuchElementException e) {
					line1 = null;
				}
				try {
					line2 = sc2.nextLine();
				}
				catch(NoSuchElementException e) {
					line2 = null;
				}
				lineNum++;
			}
			sc1.close();
			sc2.close();
			if(line1 == null) {
				line1 = "";
			}
			if(line2 == null) {
				line2 = "";
			}
//			if(!areSame) {
//				System.out.println("Line1: "+line1);
//				System.out.println("Line2: "+line2);
//				System.out.println("lineNum: "+lineNum);
//				this.data.add(new FileComparisonData(lineNum, this.getIndeOfFirstDifference(line1, line2)));
//				fcd.lineNum = lineNum;
//				fcd.colNum = this.getIndeOfFirstDifference(line1, line2);
//			}
			return areSame;
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
			return areSame;
		}
	}
	public int getIndeOfFirstDifference(String str1, String str2) {
		int str1Len = str1.length();
		int str2Len = str2.length();
		int minLength = str1Len>str2Len?str2Len:str1Len, idx=0;
		for(int i=0; i<minLength; i++) {
			if(str1.charAt(i) != str2.charAt(i)) {
				idx = i;
				break;
			}
		}
		return idx+1;
	}
	public static void main(String[] args) {
		String filename1 = "abc1.txt";
		String filename2 = "abc2.txt";
//		String content = "Today is a sunny day!";
//		File file = new File(filename1);
		FileManipulator fileManipulator = new FileManipulator();
//		if(!fileManipulator.checkIfFileExists(file)) {
//			fileManipulator.createFile(file);
//		}
//		fileManipulator.writeToFile(filename1, content);
//		fileManipulator.readAFile(file);
		File file1= new File(filename1);
		File file2 = new File(filename2);
		System.out.println("Files are same: "+ fileManipulator.compareTextFiles(file1, file2));
//		System.out.println(fileManipulator.getIndeOfFirstDifference("I prefer coffee over tea.", "I prefer coffee over Tea."));
//		System.out.println("File1 differs File2 at lineNum: "+fcd.lineNum + " , colNum: "+fcd.colNum);
		for(FileComparisonData data: fileManipulator.data) {
			System.out.println("Line: "+data.getLineNum()+ " , Col: "+data.getColNum());
		}
	}

}
