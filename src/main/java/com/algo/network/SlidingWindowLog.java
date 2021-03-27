package com.algo.network;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Sliding window log algorithm keeps a log of request timestamps for each user. When a request comes, we first pop all
 * outdated timestamps before appending the new request time to the log. Then we decide whether this request should be
 * processed depending on whether the log size has exceeded the limit.
 * 
 */
public class SlidingWindowLog extends RateLimiter {

	private final Queue<Long> log = new LinkedList<>();

	protected SlidingWindowLog(int maxRequestPerSec) {
		super(maxRequestPerSec);
	}

	@Override
	boolean allow() {
		long curTime = System.currentTimeMillis();
		long boundary = curTime - 1000;
		synchronized (log) {
			while (!log.isEmpty() && log.element() <= boundary) {
				log.poll();
			}
			log.add(curTime);
			return log.size() <= maximumRequestsPerSec;
		}
	}
}