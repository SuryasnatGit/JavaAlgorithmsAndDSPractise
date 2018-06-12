package com.oops.parkinglot;

public class Bus extends Vehicle {

	public Bus() {
		this.spotsNeeded = 5;
		this.size = VehicleSize.large;
	}

	@Override
	public boolean canFitinSpot(ParkingSpot spot) {
		// TODO Auto-generated method stub
		return false;
	}

}
