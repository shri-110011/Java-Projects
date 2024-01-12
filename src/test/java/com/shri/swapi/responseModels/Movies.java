package com.shri.swapi.responseModels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movies {
	
	private List<Movie> results;

	public List<Movie> getResults() {
		return results;
	}

	public void setResults(List<Movie> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "Movies [results=" + results + "]";
	}
	
}
