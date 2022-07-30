
// Note: In this variation of ShowFile the closing of file code has been moved to the finally block.
// This is because if the code to access the file for reading fails because of some non-IO exception
// still the file would be closed.
package fileIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShowFile2 {

	public static void main(String[] args) {
		int i;
		FileInputStream fin;
		
		System.out.println("args[0]: "+args[0]);
		if(args.length != 1) {
			System.out.println("Usage: ShowFile filename");
			return;
		}
		
		// Attempt to open a file
		try {
			fin = new FileInputStream(args[0]);
			System.out.println("File opened: "+args[0]);
			System.out.println("--------------------------------");
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot open file: "+args[0]+" !");
			return;
		}
		
		// At this point the file is open and can be read.
		// The following reads characters until EOF is reached.
		try {
			int count = 0;
			do {
				i = fin.read();
				if(i != -1) { count++; System.out.print((char)i);}
			} while(i != -1);
			System.out.println("\n--------------------------------");
			System.out.println("count: "+count);
		}
		catch(IOException e) {
			System.out.println("Error in reading file: "+args[0]+" !");
		}
		finally {
			// Close the file
			try {
				fin.close();
				System.out.println("File closed: "+args[0]);
			}
			catch(IOException e) {
				System.out.println("Error in closing file: "+args[0]+" !");
			}
		}
		
	}

}

//Things will unfold at precisely the right time.