package com.algo.network;

public abstract class RateLimiter {

	protected final int maximumRequestsPerSec;

	protected RateLimiter(int maxRequests) {
		this.maximumRequestsPerSec = maxRequests;
	}

	abstract boolean allow();
}
