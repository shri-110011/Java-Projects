package fileIO;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {

	public static void main(String[] args) {
		try(RandomAccessFile raf = new RandomAccessFile("test.txt", "rw")) {
			int c;
			// Sets the file-pointer offset, measured from the beginning of this file, at which the
			// next read or write occurs.
			raf.seek(2);
			
			raf.setLength(5);
			
			// Returns the current file pointer position in the form of offset from the beginning of the file, in bytes.
			System.out.println(raf.getFilePointer());
			while((c = raf.read()) != -1) {
				System.out.print((char)c);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
