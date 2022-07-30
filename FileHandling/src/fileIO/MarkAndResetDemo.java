package fileIO;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MarkAndResetDemo {

	public static void main(String[] args) {
		String s = "Today is Sunday!";
		byte buf[] = s.getBytes();
		
		System.out.println("s: "+s);
		ByteArrayInputStream in = new ByteArrayInputStream(buf);
		int c, count = 0;
		
		try(BufferedInputStream f = new BufferedInputStream(in, 8)) {
			while((c=f.read()) != -1) {
				System.out.print((char)c);
				count++;
				
				if(count==2) f.mark(8);
				
				if(count==10) f.reset();
				
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
/*
For internal buffer of length n >= marklimit:

If markpos = k where k is the index of the internal buffer(buf) and reset 
is not called even after (n-k) bytes have been read from the buf since 
mark() was last called then on reading the next input from the input stream:

Bytes from index position k to n-1 of buf are written at index 
position 0 to n-k-1 and at the index ranging from n-k to n-1 new data is 
written and the value of pos(the index position of the next byte to be 
read in buf) becomes n-k+1 since by now one byte from the newly added data 
has been read.

For internal buffer of length n < marklimit:

A new buffer of length marklimit will be created after n+k bytes have been 
read and (n+k+1)th byte is about to be read.
 */
