package handlingBinaryFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CheckPNG {
	static final String srcFile = "src/handlingBinaryFile/many-flowers.jpg";
	static final int[] pngFileSignature = {137, 80, 78, 71, 13, 10, 26, 10};
	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream(srcFile);
		
		int readByte;
		boolean isPNG = true;
		
		for(int i=0;i<8;i++) {
			readByte = in.read();
			System.out.println(readByte);
			if(readByte != pngFileSignature[i]) {
				isPNG = false;
				break;
			}
		}
		
		
		System.out.println("Is png file? "+isPNG);
		
		in.close();
	
	}

}
