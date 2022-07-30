package fileIO;

import java.io.CharArrayWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CharArrayWriterDemo {

	public static void main(String[] args) {
		CharArrayWriter f = new CharArrayWriter();
		String s = "This should end up \n in the array";
		char buf[] = new char[s.length()];
		
		s.getChars(0, s.length(), buf, 0);
		
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
		char c[] = f.toCharArray();
		
		for(int i=0; i<c.length; i++) System.out.print((char)c[i]);
		
		System.out.println("\nTo an output stream");
		try(FileWriter f2 = new FileWriter("test.txt")) {
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
