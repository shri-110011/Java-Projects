package fileIO;

import java.io.File;

public class FileDemo {
	
	static void p(String s) {
		System.out.println(s);
	}

	public static void main(String[] args) {
		File f1 = new File("D:\\Java EE Workspace");
		
		// If the directory path represented by the file object is empty string then getName() returns empty string.
		p("FilenName: "+f1.getName());
		p("Path: "+f1.getPath());
		
		// If the directory path represented by the file object is empty string then the absolute path of the 
		// current directory is returned by getAbsolutePath() for Windows.
		p("Abs Path: "+f1.getAbsolutePath());
		
		// If the directory path represented by the file object does not name a parent then getParent() 
		// returns null.
		p("Parent: "+f1.getParent());
		
		p(f1.exists() ? "exists" : "does not exist");
		
		p(f1.canWrite() ? "is writable" : "is not writable");
		
		p(f1.canRead() ? "is readable" : "is not readable");
		
		p("is "+ (f1.isDirectory() ? "" : "not ") + "a directory");
		
		p(f1.isFile() ? "is normal file" : "might be a named pipe");
		
		p(f1.isAbsolute() ? "is Absolute" : "is not Absolute");
		
		// lastModified() returns a long value representing the time the file waslast modified, measured in 
		// milliseconds since the epoch(00:00:00 GMT, January 1, 1970), or 0L if thefile does not exist 
		// or if an I/O error occurs. The value maybe negative indicating the number of milliseconds before the epoch.
		p("File last modified: "+f1.lastModified());
		
		
		// length() returns the length, in bytes, of the file denoted by this abstractpathname, 
		// or 0L if the file does not exist. The return value is unspecified if this pathname denotes a directory.
		p("File size: "+f1.length()+ " Bytes");
		
		

	}

}
