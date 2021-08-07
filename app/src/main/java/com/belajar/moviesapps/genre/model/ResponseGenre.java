package com.belajar.moviesapps.genre.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGenre{

	@SerializedName("genres")
	private List<GenresItem> genres;

	public void setGenres(List<GenresItem> genres){
		this.genres = genres;
	}

	public List<GenresItem> getGenres(){
		return genres;
	}

	@Override
 	public String toString(){
		return 
			"ResponseGenre{" + 
			"genres = '" + genres + '\'' + 
			"}";
		}
}