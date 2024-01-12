package stream_api_01;

import java.util.ArrayList;
import java.util.stream.Stream;

/* Use map() to create a stream that contains only selected aspects of the original stream.*/
class NamePhoneEmail {
	String name;
	String phonenum;
	String email;
	
	public NamePhoneEmail(String name, String phonenum, String email) {
		this.name = name;
		this.phonenum = phonenum;
		this.email = email;
	}
}

class NamePhone {
	
	String name;
	String phonenum;
	
	public NamePhone(String name, String phonenum) {
		this.name = name;
		this.phonenum = phonenum;
	}
	
}

public class StreamDemo5 {

	public static void main(String[] args) {
		
		// A list of names, phone numbers and e-mail addresses.
		ArrayList<NamePhoneEmail> myList = new ArrayList<>();
		myList.add(new NamePhoneEmail("Larry", "555-5555", "Larry@HerbSchildt.com"));
		myList.add(new NamePhoneEmail("James", "555-4444", "James@HerbSchildt.com"));
		myList.add(new NamePhoneEmail("Mary", "555-3333", "Mary@HerbSchildt.com"));
		
		System.out.println("Original values in myList: ");
		myList.forEach(el -> {
			System.out.println(el.name + " " + el.phonenum + " " + el.email);
		});
		System.out.println();
		
		// Map just the names and phone numbers to a new stream.
		Stream<NamePhone> nameAndPhone  = myList.stream().map(el -> new NamePhone(el.name, el.phonenum));
		System.out.println("List of names and phone numbers: ");
		nameAndPhone.forEach(el -> {
			System.out.println(el.name + " " + el.phonenum);
		});
	}

}
