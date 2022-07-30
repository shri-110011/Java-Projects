package sample_swing_project;

public class Utils {
	
	public static String getFileExtension(String fileName) {
		if(fileName == null) return null;
		
		int pointIndex = fileName.lastIndexOf(".");
		
		if(pointIndex == -1 || pointIndex == fileName.length()) return null;
		
		return fileName.substring(pointIndex+1);
	}

}
