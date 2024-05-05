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
 * Category : Hard
 * 
 */
public class SecuritySystemBadgeAccess {

	public static void main(String[] argv) {
		SecuritySystemBadgeAccess sec = new SecuritySystemBadgeAccess();
		String[][] badgeTimes = new String[][] { { "Paul", "1355" }, { "Jennifer", "1910" }, { "John", "835" },
				{ "John", "830" }, { "Paul", "1315" }, { "John", "1615" }, { "John", "1640" }, { "Paul", "1405" },
				{ "John", "855" }, { "John", "930" }, { "John", "915" }, { "John", "730" }, { "John", "940" },
				{ "Jennifer", "1335" }, { "Jennifer", "730" }, { "John", "1630" }, { "Jennifer", "5" }, };

		System.out.println(sec.getInvalidPersonList(badgeTimes));

	}

	public Map<String, List<Integer>> getInvalidPersonList(String[][] badgeTimes) {

		// map of name to list of badge in times
		Map<String, List<Integer>> map = new HashMap<>();

		for (String[] badgeTime : badgeTimes) {
			String name = badgeTime[0];
			int time = Integer.parseInt(badgeTime[1]);
			map.put(name, map.getOrDefault(name, new ArrayList<Integer>()));
			map.get(name).add(time);
		}

		System.out.println(map);

		Map<String, List<Integer>> result = new HashMap<>();
		for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
			String name = entry.getKey();
			List<Integer> list = entry.getValue();
			Collections.sort(list);
			if (list.size() > 2) {
				int start = 0, end = list.size() - 1;
				while (start < end) {
					// if we consider military time then 1 hr = 100. 2100 to 2200 or 0315 to 0415
					if ((list.get(end) - list.get(start) <= 100) && (end - start + 1 >= 3)) { // within the 1 hr window
						List<Integer> subList = list.subList(start, end + 1);
						System.out.println(subList);
						result.put(name, subList);
						break;
					} else {
						// consider both ends
						if ((list.get(end) - list.get(end - 1)) > (list.get(start + 1) - list.get(start))) {
							end--;
						} else {
							start++;
						}
					}
				}
			}
		}

		return result;

	}

}
