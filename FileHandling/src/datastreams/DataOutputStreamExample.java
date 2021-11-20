package datastreams;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamExample {
	static final String dataFile = "src/datastreams/invoicedata";

	static final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
	static final int[] units = { 12, 8, 13, 29, 50 };
	static final String[] descs = {
	    "Java T-shirt",
	    "Java Mug",
	    "Duke Juggling Dolls",
	    "Java Pin",
	    "Java Key Chain"
	};

	public static void main(String[] args) throws IOException {
		DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));
		for(int i=0; i<prices.length; i++) {
			dout.writeDouble(prices[i]);
			dout.writeInt(units[i]);
			dout.writeUTF(descs[i]);
		}
//		flush() is used in case the buffer is not full yet and we want to write the contents of the buffer before it is full
//		dout.flush();
		dout.close();
		
	}

}
