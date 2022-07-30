
// Demonstrate FileInputStream.
// This program uses try-with-resources. It requires JDK 7 or later.

package fileIO;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamDemo {

	public static void main(String[] args) {
		int size;
		
		// Use try-with-resources to close the stream.
		try(FileInputStream f = new FileInputStream("D:\\Java EE Workspace\\Learn-Java\\FileHandling\\src\\fileIO\\FileInputStreamDemo.java")) {
			System.out.println("Total Bytes Available: "+(size = f.available()));
			
			int n = size/40;
			System.out.println("First "+n+" bytes of the file with one read() at a time");
			
			for(int i = 0; i<n; i++) {
				System.out.print((char)f.read());
			}
			
			System.out.println("\nStill Available: "+f.available()+" bytes.");
			
			System.out.println("Reading the next "+n+" bytes with one read(b[])");
			
			byte b[] = new byte[n];
			if(f.read(b) != n)
				System.out.println("Couldn't read "+n+" bytes.");
			
			System.out.println(new String(b, 0, n));
			
			System.out.println("Skipping half of remaining bytes with skip()");
			f.skip(size/2);
			System.out.println("Still Available: "+f.available()+" bytes.");
			
			System.out.println("Reading "+n/2+" bytes into the the end of the array.");
			if(f.read(b, n/2, n/2) != n/2)
				System.out.println("Couldn't read "+n/2+" bytes.");
			
			System.out.println(new String(b, 0, b.length));
			System.out.println("\nStill Available: "+f.available()+" bytes.");
				
		}
		catch(IOException e) {
			System.out.println("I/O Error: "+e);
		}

	}

}
