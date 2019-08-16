package com.algo.network;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Suppose there are a few tokens in a bucket. When a request comes, a token has to be taken from the bucket for it to
 * be processed. If there is no token available in the bucket, the request will be rejected and the requester has to
 * retry later. The token bucket is also refilled per time unit.
 * 
 * In this way, we can limit the requests per user per time unit by assigning a bucket with fixed amount of tokens to
 * each user. When a user uses up all the tokens in a time period, we know that he has exceeded the limit and reject his
 * requests until his bucket is refilled.
 * 
 * As we can see, in the token bucket algorithm, the request processing rate is not capped, which means it only
 * guarantees an average processing rate will not exceeds the maximum rate. But in some period, the real-time processing
 * rate can be higher than maximum.
 * 
 * @author surya
 *
 */
public class TokenBucketRateLimiter {

	private static final double MIN_WAIT_S = 0.005;
	private static final double MAX_WAIT_S = 0.1;

	private static final long TIMEOUT = 1000;

	private double bandwidth;
	private double bucketSize;

	private double bucket;
	private long lastFill;

	/**
	 * Create a new TokenBucket
	 * 
	 * @param bandwidth
	 *            the maximum bandwidth in tokens/s
	 * @param bucketSize
	 *            the maximum burst size
	 */
	public TokenBucketRateLimiter(double bandwidth, double bucketSize) {
		this.bandwidth = bandwidth;
		this.bucketSize = bucketSize;

		bucket = 0;
		lastFill = System.currentTimeMillis();
	}

	/**
	 * Wait until this many tokens may be send/received.
	 * 
	 * @param tokens
	 *            number if tokens
	 */
	public void waitForTokens(double tokens) {
		while (true) {
			updateBucket();
			synchronized (this) {
				if (bucket >= 0)
					break;
			}
			double waitTime = -bucket / bandwidth;
			if (waitTime < MIN_WAIT_S || waitTime > MAX_WAIT_S)
				waitTime = MIN_WAIT_S;
			try {
				Thread.sleep((long) (waitTime * 1000));
			} catch (InterruptedException ex) {
				Logger.getLogger(TokenBucketRateLimiter.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		synchronized (this) {
			bucket -= tokens;
		}
	}

	/**
	 * Check if the number of tokens may be send/received right now.
	 * 
	 * @param tokens
	 *            number of tokens
	 * @return send/received allowed right now?
	 */
	public boolean tokensAvailable(double tokens) {
		updateBucket();
		synchronized (this) {
			if (bucket >= 0) {
				bucket -= tokens;
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Update the bucket fill level.
	 */
	private synchronized void updateBucket() {
		long time = System.currentTimeMillis();

		if (bandwidth == 0) {
			bucket = bucketSize;
		} else {
			bucket += bandwidth * (time - lastFill) / 1000;
			if (bucket > bucketSize)
				bucket = bucketSize;
		}
		lastFill = time;
	}

	/**
	 * Set the maximum bandwidth.
	 * 
	 * @param bandwidth
	 *            the bandwidth in tokens/s
	 */
	public synchronized void setBandwidth(double bandwidth) {
		this.bandwidth = bandwidth;
	}

}
