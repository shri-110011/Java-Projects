package fileIOBasics;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {

	public static void main(String[] args) throws IOException {
		
		//Programs use byte streams to perform input and output of 8-bit bytes. All byte stream classes 
		//are descended from InputStream and OutputStream.
		FileInputStream in = null;
		FileOutputStream out = null;
		
		try {
			
			//If the named file does not exist, is a directory rather than a regular file, or for some 
			//other reason cannot be opened for reading then a FileNotFoundException is thrown.
			in = new FileInputStream("src/fileIOBasics/xanadu.txt");
			
			//If the file exists but is a directory rather than a regular file, does not exist but 
			//cannot be created, or cannot be opened for any other reason then a FileNotFoundException 
			//is thrown.
			out = new FileOutputStream("src/fileIOBasics/outagain.txt");
			
			int c,i=0;
			
			//in.read() returns the next byte of data, or -1 if the end of the file is reached. 
			while((c = in.read()) != -1) {
				if(i == 0) {
					System.out.println(c);
					i++;
				}
				out.write(c);
			}
			
		}
		finally {
			if(in != null) {
				in.close();
			}
			if(out != null) {
				out.close();
			}
		}
	}

}
