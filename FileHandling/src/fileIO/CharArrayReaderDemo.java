package fileIO;

import java.io.CharArrayReader;
import java.io.IOException;

public class CharArrayReaderDemo {
	
	public static void readCharacters(CharArrayReader input) throws IOException {
		int i;
		while((i = input.read()) != -1) {
			System.out.print((char)i);
		}
	}

	public static void main(String[] args) {
		String tmp = "abcdefghijklmnopqrstuvwxyz";
		char c[] = new char[tmp.length()];
		
		tmp.getChars(0, tmp.length(), c, 0);
		
		CharArrayReader input1 = new CharArrayReader(c);
		
		try {
			readCharacters(input1);
			
			// Note: The close() has no effect on a CharArrayReader. So it is not necessary to call close() on a 
			//  CharArrayReader and doing so is not error.
			input1.close();
			
			System.out.println();
			
			CharArrayReader input2 = new CharArrayReader(c, 0, 3);
			
			readCharacters(input2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
