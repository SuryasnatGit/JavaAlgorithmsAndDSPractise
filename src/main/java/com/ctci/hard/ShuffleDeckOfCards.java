package com.ctci.hard;

import java.util.Arrays;

/**
 * Write a method to shuffle a deck of cards. It must be a perfect shuffle-in other words, each of the 52! permutations
 * of the deck has to be equally likely. Assume that you are given a random number generator which is perfect
 * 
 * CTCI : 17.2
 *
 */
public class ShuffleDeckOfCards {

	/**
	 * Using our Base Case and Build approach, we can ask this question : suppose we had a method shuffle ( ... ) that
	 * worked on n - 1 elements. Could we use this to shuffle n elements? Sure. In fact, that's quite easy. We would
	 * first shuffle the first n - 1 elements. Then, we would take the nth element and randomly swap it with an element
	 * in the array. That's it!
	 * 
	 * Iterative way
	 * 
	 * @param cards
	 */
	public void shuffle(int[] cards) {
		for (int i = 0; i < cards.length; i++) {
			int k = random(0, i);
			int temp = cards[k];
			cards[k] = cards[i];
			cards[i] = temp;
		}
	}

	private int random(int lower, int higher) {
		return lower + (int) (Math.random()) * (higher - lower + 1);
	}

	public static void main(String[] args) {
		ShuffleDeckOfCards sd = new ShuffleDeckOfCards();
		int[] cards = { 1, 5, 3, 2, 4 };
		System.out.println(Arrays.toString(cards));
		sd.shuffle(cards);
		System.out.println(Arrays.toString(cards));
	}

}
