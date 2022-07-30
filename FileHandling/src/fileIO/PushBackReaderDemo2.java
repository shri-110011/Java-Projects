package fileIO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

public class PushBackReaderDemo2 {

	public static void main(String[] args) {
		FileReader in = null;
		
		try {
			in = new FileReader("file2.txt");
		}
		catch(FileNotFoundException e) {
			System.out.println("I/O error: "+e);
		}
		
		int c, count = 0;
		char buf[] = {'x','z'};
		try(PushbackReader f = new PushbackReader(in, 2)) {
			while((c = f.read()) != -1) {
				count++;
				System.out.print((char)c);
				if(count == 5) 
					f.unread(buf);
			}
		} catch (IOException e) {
			System.out.println("I/O error: "+ e);
		}
		
	}

}
