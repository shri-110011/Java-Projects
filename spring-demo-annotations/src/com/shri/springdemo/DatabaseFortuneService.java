package com.shri.springdemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class DatabaseFortuneService implements FortuneService {
	
	private ArrayList<String> fortunes = new ArrayList<>();
	
	private Random random = new Random();
	
	@PostConstruct
	public void loadFortunes() {
		BufferedReader reader = null;
		System.out.println("Inside DatabaseFortuneService: inside loadFortunes()");
		System.out.println("Getting fortunes from the file fortunes.txt...");
		try {
			reader = new BufferedReader(new FileReader("src/com/shri/springdemo/fortunes.txt"));
			String fortune;
			
			while((fortune=reader.readLine())!=null) {
				fortunes.add(fortune);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

//	@Override
//	public String getFortune() {
//		
//		BufferedReader reader = null;
//		System.out.println("Getting fortunes from the file fortunes.txt...");
//		try {
//			reader = new BufferedReader(new FileReader("src/com/shri/springdemo/fortunes.txt"));
//			String fortune;
//			
//			while((fortune=reader.readLine())!=null) {
//				fortunes.add(fortune);
//			}
//			
//			int index = random.nextInt(fortunes.size());
//			
//			return fortunes.get(index);
//			
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
//		finally {
//			if(reader!=null) {
//				try {
//					reader.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return null;
//	}
	
	public String getFortune() {
		int index = random.nextInt(fortunes.size());
		return fortunes.get(index);
	}

}
