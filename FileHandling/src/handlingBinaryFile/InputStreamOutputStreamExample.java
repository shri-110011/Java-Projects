package handlingBinaryFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputStreamOutputStreamExample {
	static final String srcFile = "src/handlingBinaryFile/xanadu.txt";
   	static final String destFile = "src/handlingBinaryFile/outagain.txt";
	
	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream(srcFile);
		OutputStream out = new FileOutputStream(destFile);
		
		int readByte;
		while((readByte = in.read()) != -1) {
			out.write(readByte);
		}
		
		in.close();
		out.close();
		
		
	}

}
