import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PersonFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		
//		if(file.isDirectory()) return true;
		
		String name = file.getName();
		
		String fileExtension = Utils.getFileExtension(name);
		
		if(fileExtension == null) return false;
		
		if(fileExtension.equals("txt")) return true;
		
		return false;
	}

	@Override
	public String getDescription() {
		return "Person database files (*.per)";
	}
	
}
