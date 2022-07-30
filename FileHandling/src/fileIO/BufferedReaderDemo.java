package fileIO;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;

public class BufferedReaderDemo {

	public static void main(String[] args) {
		String s = "This is a &copy; copyright symbol "+
				"but this is &copy not.\n";
		char buffer[] = new char[s.length()];
		
		s.getChars(0, s.length(), buffer, 0);
		System.out.println("s: "+s);
		
		// CharArrayReader does not have a separate internal buffer of its
		// own, it uses the character array passed as an argument to it in the
		// constructor as its buffer array.
		CharArrayReader in = new CharArrayReader(buffer, 0, 16);
		int c;
		boolean marked = false;
		
		// BufferedReader do have a separate internal buffer of its
		// own, whose default size is 8192 bytes and it uses it based on the 
		// type of input stream passed as an argument to it in the
		// constructor.
		// If the input stream is a CharArrayReader then the buffer passed as 
		// an argument to CharArrayReader constructor will be used by 
		// BufferedReader as its buffer array.
		try(BufferedReader f = new BufferedReader(in)) {
			buffer[0] ='Z';
			while((c=f.read()) != -1) {
				
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
			System.out.println(buffer.length);
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
