// This program shows the copying of source file into destination file while using try with resources.

package fileIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile2 {

	public static void main(String[] args) {
		int i;
		
		System.out.println("args[0]: "+args[0]);
		System.out.println("args[1]: "+args[1]);
		if(args.length != 2) {
			System.out.println("Usage: CopyFile sourceFile destinationFile");
			return;
		}
		
		try(FileInputStream fin = new FileInputStream(args[0]); 
			FileOutputStream fout = new FileOutputStream(args[1])) {
			
			int count = 0;
			do {
				i = fin.read();
				if(i != -1) {
					fout.write(i);
					count++;
				}
			} while(i != -1);
			System.out.println("Copying of "+args[0]+" to "+args[1]+" complete !");
			System.out.println("--------------------------------");
			System.out.println("count: "+count);
		}
		catch(IOException e) {
			System.out.println("I/O error: "+e);
		}
	}

}