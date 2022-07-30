package fileIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationDemo {

	public static void main(String[] args) {
		
		// Object Serialization.
		
		try(ObjectOutputStream objOStrm = new ObjectOutputStream(new FileOutputStream("serial.txt"))) {
			MyClass object1 = new MyClass("Hello", -5, 1.2e10);
			System.out.println("object1: "+object1);
			
			objOStrm.writeObject(object1);
		}
		catch (IOException e) {
			System.out.println("I/O error: "+e);
		}

		// Object De-Serialization.
		
		try(ObjectInputStream objIStrm = new ObjectInputStream(new FileInputStream("serial.txt"))) {
			MyClass object2;
			object2 = (MyClass)objIStrm.readObject();
			
			
			System.out.println("object2: "+object2);
		}
		catch (IOException e) {
			System.out.println("I/O error: "+e);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
class MyClass implements Serializable {
	String s;
	int i;
	double d;
	
	public MyClass(String s, int i, double d) {
		this.s = s;
		this.i = i;
		this.d = d;
	}
	
	public String toString() {
		return "s = " + s + "; i = " + i +"; d = " + d;
	}
}
