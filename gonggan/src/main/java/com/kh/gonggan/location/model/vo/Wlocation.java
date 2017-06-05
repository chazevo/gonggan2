package com.kh.gonggan.location.model.vo;

public class Wlocation {
	
	private String city;
	private String lat;
	private String lon;
	
	public Wlocation(){}
	
	public Wlocation(String city, String lat, String lon) {
		super();
		this.city = city;
		this.lat = lat;
		this.lon = lon;
	}



	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	
	
}
