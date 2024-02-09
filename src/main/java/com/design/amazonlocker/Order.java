package com.ooad.amazonlocker;

import java.util.List;

public class Order {

	private Location destLoc;
	private List<Shipment> shipments;

	public Location getDestLoc() {
		return destLoc;
	}

	public List<Shipment> getShipments() {
		return shipments;
	}
}
