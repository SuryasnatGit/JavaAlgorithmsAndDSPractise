package com.ooad.amazonlocker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Create these objects: Locker, Location, Package, User, Order, Shipment
 * 
 * Amazon warehouse packages Orders into a Shipment with one or more Packages.
 * 
 * Insert created Package(s) into a database and associate them with a Shipment. Associate Shipment with Order,
 * Associate Order with User.
 * 
 * Guarantee that the length, width, and height of each Package cannot exceed the largest Locker's length, width, and
 * height.
 * 
 * (Closest Locker Problem) Find closest Location of lockers to the Package's destination Location. Check that the
 * Location has a volume of Locker spaces greater than or equal to the Package volume (we only need to check volume
 * because step 3 constrains the dimensions). If not, find second closest Locker Location, and so on and so forth.
 * 
 * (Fitting Problem) Lockers have a set number of sizes (say small, medium, and large). Now, design an algorithm to fit
 * Packages volume into Locker volume, so that minimum amount of Lockers are used. This is easily imagined as a
 * recursive algorithm where you continuously solve for the remaining Packages until the Packages are all fit into
 * Lockers. Each time you fit a package, you return a list of available boxes (remaining spaces in the locker in terms
 * of boxes for that single Locker). If there are no boxes that fit the remaining packages, look for another Locker for
 * the rest of the packages. This method will return the list of Lockers used for the Shipment.
 * 
 * Once we know the Packages can be stored at a Locker Location, return the used Locker's Locker IDs and Password to the
 * user (delivery person, recipient, etc).
 * 
 * @author surya
 *
 */
public class LockerApp {

	private Map<LockerType, Integer> map;

	// map of locker by zipcode
	private Map<Integer, List<Locker>> lockerMapByZip; // this will be preprocessed

	public LockerApp() {
		this.map = new HashMap<LockerType, Integer>();
		loadAvailableLockers();
	}

	private void loadAvailableLockers() {
		map.put(LockerType.SMALL, 10);
		map.put(LockerType.MEDIUM, 10);
		map.put(LockerType.LARGE, 10);
	}

	public List<Locker> getClosestLockersByZip(int zipCode) {
		List<Locker> lockerList = lockerMapByZip.get(zipCode);

		PriorityQueue<Locker> pq = new PriorityQueue<Locker>(
				(a, b) -> a.getLockerLoc().getDistance() > b.getLockerLoc().getDistance() ? -1 : 1);

		for (Locker locker : lockerList) {
			Location loc = locker.getLockerLoc();
			int lat = loc.getLatitude();
			int lon = loc.getLongitude();
			double dist = Math.sqrt(lat * lat + lon * lon);
			loc.setDistance(dist);
			pq.add(locker);
		}

		List<Locker> result = new ArrayList<>();
		int dist = 5; // 5 miles around that zip code
		// while (true) {
		while (dist-- > 0) {
			result.add(pq.poll());
		}
		// }

		return result;
	}

	// fitting problem
	public LockerType getAvailableLockerSize(PackageType pack) {
		int packageSize = pack.getSize();
		if (packageSize <= LockerType.SMALL.getSize()) {
			return findSmallLocker();
		} else if (LockerType.SMALL.getSize() <= packageSize && packageSize <= LockerType.MEDIUM.getSize()) {
			return findMediumLocker();
		} else if (packageSize < LockerType.LARGE.getSize()) {
			return findLargeLocker();
		}

		return LockerType.INVALID;
	}

	private LockerType findSmallLocker() {
		int smallLockerCount = map.get(LockerType.SMALL);
		if (smallLockerCount == 0) {
			// try next bigger locker (i.e medium)
			return findMediumLocker();
		}
		// reduce count
		map.put(LockerType.SMALL, map.get(LockerType.SMALL) - 1);
		return LockerType.SMALL;
	}

	private LockerType findMediumLocker() {
		int mediumLockerCount = map.get(LockerType.MEDIUM);
		if (mediumLockerCount == 0) {
			// try next bigger locker (i.e large)
			return findLargeLocker();
		}
		// reduce count
		map.put(LockerType.MEDIUM, map.get(LockerType.MEDIUM) - 1);
		return LockerType.MEDIUM;
	}

	private LockerType findLargeLocker() {
		int largeLockerCount = map.get(LockerType.LARGE);
		if (largeLockerCount == 0) {
			return LockerType.INVALID;
		}
		// reduce count
		map.put(LockerType.LARGE, map.get(LockerType.LARGE) - 1);
		return LockerType.LARGE;
	}

	public void updateAvailableLockers(LockerType locker) {
		map.put(locker, map.get(locker) + 1);
	}

	public static void main(String[] args) {
		LockerApp la = new LockerApp();
		for (int i = 0; i < 10; i++) {
			System.out.println(la.getAvailableLockerSize(PackageType.SMALL));
		}
		System.out.println("*********************");
		for (int i = 0; i < 15; i++) {
			System.out.println(la.getAvailableLockerSize(PackageType.SMALL));
		}
		System.out.println("*********************");
		for (int i = 0; i < 25; i++) {
			System.out.println(la.getAvailableLockerSize(PackageType.SMALL));
		}
	}
}
