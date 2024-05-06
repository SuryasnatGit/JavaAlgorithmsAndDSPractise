package com.design.parkinglot;

import java.util.Date;
import java.util.HashMap;

public class ParkingLotSystem {

	/*
	 * Here are the required enums, data types, and constants:
	 */
	public enum VehicleType {
		CAR, TRUCK, ELECTRIC, VAN, MOTORBIKE
	}

	public enum ParkingSpotType {
		HANDICAPPED, COMPACT, LARGE, MOTORBIKE, ELECTRIC
	}

	public enum AccountStatus {
		ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, UNKNOWN
	}

	public enum ParkingTicketStatus {
		ACTIVE, PAID, LOST
	}

	public class Location {
		private String streetAddress;
		private String city;
		private String state;
		private String zipCode;
		private String country;
	}

	public class Person {
		private String name;
		private Location address;
		private String email;
		private String phone;
	}

	/*
	 * various people that interact with the system
	 */

	// For simplicity, we are not defining getter and setter functions. The reader can
	// assume that all class attributes are private and accessed through their respective
	// public getter methods and modified only through their public methods function.

	public abstract class Account {
		private String userName;
		private String password;
		private AccountStatus status;
		private Person person;

		public boolean resetPassword();
	}

	public class Admin extends Account {
		public boolean addParkingFloor(ParkingFloor floor);

		public boolean addParkingSpot(String floorName, ParkingSpot spot);

		public boolean addParkingDisplayBoard(String floorName, ParkingDisplayBoard displayBoard);

		public boolean addCustomerInfoPanel(String floorName, CustomerInfoPanel infoPanel);

		public boolean addEntrancePanel(EntrancePanel entrancePanel);

		public boolean addExitPanel(ExitPanel exitPanel);
	}

	public class ParkingAttendant extends Account {
		public boolean processTicket(string TicketNumber);
	}

	/*
	 * Parking ticket
	 */
	public class ParkingTicket {

		private int ticketNumber;
		private Date issuedAt;
		private Date paidAt;
		private Date paidAmount;
		private ParkingTicketStatus parkingTicketStatus;

	}

	/*
	 * Parking Rate
	 */
	public class ParkingRate {

	}

	/*
	 * ParkingSpot: Here is the definition of ParkingSpot and all of its children classes:
	 */
	public abstract class ParkingSpot {
		private String number;
		private boolean free;
		private Vehicle vehicle;
		private final ParkingSpotType type;

		public boolean isFree();

		public ParkingSpot(ParkingSpotType type) {
			this.type = type;
		}

		public void assignVehicle(Vehicle vehicle) {
			this.vehicle = vehicle;
			free = false;
		}

		public void removeVehicle() {
			this.vehicle = null;
			free = true;
		}

		public ParkingSpotType getType() {
			return type;
		}

	}

	public class HandicappedSpot extends ParkingSpot {
		public HandicappedSpot() {
			super(ParkingSpotType.HANDICAPPED);
		}
	}

	public class CompactSpot extends ParkingSpot {
		public CompactSpot() {
			super(ParkingSpotType.COMPACT);
		}
	}

	public class LargeSpot extends ParkingSpot {
		public LargeSpot() {
			super(ParkingSpotType.LARGE);
		}
	}

	public class MotorbikeSpot extends ParkingSpot {
		public MotorbikeSpot() {
			super(ParkingSpotType.MOTORBIKE);
		}
	}

	public class ElectricSpot extends ParkingSpot {
		public ElectricSpot() {
			super(ParkingSpotType.ELECTRIC);
		}
	}

	/*
	 * Vehicle: Here is the definition for Vehicle and all of its child classes:
	 */
	public abstract class Vehicle {
		private String licenseNumber;
		private final VehicleType type;
		private ParkingTicket ticket;

		public Vehicle(VehicleType type) {
			this.type = type;
		}

		public void assignTicket(ParkingTicket ticket) {
			this.ticket = ticket;
		}
	}

	public class Car extends Vehicle {
		public Car() {
			super(VehicleType.CAR);
		}
	}

	public class Van extends Vehicle {
		public Van() {
			super(VehicleType.VAN);
		}
	}

	public class Truck extends Vehicle {
		public Truck() {
			super(VehicleType.TRUCK);
		}
	}

	/*
	 * CustomerInfoPortal
	 */
	public class CustomerInfoPortal {

	}

	// Similarly we can define classes for Motorcycle and Electric vehicles

	/*
	 * ParkingFloor: This class encapsulates a parking floor:
	 * 
	 */
	public class ParkingFloor {
		private String name;
		private HashMap<String, HandicappedSpot> handicappedSpots;
		private HashMap<String, CompactSpot> compactSpots;
		private HashMap<String, LargeSpot> largeSpots;
		private HashMap<String, MotorbikeSpot> motorbikeSpots;
		private HashMap<String, ElectricSpot> electricSpots;
		private HashMap<String, CustomerInfoPortal> infoPortals;
		private ParkingDisplayBoard displayBoard;

		public ParkingFloor(String name) {
			this.name = name;
		}

		public void addParkingSpot(ParkingSpot spot) {
			switch (spot.getType()) {
			case HANDICAPPED:
				handicappedSpots.put(spot.getNumber(), spot);
				break;
			case COMPACT:
				compactSpots.put(spot.getNumber(), spot);
				break;
			case LARGE:
				largeSpots.put(spot.getNumber(), spot);
				break;
			case MOTORBIKE:
				motorbikeSpots.put(spot.getNumber(), spot);
				break;
			case ELECTRIC:
				electricSpots.put(spot.getNumber(), spot);
				break;
			default:
				System.out.println("Wrong parking spot type!");
			}
		}

		public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
			spot.assignVehicle(vehicle);
			switch (spot.getType()) {
			case HANDICAPPED:
				updateDisplayBoardForHandicapped(spot);
				break;
			case COMPACT:
				updateDisplayBoardForCompact(spot);
				break;
			case LARGE:
				updateDisplayBoardForLarge(spot);
				break;
			case MOTORBIKE:
				updateDisplayBoardForMotorbike(spot);
				break;
			case ELECTRIC:
				updateDisplayBoardForElectric(spot);
				break;
			default:
				System.out.println("Wrong parking spot type!");
			}
		}

		private void updateDisplayBoardForHandicapped(ParkingSpot spot) {
			if (this.displayBoard.getHandicappedFreeSpot().getNumber() == spot.getNumber()) {
				// find another free handicapped parking and assign to displayBoard
				for (String key : handicappedSpots.keySet()) {
					if (handicappedSpots.get(key).isFree()) {
						this.displayBoard.setHandicappedFreeSpot(handicappedSpots.get(key));
					}
				}
				this.displayBoard.showEmptySpotNumber();
			}
		}

		private void updateDisplayBoardForCompact(ParkingSpot spot) {
			if (this.displayBoard.getCompactFreeSpot().getNumber() == spot.getNumber()) {
				// find another free compact parking and assign to displayBoard
				for (String key : compactSpots.keySet()) {
					if (compactSpots.get(key).isFree()) {
						this.displayBoard.setCompactFreeSpot(compactSpots.get(key));
					}
				}
				this.displayBoard.showEmptySpotNumber();
			}
		}

		private void updateDisplayBoardForLarge(ParkingSpot spot) {
			// TODO Auto-generated method stub

		}

		private void updateDisplayBoardForMotorbike(ParkingSpot spot) {
			// TODO Auto-generated method stub

		}

		private void updateDisplayBoardForElectric(ParkingSpot spot) {
			// TODO Auto-generated method stub

		}

		public void freeSpot(ParkingSpot spot) {
			spot.removeVehicle();
			switch (spot.getType()) {
			case HANDICAPPED:
				freeHandicappedSpotCount++;
				break;
			case COMPACT:
				freeCompactSpotCount++;
				break;
			case LARGE:
				freeLargeSpotCount++;
				break;
			case MOTORBIKE:
				freeMotorbikeSpotCount++;
				break;
			case ELECTRIC:
				freeElectricSpotCount++;
				break;
			default:
				System.out.println("Wrong parking spot type!");
			}
		}
	}

	/*
	 * ParkingDisplayBoard: This class encapsulates a parking display board:
	 */
	public class ParkingDisplayBoard {

		private String id;
		private HandicappedSpot handicappedFreeSpot;
		private CompactSpot compactFreeSpot;
		private LargeSpot largeFreeSpot;
		private MotorbikeSpot motorbikeFreeSpot;
		private ElectricSpot electricFreeSpot;

		public void showEmptySpotNumber() {
			String message = "";
			if (handicappedFreeSpot.isFree()) {
				message += "Free Handicapped: " + handicappedFreeSpot.getNumber();
			} else {
				message += "Handicapped is full";
			}
			message += System.lineSeparator();

			if (compactFreeSpot.isFree()) {
				message += "Free Compact: " + compactFreeSpot.getNumber();
			} else {
				message += "Compact is full";
			}
			message += System.lineSeparator();

			if (largeFreeSpot.isFree()) {
				message += "Free Large: " + largeFreeSpot.getNumber();
			} else {
				message += "Large is full";
			}
			message += System.lineSeparator();

			if (motorbikeFreeSpot.isFree()) {
				message += "Free Motorbike: " + motorbikeFreeSpot.getNumber();
			} else {
				message += "Motorbike is full";
			}
			message += System.lineSeparator();

			if (electricFreeSpot.isFree()) {
				message += "Free Electric: " + electricFreeSpot.getNumber();
			} else {
				message += "Electric is full";
			}

			System.out.println(message);
		}
	}

	/*
	 * Entrance and Exit Panels
	 */
	public class EntrancePanel {

	}

	public class ExitPanel {

	}

	/*
	 * ParkingLot: Our system will have only one object of this class. This can be enforced by using the Singleton
	 * pattern. In software engineering, the singleton pattern is a software design pattern that restricts the
	 * instantiation of a class to only one object.
	 */
	public class ParkingLot {

		private String name;
		private Location address;
		private ParkingRate parkingRate;

		private int compactSpotCount;
		private int largeSpotCount;
		private int motorbikeSpotCount;
		private int electricSpotCount;

		private final int maxCompactCount;
		private final int maxLargeCount;
		private final int maxMotorbikeCount;
		private final int maxElectricCount;

		private HashMap<String, EntrancePanel> entrancePanels;
		private HashMap<String, ExitPanel> exitPanels;
		private HashMap<String, ParkingFloor> parkingFloors;

		// all active parking tickets, identified by their ticketNumber
		private HashMap<String, ParkingTicket> activeTickets;

		// singleton ParkingLot to ensure only one object of ParkingLot in the system,
		// all entrance panels will use this object to create new parking ticket: getNewParkingTicket(),
		// similarly exit panels will also use this object to close parking tickets
		private static ParkingLotSystem parkingLot = null;

		// private constructor to restrict for singleton
		private ParkingLot() {
			// 1. initialize variables: read name, address and parkingRate from database
			// 2. initialize parking floors: read the parking floor map from database,
			// this map should tell how many parking spots are there on each floor. This
			// should also initialize max spot counts too.
			// 3. initialize parking spot counts by reading all active tickets from database
			// 4. initialize entrance and exit panels: read from database
		}

		// static method to get the singleton instance of StockExchange
		public static ParkingLotSystem getInstance() {
			if (parkingLot == null) {
				parkingLot = new ParkingLotSystem();
			}
			return parkingLot;
		}

		// note that the following method is 'synchronized' to allow multiple entrances
		// panels to issue a new parking ticket without interfering with each other
		public synchronized ParkingTicket getNewParkingTicket(Vehicle vehicle) throws ParkingFullException {
			if (this.isFull(vehicle.getType())) {
				throw new ParkingFullException();
			}
			ParkingTicket ticket = new ParkingTicket();
			vehicle.assignTicket(ticket);
			ticket.saveInDB();
			// if the ticket is successfully saved in the database, we can increment the parking spot count
			this.incrementSpotCount(vehicle.getType());
			this.activeTickets.put(ticket.getTicketNumber(), ticket);
			return ticket;
		}

		public boolean isFull(VehicleType type) {
			// trucks and vans can only be parked in LargeSpot
			if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
				return largeSpotCount >= maxLargeCount;
			}

			// motorbikes can only be parked at motorbike spots
			if (type == VehicleType.MOTORBIKE) {
				return motorbikeSpotCount >= maxMotorbikeCount;
			}

			// cars can be parked at compact or large spots
			if (type == VehicleType.CAR) {
				return (compactSpotCount + largeSpotCount) >= (maxCompactCount + maxLargeCount);
			}

			// electric car can be parked at compact, large or electric spots
			return (compactSpotCount + largeSpotCount + electricSpotCount) >= (maxCompactCount + maxLargeCount
					+ maxElectricCount);
		}

		// increment the parking spot count based on the vehicle type
		private boolean incrementSpotCount(VehicleType type) {
			if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
				largeSpotCount++;
			} else if (type == VehicleType.MOTORBIKE) {
				motorbikeSpotCount++;
			} else if (type == VehicleType.CAR) {
				if (compactSpotCount < maxCompactCount) {
					compactSpotCount++;
				} else {
					largeSpotCount++;
				}
			} else { // electric car
				if (electricSpotCount < maxElectricCount) {
					electricSpotCount++;
				} else if (compactSpotCount < maxCompactCount) {
					compactSpotCount++;
				} else {
					largeSpotCount++;
				}
			}
		}

		public boolean isFull() {
			for (String key : parkingFloors.keySet()) {
				if (!parkingFloors.get(key).isFull()) {
					return false;
				}
			}
			return true;
		}

		public void addParkingFloor(ParkingFloor floor) {
			/* store in database */ }

		public void addEntrancePanel(EntrancePanel entrancePanel) {
			/* store in database */ }

		public void addExitPanel(ExitPanel exitPanel) {
			/* store in database */ }
	}
}
