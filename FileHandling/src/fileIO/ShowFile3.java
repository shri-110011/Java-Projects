
// Note: In this variation of ShowFile the portion of the program that opens and access the file for reading have been 
// wrapped in a single try block.

package fileIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShowFile3 {

	public static void main(String[] args) {
		int i;
		FileInputStream fin = null;
		
		System.out.println("args[0]: "+args[0]);
		if(args.length != 1) {
			System.out.println("Usage: ShowFile filename");
			return;
		}
		
		try {

			// Attempt to open a file
			fin = new FileInputStream(args[0]);
			System.out.println("File opened: "+args[0]);
			System.out.println("--------------------------------");
	
		
			// At this point the file is open and can be read.
			// The following reads characters until EOF is reached.
			int count = 0;
			do {
				i = fin.read();
				if(i != -1) { count++; System.out.print((char)i);}
			} while(i != -1);
			System.out.println("\n--------------------------------");
			System.out.println("count: "+count);
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot open file: "+args[0]+" !");
			return;
		}
		catch(IOException e) {
			System.out.println("Error in reading file: "+args[0]+" !");
		}
		finally {
			// Close the file
			try {
				if(fin != null) {
					fin.close();
					System.out.println("File closed: "+args[0]);
				}
			}
			catch(IOException e) {
				System.out.println("Error in closing file: "+args[0]+" !");
			}
		}
		
	}

}

//Things will unfold at precisely the right time.