package handlingBinaryFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadAllBytesExample {
	static final String srcFile = "src/handlingBinaryFile/xanadu.txt";
   	static final String destFile = "src/handlingBinaryFile/outagain2.txt";
	public static void main(String[] args) throws IOException {
		OutputStream out = new FileOutputStream(destFile);
		
		long fileSize = new File(srcFile).length();
		
		byte[] allBytes = Files.readAllBytes(Paths.get(srcFile));
		
		out.write(allBytes);
		
		out.close();
		
		System.out.println("Write completed!");
	}

}
