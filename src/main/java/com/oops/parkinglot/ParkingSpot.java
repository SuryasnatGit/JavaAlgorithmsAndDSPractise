package com.oops.parkinglot;

import java.util.logging.Level;

/**
 * The ParkingSpot is implemented by having just a variable which represents the size of the spot.
 * We could have implemented this by having classes for LargeSpot, CompactSpot, and MotorcycleSpot
 * which inherit from ParkingSpot, but this is probably overkilled. The spots probably do not have
 * different behaviors, other than their sizes.
 * 
 * @author surya
 *
 */
public class ParkingSpot {
	private Vehicle vehicle;
	private VehicleSize spotSize;
	private int row;
	private int spotNumber;
	private Level level;

	public ParkingSpot(Level lvl, int r, int n,
                         VehicleSize s)
	{
	}

	public boolean isAvailable() {
		return vehicle == null;
	}

	/* Check if the spot is big enough and is available */
	public boolean canFitVehicle(Vehicle vehicle) {
		return false;
	}

	/* Park vehicle in this spot. */
	public boolean park(Vehicle v) {
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getSpotNumber() {
		return spotNumber;
	}

	/*
	 * Remove vehicle from spot, and notify level that a new spot is available
	 */
	public void removeVehicle() {
	}

}
