package fileIOBasics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class NIOCopy {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("Usage: Copy from to");
			return;
		}
		
		try {
			Path source = Paths.get(args[0]);
			Path destination = Paths.get(args[1]);
			
			// Copy the file.
			Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
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
