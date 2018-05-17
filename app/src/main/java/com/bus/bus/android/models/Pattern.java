package com.bus.bus.android.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Pattern{

	@SerializedName("shortDesc")
	private String shortDesc;

	@SerializedName("id")
	private String id;

	@SerializedName("dir")
	private String dir;

	@SerializedName("desc")
	private String desc;

	public void setShortDesc(String shortDesc){
		this.shortDesc = shortDesc;
	}

	public String getShortDesc(){
		return shortDesc;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDir(String dir){
		this.dir = dir;
	}

	public String getDir(){
		return dir;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	@Override
 	public String toString(){
		return 
			"Pattern{" + 
			"shortDesc = '" + shortDesc + '\'' + 
			",id = '" + id + '\'' + 
			",dir = '" + dir + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}
}