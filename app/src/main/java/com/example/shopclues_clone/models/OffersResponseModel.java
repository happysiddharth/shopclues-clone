package com.example.shopclues_clone.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class OffersResponseModel implements Serializable {

	@SerializedName("image")
	private String image;

	@SerializedName("price_before")
	private int priceBefore;

	@SerializedName("price")
	private int price;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("category")
	private String category;

	@SerializedName("off")
	private int off;

	public String getImage(){
		return image;
	}

	public int getPriceBefore(){
		return priceBefore;
	}

	public int getPrice(){
		return price;
	}

	public String getDescription(){
		return description;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getCategory(){
		return category;
	}

	public int getOff(){
		return off;
	}
}