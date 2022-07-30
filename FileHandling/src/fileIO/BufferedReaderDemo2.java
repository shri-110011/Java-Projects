package fileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderDemo2 {

	public static void main(String[] args) {
		FileReader in = null;
		
		try {
			in = new FileReader("file2.txt");
		}
		catch(FileNotFoundException e) {
			System.out.println("I/O error: "+e);
		}
		
		/* Here BufferedReader uses its internal buffer array because the input 
		 * stream is a FileReader and it doesn't have any buffer array associated
		 * with it which the BufferedReader can make use of.
		 * */
		try (BufferedReader f = new BufferedReader(in)) {
			int c;
			while((c = f.read()) != -1) {
				System.out.print((char)c);
			}
		}
		catch(IOException e) {
			System.out.println("I/O error: "+e);
		}
		
	}

}
