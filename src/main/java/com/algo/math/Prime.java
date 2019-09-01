

package com.algo.math;


import java.util.Arrays;

import org.junit.Assert;


public class Prime {

    public boolean isPrimeSimple(int n) {
        if (n < 2)
            return false;

        for (int i = 2; i < n - 1; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    /**
     * less number of iterations than 1st as we need to go only upto sqrt.
     * 
     * @param n
     * @return
     */
    public boolean isPrimeBetter(int n) {
        if (n < 2)
            return false;

        double sqrt = Math.sqrt(n);
        for (int i = 2; i < sqrt; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    /**
     * using sieve of eratostehenes principle.
     * 
     * @param n
     * @return
     */
    public boolean[] listOfPrimes(int n) {
        boolean[] flags = new boolean[n + 1];
        int count = 0;

        // set all flags to true other than 0 and 1
        init(flags);
        int prime = 2;

        while (prime <= Math.sqrt(n)) {
            // cross of remaining multiples of prime
            crossOff(flags, prime);
            // find next value which is true
            prime = findNextPrime(flags, prime);
        }

        return flags;
    }

    private void init(boolean[] flags) {
        for (int i = 2; i < flags.length; i++)
            flags[i] = true;
    }

    /**
     * Cross off remaining multiples of prime. We can start with (prime*prime), * because if we have a k * prime, where k <
     * prime, this value would have already been crossed off in a prior iteration
     * 
     * @param flags
     * @param prime
     */
    private void crossOff(boolean[] flags, int prime) {
        for (int i = prime * prime; i < flags.length; i += prime)
            flags[i] = false;
    }

    private int findNextPrime(boolean[] flags, int prime) {
        int next = prime + 1;
        while (next < flags.length && !flags[next])
            next++;

        return next;
    }

    public static void main(String[] args) {
        Prime p = new Prime();
        Assert.assertTrue(p.isPrimeSimple(5));
        try {
            Assert.assertTrue(p.isPrimeSimple(6));
        }
        catch (AssertionError e) {
            System.err.println("6 not prime");
        }
        Assert.assertTrue(p.isPrimeBetter(5));
        try {
            Assert.assertTrue(p.isPrimeBetter(6));
        }
        catch (AssertionError e) {
            System.err.println("6 not prime");
        }

        System.out.println(Arrays.toString(p.listOfPrimes(10)));
    }

}
