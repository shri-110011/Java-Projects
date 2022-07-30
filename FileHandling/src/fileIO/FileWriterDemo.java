package fileIO;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {

	public static void main(String[] args) {
		String source = "Now is the time for all good men\n"
				+ "to come to the aid of their country\n"
				+ "and pay their due taxes.";
		
		char buffer[] = new char[source.length()];
		source.getChars(0, source.length(), buffer, 0);
		
		try (
			FileWriter f0 = new FileWriter("file1.txt");
			FileWriter f1 = new FileWriter("file2.txt");
			FileWriter f2 = new FileWriter("file3.txt")) {
			
			System.out.println("source.length(): "+source.length());
			
			// write to first file
			for(int i=0; i<buffer.length; i+=2) f0.write(buffer[i]);
			
			// write to second file
			f1.write(buffer);
			
			// write to third file
			f2.write(buffer, buffer.length-buffer.length/4, buffer.length/4);
			System.out.println(buffer.length-buffer.length/4);
			
			String s = "\n";
			s.getChars(0, s.length(), buffer, 0
					);
			System.out.println(buffer.length);
		}
		catch(IOException e) {
			System.out.println("An I/O error occurred.");
		}

	}

}