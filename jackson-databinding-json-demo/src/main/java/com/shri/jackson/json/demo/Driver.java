package com.shri.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {
	
	public static void main(String[] args) {
		
		try {
			
			// create an object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// read JSON file and map/convert into POJO
			// data/sample-lite.json
			Student student = mapper.readValue(new File("data/sample-full.json"), Student.class);
			
			// print first name and last name
			System.out.println(student.getFirstName());
			System.out.println(student.getLastName());
			
			// print out address: street and city
			Address address = student.getAddress();
			System.out.println("Street: "+address.getStreet());
			System.out.println("City: "+address.getCity());
			
			// print out languages
			for(String language: student.getLanguages()) {
				System.out.println(language);
			}
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
	}

}
