package com.leetcode;

import java.util.Arrays;

/**
 * The rotated array is very simple, that is, for example, 1 second to limit 3 requests. I maintain a long int array
 * with a length of 3, which stores the access timestamps of the last 3 requests, and a pointer points to the first one
 * of these 3 requests. request. If there is a new request, I will check whether the request pointed to by the pointer
 * is 1 second ago. If it is, the request is successful, and the timestamp of the new request overwrites the pointer.
 * Then the pointer +1 and then mod, it is rotated.
 *
 */
public class RateLimiter {
	int[] arr = null;
	int pos = -1;

	// Max Query per second is N.
	RateLimiter(int N) {
		arr = new int[N];
		Arrays.fill(arr, -1);
		pos = 0;
	}

	boolean query(int timestamp) {
		if (arr[pos] == -1) { // It’s not full yet, keep going
			arr[pos] = timestamp;
			pos = (pos + 1) % arr.length;
			return true;
		} else { // Pos is the oldest
			if (timestamp - arr[pos] > 60) {
				arr[pos] = timestamp; // Too old, update
				pos = (pos + 1) % arr.length;
				return true;
			} else {
				// Reject
				return false;
			}
		}
	}

	public static void main(String[] args) {
		RateLimiter limiter = new RateLimiter(3);

		limiter.query(1);
		limiter.query(5);
		limiter.query(8);
		limiter.query(10);
		limiter.query(16);
		limiter.query(19);
		limiter.query(50);
		limiter.query(68);

		limiter.query(78);
		limiter.query(88);
		limiter.query(98);
	}
}
