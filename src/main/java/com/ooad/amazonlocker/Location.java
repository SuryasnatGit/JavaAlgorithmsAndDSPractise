package com.ooad.amazonlocker;

public class Location {

	private int longitude;
	private int latitude;
	private double distance;

	public int getLongitude() {
		return longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
