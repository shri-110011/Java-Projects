// This program shows the concept of preemption of original exception by the secondary exception while using normal try
// statement

package fileIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShowFile6 {
	
	static void readFile(String filePath) throws Exception {
		int i;
		FileInputStream fin = null;
		
		try {

			// Attempt to open a file
			fin = new FileInputStream(filePath);
			System.out.println("File opened: "+filePath);
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
			throw new IOException(e);
		}
		finally {
			fin.close();
			System.out.println("File closed: "+filePath);
		}
	}

	public static void main(String[] args) {
		System.out.println("args[0]: "+args[0]);
		if(args.length != 1) {
			System.out.println("Usage: ShowFile filename");
			return;
		}
		
		try {
			readFile(args[0]);
		}
		catch(Exception e) {
			System.out.println("Number of suppresed exceptions: "+e.getSuppressed().length);
			if(e.getSuppressed().length>0)
				System.out.println("Suppressed exception: "+e.getSuppressed()[0]);
		}
	}

}
