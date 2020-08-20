package com.ctci.hard;

/**
 * Count of 2s: Write a method to count the number of 2s between 0 and n.
 * 
 * CTCI : 17.6
 * 
 * TODO : revisit later
 *
 */
public class CountNumberOf2sInRange {

	public int count2sBruteForce(int n) {
		int count = 0;

		for (int i = 2; i <= n; i++) {
			count += count2s(i);
		}

		return count;
	}

	private int count2s(int n) {
		int count = 0;
		while (n > 0) {
			if (n % 10 == 2) {
				count++;
			}
			n /= 10;
		}
		return count;
	}

	/**
	 * We need recursion. For a number, we split it into two parts: the MSB and the reminder. For example, 319 has MSB
	 * of 3 and reminder of 19.
	 * 
	 * 1. Count the number of 2s for MSB:
	 * 
	 * If MSB > 2: We will have 1 or 10 or 100 or 1000, etc 2s. In this case of 319, we have 100 2s (occurring at MSB
	 * from 200 to 299).
	 * 
	 * If MSB == 2: We will have reminder+1 2s. For example if we have n = 219, we have 20 2s (occurring at MSB from 200
	 * to 219).
	 * 
	 * 2. Count the number of 2s for reminder, two parts:
	 * 
	 * Recursively count the number of 2s for the tens. For example of n = 319, we’d like to recursively count number of
	 * 2s from 1 to 100. We then know we have 3 times that number of 2s. This is like: we know number 12 has a 2, so we
	 * know number 12, 112 and 212 have three 2s.
	 * 
	 * Count the number of 2s causing from the reminder. For example of n = 319, we’d like to recursively count number
	 * of 2s from 1 to 19. That counts for the number of 2s appearing from 301 to 319.
	 */
	public int numberOf2s(int n) {

		if (n < 2)
			return 0;

		int result = 0;
		int power10 = 1;

		while (power10 * 10 < n) {
			power10 *= 10;
		}

		// power10 = 100

		int msb = n / power10; // 3
		int reminder = n % power10; // 19

		// Count # of 2s from MSB
		if (msb > 2) {
			// This counts the first 2 from 200 to 299
			result += power10;
		}

		if (msb == 2) {
			// If n = 219, this counts the first 2
			// from 200 to 219 (20 of 2s).
			result += reminder + 1;
		}

		// Count # of 2s from reminder
		// This (recursively) counts for # of 2s from 1 to 100
		// msb = 3, so we need to multiply by that.

		result += msb * numberOf2s(power10);

		// This (recursively) counts for # of 2s from 1 to reminder
		result += numberOf2s(reminder);

		return result;

	}

	public static void main(String[] args) {
		CountNumberOf2sInRange count = new CountNumberOf2sInRange();
		System.out.println(count.count2sBruteForce(1));
		System.out.println(count.count2sBruteForce(2));
		System.out.println(count.count2sBruteForce(212));
		System.out.println(count.numberOf2s(212));
		System.out.println(count.numberOf2s(1000));
		System.out.println(count.numberOf2s(61523));
	}
}
