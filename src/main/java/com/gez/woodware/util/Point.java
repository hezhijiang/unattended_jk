package com.gez.woodware.util;

public class Point {
	private double log;
	private double lat;
	private static final double SELF_SERVICE_RADIUS = 0.500;// 经纬度差别在这个范围内，表示只取这个范围的自助点。大概是50公里

	public Point(double log, double lat) {
		super();
		this.log = log;
		this.lat = lat;
	}

	public double getLog() {
		return log;
	}

	public void setLog(double log) {
		this.log = log;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public Point getTopRight() {
		return new Point(log + SELF_SERVICE_RADIUS, lat + SELF_SERVICE_RADIUS);
	}

	public Point getButtomLeft() {
		return new Point(log - SELF_SERVICE_RADIUS, lat - SELF_SERVICE_RADIUS);
	}

}

