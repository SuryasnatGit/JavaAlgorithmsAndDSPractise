package com.leetcode;

import java.util.Arrays;

/**
 * rotated array很简单啊，就是比如1秒钟限制3个request. 我维护一个long int array 长度为3，里面存着最近3个request的访问时间戳，一个指针指向这3个中第一个进来的request。
 * 有新的请求，我就检查指针指向的request是不是已经是1秒以前的，如果是则请求成功，新请求的时间戳覆盖指针指向。然后指针+1再mod，就rotated了。
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
		if (arr[pos] == -1) { // 还没满呢，继续往前推进
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
