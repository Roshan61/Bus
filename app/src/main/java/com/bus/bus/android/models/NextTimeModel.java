package com.bus.bus.android.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class NextTimeModel{

	@SerializedName("times")
	private List<TimesItem> times;

	@SerializedName("pattern")
	private Pattern pattern;

	public void setTimes(List<TimesItem> times){
		this.times = times;
	}

	public List<TimesItem> getTimes(){
		return times;
	}

	public void setPattern(Pattern pattern){
		this.pattern = pattern;
	}

	public Pattern getPattern(){
		return pattern;
	}

	@Override
 	public String toString(){
		return 
			"NextTimeModel{" + 
			"times = '" + times + '\'' + 
			",pattern = '" + pattern + '\'' + 
			"}";
		}
}