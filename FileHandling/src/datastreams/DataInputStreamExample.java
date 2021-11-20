package datastreams;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class DataInputStreamExample {
	static final String dataFile = "src/datastreams/invoicedata";
	
	public static void main(String[] args) throws IOException  {
		DataInputStream din = new DataInputStream(new BufferedInputStream(
				new FileInputStream(dataFile)));
		double price;
		int unit;
		String desc;
		double total = 0.0;
		try {
			while(true) {
				price = din.readDouble();
				unit = din.readInt();
				desc = din.readUTF();
				
				System.out.format("You ordered %d" + " units of %s at $%.2f%n",
			            unit, desc, price);
			}
		}
		catch(EOFException e) {
			din.close();
		}
	}

}
