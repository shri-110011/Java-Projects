package fileNIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class PathDemo {

	public static void main(String[] args) {
		
		Path filePath = Paths.get("test.txt");
		
		System.out.println("File Name: "+filePath.getFileName());
		System.out.println("Path: "+filePath);
		System.out.println("Absolute Path: "+filePath.toAbsolutePath());
		System.out.println("Parent: "+filePath.toAbsolutePath().getParent());
		
		if(Files.exists(filePath))
			System.out.println("File exists.");
		else
			System.out.println("File does not exist.");
		
		try {
			if(Files.isHidden(filePath))
				System.out.println("File is hidden.");
			else
				System.out.println("File is not hidden.");
		}
		catch(IOException e) {
			System.out.println("I/O error: "+e);
		}
		
		if(Files.isWritable(filePath))
			System.out.println("File is writable.");
		else
			System.out.println("File is not writable.");
		
		if(Files.isReadable(filePath))
			System.out.println("File is readable.");
		else
			System.out.println("File is not readable.");
		
		try {
			BasicFileAttributes attribs = Files.readAttributes(filePath, BasicFileAttributes.class);
			
			if(attribs.isDirectory())
				System.out.println("The file is a directory.");
			else
				System.out.println("The file is not a directory.");
			
			if(attribs.isRegularFile())
				System.out.println("The file is a normal file.");
			else
				System.out.println("The file is not a normal file.");
			
			if(attribs.isSymbolicLink())
				System.out.println("The file is a symbolic link.");
			else
				System.out.println("The file is not a symbolic link.");
			
			System.out.println("File last modified: "+attribs.lastModifiedTime());
			System.out.println("File size: "+attribs.size()+" bytes.");
			System.out.println("File last access: "+attribs.lastAccessTime());
			System.out.println("File created: "+attribs.creationTime());
		}
		catch(IOException e) {
			System.out.println("I/O error: "+e);
		}
		
		
	}

}
