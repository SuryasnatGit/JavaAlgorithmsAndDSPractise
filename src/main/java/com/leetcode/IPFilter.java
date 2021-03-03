package com.leetcode;

import java.util.List;

/**
 * Given an API to find all IPv4 addresses in a log file, find all IPs that occurred only once.
 * 
 * Follow-up: What if the log comes from a data stream.
 * 
 * Follow-up: If the machine has 4GB RAM, is there going to be a problem?
 * 
 * Approach: For the first problem a hash set of {IPs} may simply solve it.
 * 
 * However in the follow-ups as the amount of data cumulates, if the IPs are stored as strings, 4GB is becomes
 * absolutely insufficient, since there are over 4 billion IPs out there and each ip as a string takes at least 9 bytes.
 * Even if convert ip to integer(8B), it wouldn't be enough.
 * 
 * An approach is to go with bit set. Then each ip takes only 1 bit. Have a bit set to store whether an ip occurs and
 * another set to store whether an ip repeats.
 * 
 * This takes 2 * 2^32 bit = ~1GB -> fits in RAM.
 * 
 * TODO : understand more and write test cases.
 */
public class IPFilter {
	long[] map; // mark all ip that showed up
	long[] repeatedIP; // mark all ips that repeatedly showed up

	public IPFilter() {
		// there's 2^32 IP in total.
		// each long integer is identifies 64 IPs.
		// Need 2^32 / 2^6 long integers in the bit map
		int size = 1 << (32 - 6);
		map = new long[size];
		repeatedIP = new long[size];
	}

	public void addToMap(List<String> IPs) {
		for (String ip : IPs) {
			long decimal = IPToLong(ip);
			int idx = (int) (decimal / 64);
			int res = (int) (decimal % 64);

			if ((map[idx] >> res) == 1) { // repeated ip
				repeatedIP[idx] |= (1 << res);
			} else { // first occurred ip
				map[idx] |= (1 << res);
			}
		}
	}

	private long IPToLong(String ipAddress) { // convert ip (base 256) to decimal
		String[] ipAddressInArray = ipAddress.split("\\.");
		long result = 0;
		for (int i = 0; i < ipAddressInArray.length; i++) {
			int power = 3 - i;
			int ip = Integer.parseInt(ipAddressInArray[i]);
			result += ip * Math.pow(256, power);
		}
		return result;
	}

}
