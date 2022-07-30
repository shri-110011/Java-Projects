package fileNIO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class NIOStreamRead {

	public static void main(String[] args) {
		
		int i;
		
		// First confirm that a file name has been specified.
		if(args.length != 1) {
			System.out.println("Usage: ShowFile filename");
			return;
		}
		System.out.println("args[0]: "+args[0]);
		
		// Open a file and obtain a stream linked to it.
		try(InputStream fin = Files.newInputStream(Paths.get(args[0]))) {
			
			do {
				i = fin.read();
				if(i != -1) System.out.print((char)i);
			} while(i != -1);
			System.out.println();
		}
		catch(InvalidPathException e) {
			System.out.println("Path error: "+e);
			return;
		}
		catch (IOException e) {
			System.out.println("I/O error: "+e);
		}

	}

}
