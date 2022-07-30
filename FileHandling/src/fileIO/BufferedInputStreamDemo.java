package fileIO;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class BufferedInputStreamDemo {

	public static void main(String[] args) {
		String s = "This is a &copy; copyright symbol "+
				"but this is &copy not.\n";
		byte buf[] = s.getBytes();
		
		System.out.println("s: "+s);
		
		// ByteArrayInputStream does not have a separate internal buffer of its
		// own, it uses the byte array passed as an argument to it in the
		// constructor as its buffer array.
		ByteArrayInputStream in = new ByteArrayInputStream(buf);
		int c;
		boolean marked = false;
		
		// BufferedInputStreamdo have have a separate internal buffer of its
		// own, whose default size is 8192 bytes.
		try(BufferedInputStream f = new BufferedInputStream(in, 10)) {
			while((c=f.read()) != -1) {
				
				// Note: With BufferedInputStream once the read() is called on the BufferedInputStream object then the
				// number of bytes remaining in the ByteArrayInputStream buffer that are to be read becomes 0 if the 
				// internal buffer size of ByteArrayInputStream is less than the internal buffer size of 
				// BufferedInputStream.
				// System.out.print(in.available());
				
				switch(c) {
					case '&':
						if(!marked) {
							f.mark(3);
							marked = true;
						}
						else {
							marked = false;
						}
						break;
					case ';':
						if(marked) {
							marked = false;
							System.out.print("(c)");
						}
						break;
					case ' ':
						if(marked) {
							marked = false;
							f.reset();
							System.out.print("&");
						}
						else System.out.print(" ");
						break;
					default:
						if(!marked)
							System.out.print((char)c);
						break;
				}
			}
			System.out.println(buf.length);
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
