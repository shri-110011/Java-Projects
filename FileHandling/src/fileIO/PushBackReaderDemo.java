package fileIO;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.PushbackReader;

public class PushBackReaderDemo {

	public static void main(String[] args) {
		String s = "if(a == 4) a = 0;\n";
		char buf[] = new char[s.length()];
		
		s.getChars(0, s.length(), buf, 0);
		CharArrayReader in = new CharArrayReader(buf);
		int c;
		
		// With no 2nd argument for PushbackReader constructor will result in a 
		// push back buffer that can store one-character.
		try(PushbackReader f = new PushbackReader(in)) {
			while((c = f.read()) != -1) {
				switch(c) {
					case '=':
						if((c = f.read()) == '=') {
							System.out.print(".eq.");
						}
						else {
							f.unread(c);
							System.out.print("<-");
						}
						break;
					default:
						System.out.print((char)c);
				}
			}
		}
		catch(IOException e) {
			System.out.println("I/O error: "+ e);
		}

	}

}
