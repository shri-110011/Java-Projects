package fileNIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class MappedChannelWrite2 {

	public static void main(String[] args) {
		RandomAccessFile fout = null;
		FileChannel fchan = null;
		String dirName ="test.txt";
		
		try {
			fout = new RandomAccessFile(dirName, "rw");
			
			fchan = fout.getChannel();
			
			MappedByteBuffer mBuf = fchan.map(MapMode.READ_WRITE, 0, 26);
			
			for(int i=0; i<26; i++)
				mBuf.put((byte)('A'+i));
			
			System.out.println("Write complete!");
		}
		catch(IOException e) {
			System.out.println("I/O error: "+e);
		}
		finally {
			try {
				if(fout != null) {
					fout.close();
					System.out.println("FileOutputStream for file: "+dirName+" closed!");
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			try {
				if(fchan != null) {
					fchan.close();
					System.out.println("FileChannel for file: "+dirName+" closed!");
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

}
