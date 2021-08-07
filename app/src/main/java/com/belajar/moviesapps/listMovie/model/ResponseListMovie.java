package com.belajar.moviesapps.listMovie.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseListMovie{

	@SerializedName("item_count")
	private int itemCount;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("favorite_count")
	private int favoriteCount;

	@SerializedName("id")
	private String id;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("items")
	private List<ItemsItem> items;

	@SerializedName("iso_639_1")
	private String iso6391;

	@SerializedName("poster_path")
	private String posterPath;

	public void setItemCount(int itemCount){
		this.itemCount = itemCount;
	}

	public int getItemCount(){
		return itemCount;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setFavoriteCount(int favoriteCount){
		this.favoriteCount = favoriteCount;
	}

	public int getFavoriteCount(){
		return favoriteCount;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	public void setIso6391(String iso6391){
		this.iso6391 = iso6391;
	}

	public String getIso6391(){
		return iso6391;
	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}

	@Override
 	public String toString(){
		return 
			"ResponseListMovie{" + 
			"item_count = '" + itemCount + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",favorite_count = '" + favoriteCount + '\'' + 
			",id = '" + id + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",items = '" + items + '\'' + 
			",iso_639_1 = '" + iso6391 + '\'' + 
			",poster_path = '" + posterPath + '\'' + 
			"}";
		}
}