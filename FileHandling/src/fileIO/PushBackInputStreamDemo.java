
// Demonstrate unread().
// This program uses try-with-resources. It requires JDK 7 or later.

package fileIO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

public class PushBackInputStreamDemo {

	public static void main(String[] args) {
		String s = "if(a == 4) a = 0;\n";
		byte buf[] = s.getBytes();
		ByteArrayInputStream in = new ByteArrayInputStream(buf);
		int c;
		
		try(PushbackInputStream f = new PushbackInputStream(in)) {
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
