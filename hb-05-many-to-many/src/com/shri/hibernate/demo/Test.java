package com.shri.hibernate.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		String emailIDString = "";
		String[] emailIDs = emailIDString.split(",");
		System.out.println("emailIDs "+emailIDs.length);
		List<String> emailIDList = new ArrayList<>(Arrays.asList(emailIDs));
		System.out.println("emailIDList |"+emailIDList+"|");
		System.out.println("sizeof emailIDList "+emailIDList.size());
		emailIDList.remove("");
		System.out.println("emailIDList |"+emailIDList+"|");
		System.out.println("sizeof emailIDList "+emailIDList.size());
		emailIDs = emailIDList.toArray(new String[0]);
		System.out.println("emailIDs "+emailIDs.length);
		if(emailIDs[0].equals("")) {
			System.out.println("true");
		}

	}

}
