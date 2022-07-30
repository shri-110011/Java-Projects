package fileNIO;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class DirList {

	public static void main(String[] args) {
		
		String dirName = "D:\\Java EE Workspace\\Learn-Java\\FileHandling\\src\\fileNIO";
		
		// Obtain and manage a directory stream within a try block.
		try(DirectoryStream<Path> dirstrm = Files.newDirectoryStream(Paths.get(dirName), "D*")) {
			
			System.out.println("Directory of: "+dirName);
			
			// Because DirectoryStream extends Iterable interface so we can use
			// for-each loop.
			for(Path entry : dirstrm) {
				BasicFileAttributes attribs = Files.readAttributes(entry, BasicFileAttributes.class);
				
				if(attribs.isDirectory())
					System.out.print("<DIR>");
				else
					System.out.print("     ");
				
				System.out.println(entry.getFileName());
			}
		}
		catch(InvalidPathException e) {
			System.out.println("Path error: "+e);
		}
		catch(NotDirectoryException e) {
			System.out.println(dirName+" is not a directory.");
		}
		catch(IOException e) {
			System.out.println("I/O error: "+e);
		}

	}

}
