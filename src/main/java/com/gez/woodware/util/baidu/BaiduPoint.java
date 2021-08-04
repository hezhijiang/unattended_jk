package com.gez.woodware.util.baidu;

public class BaiduPoint {

	private String lng;
	private String lat;
 

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getPoint() {
		return lng + "," + lat;
	}

}
