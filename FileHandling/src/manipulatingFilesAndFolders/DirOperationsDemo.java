package manipulatingFilesAndFolders;

import java.io.File;

public class DirOperationsDemo {
	
	public void createDirectory(String dirName) {
		
		File folder = new File(dirName);
		
		if(folder.exists()) {
			System.out.println("Folder already exists!");
			return;
		}
		else {
			if(folder.mkdirs()) {
				System.out.println("Folder created!");
			}
			else {
				System.out.println("Folder could not be created!");
			}
		}
	}
	
	public void listDirectoryContents(String dirName) {
		
		File folder = new File(dirName);
		int entriesDeleted=0, noOfEntries=0;
		if(folder.exists()) {
			for(String entry: folder.list()) {
				System.out.println(entry);
				noOfEntries++;
//				System.out.println(folder.getAbsolutePath());
				File file = new File(folder.getAbsolutePath()+"\\"+entry);
				if(file.delete()) {
					entriesDeleted++;
				}
			}
			if(noOfEntries==entriesDeleted) {
				System.out.println("Directory emptied!");
			}
			else {
				System.out.println("Directory could not be emptied!");
			}
		}
		else {
			System.out.println("Folder doesn't exists!");
		}
	}

	public static void main(String[] args) {
		
		DirOperationsDemo fm = new DirOperationsDemo();
		
		String dirName = "src\\screenshots\\";
		
//		fm.createDirectory(dirName);
		
		fm.listDirectoryContents(dirName);

	}

}
