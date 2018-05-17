package com.bus.bus.android.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class LinesListModel{

	@SerializedName("mode")
	private String mode;

	@SerializedName("color")
	private String color;

	@SerializedName("id")
	private String id;

	@SerializedName("shortName")
	private String shortName;

	@SerializedName("type")
	private String type;

	@SerializedName("textColor")
	private String textColor;

	@SerializedName("longName")
	private String longName;

	public void setMode(String mode){
		this.mode = mode;
	}

	public String getMode(){
		return mode;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setShortName(String shortName){
		this.shortName = shortName;
	}

	public String getShortName(){
		return shortName;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setTextColor(String textColor){
		this.textColor = textColor;
	}

	public String getTextColor(){
		return textColor;
	}

	public void setLongName(String longName){
		this.longName = longName;
	}

	public String getLongName(){
		return longName;
	}

	@Override
 	public String toString(){
		return 
			"LinesListModel{" + 
			"mode = '" + mode + '\'' + 
			",color = '" + color + '\'' + 
			",id = '" + id + '\'' + 
			",shortName = '" + shortName + '\'' + 
			",type = '" + type + '\'' + 
			",textColor = '" + textColor + '\'' + 
			",longName = '" + longName + '\'' + 
			"}";
		}
}