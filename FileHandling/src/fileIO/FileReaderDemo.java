
// Demonstrate FileReader.
// This program uses try-with-resources. It requires JDK 7 or later.

package fileIO;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {

	public static void main(String[] args) {
		
		try(FileReader fr = new FileReader("D:/Java EE Workspace/Learn-Java/FileHandling/src/fileIO/FileReaderDemo.java")) {
			int c;
			
			//Read and display the file.
			while((c = fr.read()) != -1) {
				System.out.print((char)c);
			}
			
		}
		catch(IOException e) {
			System.out.println("I/O Error: "+e);
		}

	}

}
