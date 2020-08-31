package com.ooad.amazonlocker;

import java.util.HashMap;
import java.util.Map;

public class LockerApp {

	private Map<LockerSize, Integer> map;

	public LockerApp() {
		this.map = new HashMap<LockerSize, Integer>();
		loadAvailableLockers();
	}

	private void loadAvailableLockers() {
		map.put(LockerSize.SMALL, 10);
		map.put(LockerSize.MEDIUM, 10);
		map.put(LockerSize.LARGE, 10);
	}

	public LockerSize getAvailableLockerSize(PackageSize pack) {
		int packageSize = pack.getSize();
		if (packageSize <= LockerSize.SMALL.getSize()) {
			return findSmallLocker();
		} else if (LockerSize.SMALL.getSize() <= packageSize && packageSize <= LockerSize.MEDIUM.getSize()) {
			return findMediumLocker();
		} else if (packageSize < LockerSize.LARGE.getSize()) {
			return findLargeLocker();
		}

		return LockerSize.INVALID;
	}

	private LockerSize findSmallLocker() {
		int smallLockerCount = map.get(LockerSize.SMALL);
		if (smallLockerCount == 0) {
			// try next bigger locker (i.e medium)
			return findMediumLocker();
		}
		// reduce count
		map.put(LockerSize.SMALL, map.get(LockerSize.SMALL) - 1);
		return LockerSize.SMALL;
	}

	private LockerSize findMediumLocker() {
		int mediumLockerCount = map.get(LockerSize.MEDIUM);
		if (mediumLockerCount == 0) {
			// try next bigger locker (i.e large)
			return findLargeLocker();
		}
		// reduce count
		map.put(LockerSize.MEDIUM, map.get(LockerSize.MEDIUM) - 1);
		return LockerSize.MEDIUM;
	}

	private LockerSize findLargeLocker() {
		int largeLockerCount = map.get(LockerSize.LARGE);
		if (largeLockerCount == 0) {
			return LockerSize.INVALID;
		}
		// reduce count
		map.put(LockerSize.LARGE, map.get(LockerSize.LARGE) - 1);
		return LockerSize.LARGE;
	}

	public void updateAvailableLockers(LockerSize locker) {
		map.put(locker, map.get(locker) + 1);
	}

	public static void main(String[] args) {
		LockerApp la = new LockerApp();
		for (int i = 0; i < 10; i++) {
			System.out.println(la.getAvailableLockerSize(PackageSize.SMALL));
		}
		System.out.println("*********************");
		for (int i = 0; i < 15; i++) {
			System.out.println(la.getAvailableLockerSize(PackageSize.SMALL));
		}
		System.out.println("*********************");
		for (int i = 0; i < 25; i++) {
			System.out.println(la.getAvailableLockerSize(PackageSize.SMALL));
		}
	}
}
