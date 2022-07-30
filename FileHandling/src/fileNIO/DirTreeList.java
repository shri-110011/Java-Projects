package fileNIO;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

class MyFilVisitor extends SimpleFileVisitor<Path> {
	
	int count;

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attribs) throws IOException {
//		System.out.println("#"+dir);
//		System.out.println(Paths.get("D:\\Java EE Workspace\\Learn-Java\\FileHandling\\src"));
//		System.out.println(dir.endsWith(
//				Paths.get("D:\\Java EE Workspace\\Learn-Java\\FileHandling\\src")));
		if(dir.endsWith("D:\\Java EE Workspace\\Learn-Java\\FileHandling\\src")) {
			System.out.println("##"+dir);
			return FileVisitResult.CONTINUE;
		}
		else
			return FileVisitResult.SKIP_SUBTREE;
		
//		return FileVisitResult.CONTINUE;
	}
	
//	@Override
//	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//		System.out.println("@"+dir);
//		return FileVisitResult.TERMINATE;
//	}
	
	@Override
	public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
		if(path.getFileName().endsWith("FileManipulator.java")) {
			System.out.println(path);
			count++;
		}
		return FileVisitResult.CONTINUE;
	}
	
}
public class DirTreeList {

	public static void main(String[] args) {
		
		String dirName = "D:\\Java EE Workspace\\Learn-Java\\FileHandling\\src";
		
		System.out.println("Directory tree starting with: "+dirName+" :");
		
		MyFilVisitor myFilVisitor = new MyFilVisitor();
		try {
			Files.walkFileTree(Paths.get(dirName), myFilVisitor);
			System.out.println("Total files count: "+myFilVisitor.count);
		} catch (IOException e) {
			System.out.println("I/O error: "+e);
		}
		

	}

}
