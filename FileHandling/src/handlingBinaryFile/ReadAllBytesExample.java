package handlingBinaryFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReadAllBytesExample {
	static final String srcFile = "src/handlingBinaryFile/xanadu.txt";
   	static final String destFile = "src/handlingBinaryFile/outagain2.txt";
	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream(srcFile);
		OutputStream out = new FileOutputStream(destFile);
		
		long fileSize = new File(srcFile).length();
		
		byte[] allBytes = in.readAllBytes();
		out.write(allBytes);
	}

}
