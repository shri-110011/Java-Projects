package fileIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {

	public static void main(String[] args) {
		
		int i;
		FileInputStream fin = null;
		FileOutputStream fout = null;
		
		System.out.println("args[0]: "+args[0]);
		System.out.println("args[1]: "+args[1]);
		if(args.length != 2) {
			System.out.println("Usage: CopyFile sourceFile destinationFile");
			return;
		}
		
		try {
			// Attempt to open a file
			fin = new FileInputStream(args[0]);
			fout = new FileOutputStream(args[1]);
			
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
		finally {
			try {
				if(fin != null) {
					fin.close();
					System.out.println("Source file closed: "+args[0]);
				}
			}
			catch(IOException e) {
				System.out.println("Error in closing source file: "+args[0]+" !");
			}
			
			try {
				if(fout != null) {
					fout.close();
					System.out.println("Destination file closed: "+args[1]);
				}
			}
			catch(IOException e) {
				System.out.println("Error in closing destination file: "+args[1]+" !");
			}
			
			
		}

	}

}
