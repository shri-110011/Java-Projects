package fileIOBasics;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyCharacters {

	public static void main(String[] args) throws IOException {
		
//		All character stream classes are descended from Reader and Writer.
		FileReader reader = null;
		FileWriter writer = null;
		
		try {
			reader = new FileReader("src/fileIOBasics/xanadu.txt");
			writer = new FileWriter("src/fileIOBasics/outagain2.txt");
			
//			CopyCharacters is very similar to CopyBytes. The most important difference is that 
//			CopyCharacters uses FileReader and FileWriter for input and output in place of 
//			FileInputStream and FileOutputStream. Notice that both CopyBytes and CopyCharacters use an 
//			int variable to read to and write from. However, in CopyCharacters, the int variable holds 
//			a character value in its last 16 bits; in CopyBytes, the int variable holds a byte value in 
//			its last 8 bits.
			int c,i=0;
			while((c=reader.read()) != -1) {
				if(i==0) {
					System.out.println(c);
					i++;
				}
				writer.write(c);
			}
		}
		finally {
			if(reader!=null) {
				reader.close();
			}
			if(writer!=null) {
				writer.close();
			}
		}
	}

}
