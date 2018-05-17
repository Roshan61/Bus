package com.bus.bus.android.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class StationModel{

	@SerializedName("code")
	private String code;

	@SerializedName("city")
	private String city;

	@SerializedName("name")
	private String name;

	@SerializedName("lon")
	private double lon;

	@SerializedName("lat")
	private double lat;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLon(double lon){
		this.lon = lon;
	}

	public double getLon(){
		return lon;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"StationModel{" + 
			"code = '" + code + '\'' + 
			",city = '" + city + '\'' + 
			",name = '" + name + '\'' + 
			",lon = '" + lon + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}