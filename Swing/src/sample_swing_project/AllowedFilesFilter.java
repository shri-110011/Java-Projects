package sample_swing_project;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.util.ArrayList;

public class AllowedFilesFilter extends FileFilter {

	ArrayList<String> allowedFileExtensions = new ArrayList<String>();
	
	public AllowedFilesFilter() {
		allowedFileExtensions.add("txt");
		allowedFileExtensions.add("java");
		allowedFileExtensions.add("c");
		allowedFileExtensions.add("cpp");
		allowedFileExtensions.add("m");
	}
	
	@Override
	public boolean accept(File pathname) {
		String fileName = pathname.getName();
		
		String ext = Utils.getFileExtension(fileName);
		
//		System.out.println("ext: "+ext);
			
		if(allowedFileExtensions.contains(ext.toLowerCase())) return true;
		
		return false;
	}

	@Override
	public String getDescription() {
		return "Allowed Files(.txt, .c, .cpp, .java)";
	}

}
