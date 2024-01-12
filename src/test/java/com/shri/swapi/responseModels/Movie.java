package com.shri.swapi.responseModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
	
	private String release_date;
	private String title;
	
//	public String getRelease_date() {
//		return release_date;
//	}
	
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	
//	public String getTitle() {
//		return title;
//	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Movie [release_date=" + release_date + ", title=" + title + "]";
	}
	
}
