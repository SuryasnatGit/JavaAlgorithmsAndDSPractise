package com.companyprep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * We are working on a security system for a badged-access room in our company's building.
 * 
 * We want to find employees who badged into our secured room unusually often. We have an unordered list of names and
 * entry times over a single day. Access times are given as numbers up to four digits in length using 24-hour time, such
 * as "800" or "2250".
 * 
 * Write a function that finds anyone who badged into the room three or more times in a one-hour period. Your function
 * should return each of the employees who fit that criteria, plus the times that they badged in during the one-hour
 * period. If there are multiple one-hour periods where this was true for an employee, just return the first one.
 * 
 * badge_times = [ <br/>
 * ["Paul", "1355"], <br/>
 * ["Jennifer", "1910"], <br/>
 * ["John", "835"],<br/>
 * ["John", "830"],<br/>
 * ["Paul", "1315"],<br/>
 * ["John", "1615"],<br/>
 * ["John", "1640"],<br/>
 * ["Paul", "1405"],<br/>
 * ["John", "855"],<br/>
 * ["John", "930"],<br/>
 * ["John", "915"],<br/>
 * ["John", "730"],<br/>
 * ["John", "940"],<br/>
 * ["Jennifer", "1335"],<br/>
 * ["Jennifer", "730"],<br/>
 * ["John", "1630"],<br/>
 * ["Jennifer", "5"]<br/>
 * ]<br/>
 * 
 * Expected output (in any order)
 * 
 * John: 830 835 855 915 930 Paul: 1315 1355 1405
 * 
 * n: length of the badge records array
 * 
 *
 * 
 */
public class SecuritySystemBadgeAccess {

	public static void main(String[] argv) {
		String[][] badgeTimes = new String[][] { { "Paul", "1355" }, { "Jennifer", "1910" }, { "John", "835" },
				{ "John", "830" }, { "Paul", "1315" }, { "John", "1615" }, { "John", "1640" }, { "Paul", "1405" },
				{ "John", "855" }, { "John", "930" }, { "John", "915" }, { "John", "730" }, { "John", "940" },
				{ "Jennifer", "1335" }, { "Jennifer", "730" }, { "John", "1630" }, { "Jennifer", "5" }, };

	}

	public List<String> getInvalidPersonList(String[][] badgeTimes) {

		Map<String, List<Integer>> map = new HashMap<>();

		for (String[] badgeTime : badgeTimes) {
			String name = badgeTime[0];
			String time = badgeTime[1];
			int timeInMins = 0;

			// parse it
			if (time.length() >= 3) {
				int hourPart = Integer.parseInt(time.substring(0, time.length() - 2));
				int minPart = Integer.parseInt(time.substring(time.length() - 2, time.length() + 1));
				timeInMins = hourPart * 60 + minPart;
			} else {
				timeInMins = Integer.parseInt(time);
			}
			List<Integer> timeList = null;
			if (!map.containsKey(name)) {
				timeList = new ArrayList<>();
			} else {
				timeList = map.get(name);
			}
			timeList.add(timeInMins);
			Collections.sort(timeList);

			map.put(name, timeList);

		}

		for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
			String name = entry.getKey();
			int count = 0;

			List<Integer> list = entry.getValue();
			int start = list.get(0);
			int end = list.get(list.size() - 1);

			while (start <= end) {

			}

		}

	}

}
