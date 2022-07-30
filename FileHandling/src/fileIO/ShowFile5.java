// Note: In this variation of ShowFile the try with resources has been used to make use of 
// ARM(Automatic Resource Management).

package fileIO;

import java.io.FileInputStream;
import java.io.IOException;

public class ShowFile5 {

	public static void main(String[] args) {
		int i;
		
		System.out.println("args[0]: "+args[0]);
		if(args.length != 1) {
			System.out.println("Usage: ShowFile filename");
			return;
		}
		
		// Attempt to open a file in the try statement
		try(FileInputStream fin = new FileInputStream(args[0])) {

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
		catch(IOException e) {
			System.out.println("I/O error: "+e);
		}

	}

}


//Things will unfold at precisely the right time.