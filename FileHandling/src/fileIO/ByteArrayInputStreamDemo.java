package fileIO;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteArrayInputStreamDemo {
	
	public static void readBytes(ByteArrayInputStream input) {
		int i;
		while((i = input.read()) != -1) {
			System.out.print((char)i);
		}
	}

	public static void main(String[] args) {
	
		String tmp = "abcdefghijklmnopqrstuvwxyz";
		byte b[] = tmp.getBytes();
		
		// Note: The close() has no effect on a ByteArrayInputStream. So it is not necessary to call close() on a 
		//  ByteArrayInputStream and doing so is not error.
		ByteArrayInputStream input1 = new ByteArrayInputStream(b);
		
		System.out.println("count field value "+input1.available());
		
		readBytes(input1);
		
		try {
			input1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		
		ByteArrayInputStream input2 = new ByteArrayInputStream(b, 0, 3);
		
		readBytes(input2);
		
		
		
	}

}
