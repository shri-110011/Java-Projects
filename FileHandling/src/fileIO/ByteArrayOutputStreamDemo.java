package fileIO;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayOutputStreamDemo {

	public static void main(String[] args) {
		ByteArrayOutputStream f = new ByteArrayOutputStream();
		String s = "This should end up in the array";
		byte buf[] = s.getBytes();
		
		// It returns the value of the count field, which is the number of valid bytes in this output stream.
		System.out.println("count field value "+f.size());
		
		try {
			f.write(buf);
		}
		catch(IOException e) {
			System.out.println("Error writing to the buffer");
		}
		
		System.out.println("Buffer as a string");
		System.out.println(f.toString());
		
		System.out.println("count field value "+f.size());
		
		System.out.println("Into an array");
		byte b[] = f.toByteArray();
		
		for(int i=0; i<b.length; i++) System.out.print((char)b[i]);
		
		System.out.println("\nTo an output stream");
		try(FileOutputStream f2 = new FileOutputStream("test.txt")) {
//			f2.write(f.toByteArray());
			f.writeTo(f2);
		}
		catch(IOException e) {
			System.out.println("I/O error: "+ e);
			return;
		}
		
		System.out.println("Doing a reset");
		f.reset();
		
		for(int i=0; i<3; i++) f.write('X');
		
		System.out.println(f.toString());
				
	}

}
