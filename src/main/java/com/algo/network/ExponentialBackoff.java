package com.algo.network;

import java.util.Random;

/**
 * An exponential backoff is an algorithm that repeatedly attempts to execute some action until that action has
 * succeeded, waiting an amount of time that grows exponentially between each attempt, up to some maximum number of
 * attempts. This can be a useful way to manage network calls to external services, so that temporary errors or glitches
 * don’t cause permanent failures.
 * 
 * @author surya
 *
 */
public class ExponentialBackoff {

	public void execute() {
		int low = 1;
		int high = 1000;
		Random r = new Random();

		// Attempt to execute our main action, retrying up to 4 times
		// if an exception is thrown
		for (int n = 0; n <= 4; n++) {
			try {

				// The main action you want to execute goes here
				// If this does not come in the form of a return
				// statement (i.e., code continues below the loop)
				// then you must insert a break statement after the
				// action is complete.
				doSomething();

			} catch (Exception e) {

				// If we've exhausted our retries, throw the exception
				if (n == 4) {
					throw e;
				}

				// Wait an indeterminate amount of time (range determined by n)
				try {
					Thread.sleep(((int) Math.round(Math.pow(2, n)) * 1000) + (r.nextInt(high - low) + low));
				} catch (InterruptedException ignored) {
					// Ignoring interruptions in the Thread sleep so that
					// retries continue
				}
			}
		}
	}

	private void doSomething() {
		// TODO:
	}

}
