package com.shri.swapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.shri.swapi.responseModels.Movie;
import com.shri.swapi.responseModels.Movies;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetStarWarFilms {
	
	@Test(enabled=false)
	public void getAllStarWarFilms() {
		Response response = null;
		
		// Sending GET request
		response = RestAssured.get("https://swapi.dev/api/films");

		// Printing the response body
		System.out.println("Response: " + response.asPrettyString());

		// Parsing the response to extract specific data
//		String str= response.jsonPath().getString("results[0]").toString();
//		System.out.println(str);
//		
//		String result= response.jsonPath().getJsonObject("results.find{it.title.equals('The Empire Strikes Back')}").toString();
//		System.out.println(result);
		
//		System.out.println("-----------------------------------");
//		
//		RestAssured.baseURI = ("https://swapi.dev/api/films");
//		RequestSpecification requestSpec = RestAssured.given();
		
		
//		requestSpec.log().everything();
		
//		System.out.println("Getting movies");
//		response = requestSpec.get();
//		System.out.println("Response status code: " + response.statusCode());
		
//		Gson gson = new Gson();
		
		// De-serializing json-array represented as string into a POJO.
//		Movies movies2 = gson.fromJson(response.getBody().asString(), Movies.class);
//		System.out.println(movies2);
		
		// Creating a json object
//		JSONObject obj = new JSONObject();
//		obj.put("opening_crawl", "Crawl 1");
//		obj.put("release_date", "25/5/2023");
//		obj.put("title", "Title 1");
//		System.out.println(obj.toString());
		
		// De-serializing json-object represented as string into a POJO.
//		Movie movie = gson.fromJson(obj.toString(), Movie.class);
//		System.out.println(movie);
		
//		JSONArray arr = new JSONArray();
//		arr.put(obj);
//		System.out.println(arr.toString());
		
//		Type type = new TypeToken<List<Movie>>() {}.getType();
//		List<Movie> movies = gson.fromJson(arr.toString(), type);
//		System.out.println(movies);
		
//		JSONObject obj2 = new JSONObject();
//		obj2.put("results", arr);
		
//		Movies movies3 = gson.fromJson(obj2.toString(), Movies.class);
//		System.out.println(movies3);
		
	}
	
	@Test(enabled = false)
	public void getStarWarFilm() {
		Response response = null;
		
		// Sending GET request
		response = RestAssured.get("https://swapi.dev/api/films/{filmId}", 3);

		// Printing the response body
		System.out.println("Response: " + response.asPrettyString());
		
	}
	
	@Test(enabled = false)
	public void getReleaseDateOfAStarWarFilm() {
		Response response = null;
		
		// Sending GET request
		response = RestAssured.get("https://swapi.dev/api/films");

		// Printing the response body
//		System.out.println("Response: " + response.asPrettyString());
		
		String title = "The Empire Strikes Back";
		
		// Parsing the response to extract specific data
		String releaseDate= response.jsonPath().getString("results.find{it.title.equals('" + title +"')}.release_date").toString();
		System.out.println(releaseDate);
	}
	
	@Test(enabled = false)
	public void getBasicDetailsOfAStarWarFilmFromLocal() {
		Response response = null;
		
		// Sending GET request
//		response = RestAssured.get("https://swapi.dev/api/films");
		
		File file = new File("src/test/java/resources/response.json");
		System.out.println(file.exists());
		
		try {
			FileInputStream fin = new FileInputStream(file);

			// ObjectMapper is a class from com.fasterxml.jackson.databind package.
			ObjectMapper objectMapper = new ObjectMapper();
			// Here we map a simple json object to a POJO.
//			Movie movie = objectMapper.readValue(fin, Movie.class);
//			System.out.println(movie);
			
			// Here we map a nested json array to a POJO.
			Movies movies = objectMapper.readValue(fin, Movies.class);
			System.out.println(movies.getResults());
			
			// Here we map a json array to a POJO.
//			List<Movie> movies = objectMapper.readValue(fin, new TypeReference<List<Movie>>() {});
//			System.out.println(movies);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test(enabled = true)
	public void getBasicDetailsOfAStarWarFilmFromRemote() {
		Response response = null;
		
		// Sending GET request
		response = RestAssured.get("https://swapi.dev/api/films");
		
		// Here we use just the RestAssured api to de-serialize the json string into a POJO.  
		Movies movies = response.jsonPath().getObject("", Movies.class);
		System.out.println(movies);
	}

}
