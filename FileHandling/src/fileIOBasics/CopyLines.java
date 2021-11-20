package fileIOBasics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CopyLines {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		File file1 = new File("src/fileIOBasics/outagain3.txt");
		File file2 = new File("src/fileIOBasics/outagain3-temp.txt");
		
		int noOfLines=0;
		
		try {
			reader = new BufferedReader(new FileReader(file1));
			writer = new BufferedWriter(new FileWriter(file2));
			
			String line;
			
			while((line=reader.readLine()) != null) {
				
//				writer.flush();
				noOfLines++;
				System.out.println(line);
				if(noOfLines>1)
					writer.write("\n"+line);
				else
					writer.write(line);
				if(noOfLines==2) {
					writer.write("\nHello");
				}
			}
		}
		finally {
			if(reader!=null) {
				reader.close();
			}
			if(writer!=null) {
				writer.close();
			}
			boolean fileDeleted = file1.delete();
			System.out.println(fileDeleted);
			if(fileDeleted) {
				System.out.println(file1.getAbsolutePath()+" got deleted!");
			}
			file2.renameTo(file1);
			System.out.println(file2.getParent());
			System.out.println("No. of lines: "+noOfLines);
		}
	}

}
