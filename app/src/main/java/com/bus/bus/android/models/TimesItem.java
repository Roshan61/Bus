package com.bus.bus.android.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class TimesItem{

	@SerializedName("scheduledArrival")
	private String scheduledArrival;

	@SerializedName("realtimeArrival")
	private String realtimeArrival;

	@SerializedName("realtime")
	private boolean realtime;

	@SerializedName("realtimeDeparture")
	private String realtimeDeparture;

	@SerializedName("serviceDay")
	private String serviceDay;

	@SerializedName("arrivalDelay")
	private String arrivalDelay;

	@SerializedName("stopId")
	private String stopId;

	@SerializedName("scheduledDeparture")
	private String scheduledDeparture;

	@SerializedName("tripId")
	private String tripId;

	@SerializedName("departureDelay")
	private String departureDelay;

	@SerializedName("stopName")
	private String stopName;

	@SerializedName("timepoint")
	private boolean timepoint;

	public void setScheduledArrival(String scheduledArrival){
		this.scheduledArrival = scheduledArrival;
	}

	public String getScheduledArrival(){
		return scheduledArrival;
	}

	public void setRealtimeArrival(String realtimeArrival){
		this.realtimeArrival = realtimeArrival;
	}

	public String getRealtimeArrival(){
		return realtimeArrival;
	}

	public void setRealtime(boolean realtime){
		this.realtime = realtime;
	}

	public boolean isRealtime(){
		return realtime;
	}

	public void setRealtimeDeparture(String realtimeDeparture){
		this.realtimeDeparture = realtimeDeparture;
	}

	public String getRealtimeDeparture(){
		return realtimeDeparture;
	}

	public void setServiceDay(String serviceDay){
		this.serviceDay = serviceDay;
	}

	public String getServiceDay(){
		return serviceDay;
	}

	public void setArrivalDelay(String arrivalDelay){
		this.arrivalDelay = arrivalDelay;
	}

	public String getArrivalDelay(){
		return arrivalDelay;
	}

	public void setStopId(String stopId){
		this.stopId = stopId;
	}

	public String getStopId(){
		return stopId;
	}

	public void setScheduledDeparture(String scheduledDeparture){
		this.scheduledDeparture = scheduledDeparture;
	}

	public String getScheduledDeparture(){
		return scheduledDeparture;
	}

	public void setTripId(String tripId){
		this.tripId = tripId;
	}

	public String getTripId(){
		return tripId;
	}

	public void setDepartureDelay(String departureDelay){
		this.departureDelay = departureDelay;
	}

	public String getDepartureDelay(){
		return departureDelay;
	}

	public void setStopName(String stopName){
		this.stopName = stopName;
	}

	public String getStopName(){
		return stopName;
	}

	public void setTimepoint(boolean timepoint){
		this.timepoint = timepoint;
	}

	public boolean isTimepoint(){
		return timepoint;
	}

	@Override
 	public String toString(){
		return 
			"TimesItem{" + 
			"scheduledArrival = '" + scheduledArrival + '\'' + 
			",realtimeArrival = '" + realtimeArrival + '\'' + 
			",realtime = '" + realtime + '\'' + 
			",realtimeDeparture = '" + realtimeDeparture + '\'' + 
			",serviceDay = '" + serviceDay + '\'' + 
			",arrivalDelay = '" + arrivalDelay + '\'' + 
			",stopId = '" + stopId + '\'' + 
			",scheduledDeparture = '" + scheduledDeparture + '\'' + 
			",tripId = '" + tripId + '\'' + 
			",departureDelay = '" + departureDelay + '\'' + 
			",stopName = '" + stopName + '\'' + 
			",timepoint = '" + timepoint + '\'' + 
			"}";
		}
}