package com.algo.network;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Fixed window counter algorithm divides the timeline into fixed-size windows and assign a counter to each window. Each
 * request, based on its arriving time, is mapped to a window. If the counter in the window has reached the limit,
 * requests falling in this window should be rejected. For example, if we set the window size to 1 minute. Then the
 * windows are [00:00, 00:01), [00:01, 00:02), ...[23:59, 00:00)
 * 
 */
public class FixedWindowCounter extends RateLimiter {
	// TODO: Clean up stale entries
	private final ConcurrentMap<Long, AtomicInteger> windows = new ConcurrentHashMap<>();

	protected FixedWindowCounter(int maxRequestPerSec) {
		super(maxRequestPerSec);
	}

	@Override
	boolean allow() {
		long windowKey = System.currentTimeMillis() / 1000 * 1000;
		windows.putIfAbsent(windowKey, new AtomicInteger(0));
		return windows.get(windowKey).incrementAndGet() <= maximumRequestsPerSec;
	}
}
