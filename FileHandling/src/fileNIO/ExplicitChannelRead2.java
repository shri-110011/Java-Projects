
// This program shows how to read from a file using a channel in pre-JDK 7.

package fileNIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ExplicitChannelRead2 {

	public static void main(String[] args) {
		
		FileInputStream fin = null;
		FileChannel fchan = null;
		int count;
		String dirName = "test.txt";
		try {
			fin = new FileInputStream(dirName);
			
			fchan = fin.getChannel();
			
			ByteBuffer mBuf = ByteBuffer.allocate(128);
		
			while((count = fchan.read(mBuf))!=-1) {
				mBuf.rewind();
				for(int i=0; i<count; i++)
					System.out.print((char)mBuf.get());
			}
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
