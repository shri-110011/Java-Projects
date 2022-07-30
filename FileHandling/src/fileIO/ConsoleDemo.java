package fileIO;

import java.io.Console;

public class ConsoleDemo {

	public static void main(String[] args) {
		String str;
		Console con;
		
		// Obtain a reference to the console.
		con = System.console();
		
		if(con == null) return;
		
		// Read a string and then display it.
		str = con.readLine("Enter a string: ");
		
		con.printf("Here is your string: %s\n", str);
		
		char pwd[] = con.readPassword("Enter password: ");
		con.printf("Password: %s", new String(pwd));
		
	}

}
