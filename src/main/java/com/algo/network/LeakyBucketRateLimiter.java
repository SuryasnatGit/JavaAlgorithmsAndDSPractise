package com.algo.network;

/**
 * If we map it our limiting requests to server use case, water drops from the faucets are the requests, the bucket is
 * the request queue and the water drops leaked from the bucket are the responses. Just as the water dropping to a full
 * bucket will overflow, the requests arrive after the queue becomes full will be rejected.
 * 
 * As we can see, in the leaky bucket algorithm, the requests are processed at an approximately constant rate, which
 * smooths out bursts of requests. Even though the incoming requests can be bursty, the outgoing responses are always at
 * a same rate.
 * 
 * @author surya
 *
 */
public class LeakyBucketRateLimiter extends RateLimiter {

	private long nextAllowedTime;
	private final long requestIntervalMs;

	protected LeakyBucketRateLimiter(int maxRequestsPerSec) {
		super(maxRequestsPerSec);
		requestIntervalMs = 1000 / maxRequestsPerSec;
		nextAllowedTime = System.currentTimeMillis();
	}

	@Override
	boolean allow() {
		long currentTime = System.currentTimeMillis();
		synchronized (this) { // since this is called multiple times, so critical area synchronized
			if (currentTime >= nextAllowedTime) {
				nextAllowedTime = currentTime + requestIntervalMs;
				return true;
			}
			return false;
		}
	}
}
