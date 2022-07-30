
// This program shows how to read from a file using a channel in pre-JDK 7
// without using explicit read().
package fileNIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class MappedChannelRead2 {

	public static void main(String[] args) {
		
		FileInputStream fin = null;
		FileChannel fchan = null;
		long fSize;
		String dirName = "test.txt";
		try {
			fin = new FileInputStream(dirName);
			
			fchan = fin.getChannel();
			
			fSize = fchan.size();
			
			MappedByteBuffer mBuf = fchan.map(MapMode.READ_ONLY, 0, fSize);
					
			for(int i=0; i<fSize; i++)
				System.out.print((char)mBuf.get());
			System.out.println();
		}
		catch(IOException e) {
			System.out.println("I/O error: "+e);
		}
		finally {
			if(fin!=null)
				// close the file
				try {
					fin.close();
					System.out.println("FileInputStream for file: "+dirName+" closed!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			if(fchan!=null)
				// close the channel
				try {
					fchan.close();
					System.out.println("FileChannel for file: "+dirName+" closed!");
				} catch (IOException e) {
					e.printStackTrace();
				} 
		}
		
	}

}
