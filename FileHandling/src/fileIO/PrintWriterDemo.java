package fileIO;

import java.io.PrintWriter;

public class PrintWriterDemo {

	public static void main(String[] args) {
		// Note if the auto flush argument for the PrintWriter(OutputStram out, boolean autoFlush) is false
		// we may then have to manually do the flush in order to immediately move the contents of the stream 
		// to their destination.
		PrintWriter pw = new PrintWriter(System.out, false);
		
		pw.println("This is a string.");
		
		int i = -7;
		pw.println(i);
		
		double d = 4.5e-7;
		pw.println(d);

	}

}
