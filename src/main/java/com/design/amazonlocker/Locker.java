package com.design.amazonlocker;

public class Locker {

	private int lockerId;
	private LockerType type;
	private LockerStatus status;
	private Location lockerLoc;

	public Locker() {
		// TODO Auto-generated constructor stub
	}

	public int getLockerId() {
		return lockerId;
	}

	public LockerStatus getStatus() {
		return status;
	}

	public LockerType getType() {
		return type;
	}

	public Location getLockerLoc() {
		return lockerLoc;
	}
}
