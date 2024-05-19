package com.companyprep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * <p>
 * You are given an integer array ranks and a character array suits. You have 5 cards where the ith card has a rank of
 * ranks[i] and a suit of suits[i].
 * 
 * The following are the types of poker hands you can make from best to worst:
 * 
 * "Flush": Five cards of the same suit.
 * 
 * "Three of a Kind": Three cards of the same rank.
 * 
 * "Pair": Two cards of the same rank.
 * 
 * "High Card": Any single card.
 * 
 * Return a string representing the best type of poker hand you can make with the given cards.
 * 
 * Note that the return values are case-sensitive.
 * 
 * 
 * Example 1:
 * 
 * Input: ranks = [13,2,3,1,9], suits = ["a","a","a","a","a"] Output: "Flush"
 * 
 * Explanation: The hand with all the cards consists of 5 cards with the same suit, so we have a "Flush".
 * 
 * Example 2:
 * 
 * Input: ranks = [4,4,2,4,4], suits = ["d","a","a","b","c"] Output: "Three of a Kind"
 * 
 * Explanation: The hand with the first, second, and fourth card consists of 3 cards with the same rank, so we have a
 * "Three of a Kind". Note that we could also make a "Pair" hand but "Three of a Kind" is a better hand. Also note that
 * other cards could be used to make the "Three of a Kind" hand.
 * 
 * Example 3:
 * 
 * Input: ranks = [10,10,2,12,9], suits = ["a","b","c","a","d"] Output: "Pair"
 * 
 * Explanation: The hand with the first and second card consists of 2 cards with the same rank, so we have a "Pair".
 * Note that we cannot make a "Flush" or a "Three of a Kind".
 * 
 * 
 * Constraints:
 * 
 * ranks.length == suits.length == 5 1 <= ranks[i] <= 13 'a' <= suits[i] <= 'd' No two cards have the same rank and
 * suit.
 * 
 * </p>
 * 
 * TODO: understand properly
 *
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BestPokerHand {

	private static class Card implements Comparable<Card> {

		private int rank;
		private int value;
		private char suit;
		private final String values = "23456789TJQKA";
		private final String suits = "CDHS";

		public Card(String str) {
			rank = ranking(str);
			value = rank / suits.length();
			suit = suits.charAt(rank % suits.length());
		}

		public int getRank() {
			return rank;
		}

		public int getValue() {
			return value;
		}

		public char getSuit() {
			return suit;
		}

		public int ranking(String str) {
			char value = str.charAt(0);
			char suit = str.charAt(1);
			for (int i = 0; i < values.length(); i++) {
				if (values.charAt(i) == value) {
					for (int j = 0; j < suits.length(); j++) {
						if (suits.charAt(j) == suit)
							return (i + 2) * suits.length() + j;
					}
				}
			}

			return -1;
		}

		@Override
		public int compareTo(Card anotherCard) {
			if (this.getValue() < anotherCard.getValue())
				return -1;
			else if (this.getValue() > anotherCard.getValue())
				return 1;
			else
				return 0;
		}

		@Override
		public boolean equals(Object o) {
			Card anotherCard = (Card) o;
			return this.getValue() == anotherCard.getValue();
		}

		@Override
		public int hashCode() {
			assert false : "no hashcode implementation";
			return 17;
		}

		@Override
		public String toString() {
			return "[" + value + suit + "]";
		}

	}

	private static class Hand {
		public static enum handRank {
			HIGHCARD, PAIR, TWOPAIR, THREEOFAKIND, STRAIGHT, FLUSH, FULLHOUSE, FOUROFAKIND, STRAIGHTFLUSH
		}

		private Card[] hand;
		private String name;

		public Hand() {
			hand = null;
			name = "";
		}

		public Hand(String handString, String name) {
			String[] cards = handString.split(" ");
			hand = new Card[cards.length];
			for (int i = 0; i < hand.length; i++) {
				hand[i] = new Card(cards[i]);
			}
			Arrays.sort(hand);
			this.name = name;
		}

		public Card[] getHand() {
			return this.hand;
		}

		public String getName() {
			return this.name;
		}

		private static boolean isPair(Card[] hand) {
			int card1 = hand[0].getValue();
			int card2 = hand[1].getValue();
			int card3 = hand[2].getValue();
			int card4 = hand[3].getValue();
			int card5 = hand[4].getValue();

			return ((card1 == card2 && card2 != card3) || (card2 == card3 && card3 != card4 && card2 != card1)
					|| (card3 == card4 && card4 != card5 && card3 != card2) || (card4 == card5 && card4 != card3));
		}

		private static boolean isTwoPair(Card[] hand) {
			int card1 = hand[0].getValue();
			int card2 = hand[1].getValue();
			int card3 = hand[2].getValue();
			int card4 = hand[3].getValue();
			int card5 = hand[4].getValue();

			return ((card1 == card2 && card3 == card4 && card2 != card3 && card4 != card5)
					|| (card1 != card2 && card2 == card3 && card3 != card4 && card4 == card5));
		}

		private static boolean isThreeOfAKind(Card[] hand) {
			int card1 = hand[0].getValue();
			int card2 = hand[1].getValue();
			int card3 = hand[2].getValue();
			int card4 = hand[3].getValue();
			int card5 = hand[4].getValue();

			return (card1 == card3 || card2 == card4 || card3 == card5);
		}

		private static boolean isStraight(Card[] hand) {

			return hand[4].getValue() - hand[0].getValue() == 4;
		}

		private static boolean isFlush(Card[] hand) {

			for (int i = 0; i < hand.length - 1; i++) {
				if (hand[i + 1].getSuit() != hand[i].getSuit())
					return false;
			}

			return true;
		}

		private static boolean isFullHouse(Card[] hand) {
			int card1 = hand[0].getValue();
			int card2 = hand[1].getValue();
			int card3 = hand[2].getValue();
			int card4 = hand[3].getValue();
			int card5 = hand[4].getValue();

			return ((card1 == card2 && card2 != card3 && card3 == card5)
					|| (card1 == card3 && card3 != card4 && card4 == card5));
		}

		private static boolean isFourOfAKind(Card[] hand) {

			return (hand[0].getValue() == hand[3].getValue() || hand[1].getValue() == hand[4].getValue());
		}

		private static boolean isStraightFlush(Card[] hand) {

			return isStraight(hand) && isFlush(hand);
		}

		private handRank getHandRank() {
			Card[] tempHand = this.getHand();
			if (isStraightFlush(tempHand))
				return handRank.STRAIGHTFLUSH;
			else if (isFourOfAKind(tempHand))
				return handRank.FOUROFAKIND;
			else if (isFullHouse(tempHand))
				return handRank.FULLHOUSE;
			else if (isFlush(tempHand))
				return handRank.FLUSH;
			else if (isStraight(tempHand))
				return handRank.STRAIGHT;
			else if (isThreeOfAKind(tempHand))
				return handRank.THREEOFAKIND;
			else if (isTwoPair(tempHand))
				return handRank.TWOPAIR;
			else if (isPair(tempHand))
				return handRank.PAIR;
			else
				return handRank.HIGHCARD;
		}

		private int compareHighCard(Hand hand1, Hand hand2) {
			Card[] hand1Cards = hand1.getHand();
			Card[] hand2Cards = hand2.getHand();
			return hand1Cards[hand1Cards.length - 1].compareTo(hand2Cards[hand2Cards.length - 1]);
		}

		private int compareRecursiveHighCard(Hand hand1, Hand hand2) {
			Card[] hand1Cards = hand1.getHand();
			Card[] hand2Cards = hand2.getHand();
			int current = hand1Cards.length - 1;
			int cmp = 0;
			while (current >= 0) {
				cmp = hand1Cards[current].compareTo(hand2Cards[current]);
				if (cmp != 0)
					break;
				current--;
			}
			return cmp;
		}

		private Card compareFourOfAKind(Hand tempHand) {
			Card[] tempCards = tempHand.getHand();
			if (tempCards[0] == tempCards[3])
				return tempCards[0];
			else if (tempCards[1] == tempCards[4])
				return tempCards[1];
			return null;
		}

		// 12345
		// 123 234 345
		private Card compareThreeOfAKind(Hand tempHand) {
			Card[] tempCards = tempHand.getHand();
			if (tempCards[0] == tempCards[2])
				return tempCards[0];
			else if (tempCards[1] == tempCards[3])
				return tempCards[1];
			else if (tempCards[2] == tempCards[4])
				return tempCards[2];
			return null;
		}

		private int compareTwoPair(Hand hand1, Hand hand2) {
			Card[] hand1Cards = hand1.getHand();
			int card1 = hand1Cards[0].getValue();
			int card2 = hand1Cards[1].getValue();
			int card3 = hand1Cards[2].getValue();
			int card4 = hand1Cards[3].getValue();
			int card5 = hand1Cards[4].getValue();
			Card hand1Bigger = null;
			Card hand1Smaller = null;
			Card hand1Remain = null;
			if (card1 == card2 && card3 == card4 && card2 != card3 && card4 != card5) {
				if (card1 > card3) {
					hand1Bigger = hand1Cards[0];
					hand1Smaller = hand1Cards[2];
					hand1Remain = hand1Cards[4];
				} else {
					hand1Bigger = hand1Cards[2];
					hand1Smaller = hand1Cards[0];
					hand1Remain = hand1Cards[4];
				}
			}
			if (card1 != card2 && card2 == card3 && card3 != card4 && card4 == card5) {
				if (card2 > card4) {
					hand1Bigger = hand1Cards[1];
					hand1Smaller = hand1Cards[3];
					hand1Remain = hand1Cards[0];
				} else {
					hand1Bigger = hand1Cards[3];
					hand1Smaller = hand1Cards[1];
					hand1Remain = hand1Cards[0];
				}
			}

			Card[] hand2Cards = hand1.getHand();
			int card11 = hand2Cards[0].getValue();
			int card22 = hand2Cards[1].getValue();
			int card33 = hand2Cards[2].getValue();
			int card44 = hand2Cards[3].getValue();
			int card55 = hand2Cards[4].getValue();
			Card hand2Bigger = null;
			Card hand2Smaller = null;
			Card hand2Remain = null;
			if (card11 == card22 && card33 == card44 && card22 != card33 && card44 != card55) {
				if (card11 > card33) {
					hand2Bigger = hand2Cards[0];
					hand2Smaller = hand2Cards[2];
					hand2Remain = hand2Cards[4];
				} else {
					hand2Bigger = hand2Cards[2];
					hand2Smaller = hand2Cards[0];
					hand2Remain = hand2Cards[4];
				}
			}
			if (card11 != card22 && card22 == card33 && card33 != card44 && card44 == card55) {
				if (card22 > card44) {
					hand2Bigger = hand2Cards[1];
					hand2Smaller = hand2Cards[3];
					hand2Remain = hand2Cards[0];
				} else {
					hand2Bigger = hand2Cards[3];
					hand2Smaller = hand2Cards[1];
					hand2Remain = hand2Cards[0];
				}
			}

			if (hand1Bigger.compareTo(hand2Bigger) != 0)
				return hand1Bigger.compareTo(hand2Bigger);
			else if (hand1Smaller.compareTo(hand2Smaller) != 0)
				return hand1Smaller.compareTo(hand2Smaller);
			else
				return hand1Remain.compareTo(hand2Remain);
		}

		private int comparePair(Hand hand1, Hand hand2) {
			Card[] hand1Cards = hand1.getHand();
			Card[] hand2Cards = hand2.getHand();
			Set<Card> hand1Set = new HashSet<Card>();
			Set<Card> hand2Set = new HashSet<Card>();

			Card hand1PairCard = null;
			Card hand2PairCard = null;
			for (int i = 0; i < hand1Cards.length; i++) {
				if (!hand1Set.contains(hand1Cards[i]))
					hand1Set.add(hand1Cards[i]);
				else
					hand1PairCard = hand1Cards[i];
			}
			for (int i = 0; i < hand2Cards.length; i++) {
				if (!hand2Set.contains(hand2Cards[i]))
					hand2Set.add(hand2Cards[i]);
				else
					hand2PairCard = hand2Cards[i];
			}

			if (hand1PairCard.compareTo(hand2PairCard) != 0)
				return hand1PairCard.compareTo(hand2PairCard);
			else {
				while (!hand1Set.isEmpty() && !hand2Set.isEmpty()) {
					Card tempHand1Max = Collections.max(hand1Set);
					Card tempHand2Max = Collections.max(hand2Set);
					if (tempHand1Max.compareTo(tempHand2Max) != 0)
						return tempHand1Max.compareTo(tempHand2Max);
					hand1Set.remove(tempHand1Max);
					hand2Set.remove(tempHand2Max);
				}
			}
			return -10000;
		}

		private String getWinner(Hand hand1, Hand hand2) {
			handRank hand1Rank = hand1.getHandRank();
			handRank hand2Rank = hand2.getHandRank();
			Card[] hand1Cards = hand1.getHand();
			Card[] hand2Cards = hand2.getHand();
			int cmp = hand1Rank.compareTo(hand2Rank);
			if (cmp > 0)
				return hand1.getName();
			else if (cmp < 0)
				return hand2.getName();
			else {
				switch (hand1Rank) {
				case STRAIGHTFLUSH:
					cmp = compareHighCard(hand1, hand2);
					break;
				case FOUROFAKIND:
					cmp = compareFourOfAKind(hand1).compareTo(compareFourOfAKind(hand2));
					break;
				case FULLHOUSE:
					cmp = compareThreeOfAKind(hand1).compareTo(compareThreeOfAKind(hand2));
					break;
				case FLUSH:
					cmp = compareRecursiveHighCard(hand1, hand2);
					break;
				case STRAIGHT:
					cmp = compareHighCard(hand1, hand2);
					break;
				case THREEOFAKIND:
					cmp = compareThreeOfAKind(hand1).compareTo(compareThreeOfAKind(hand2));
					break;
				// **XX- **-XX **XX-
				//
				case TWOPAIR:
					cmp = compareTwoPair(hand1, hand2);
					break;
				case PAIR:
					break;
				case HIGHCARD:
					cmp = compareRecursiveHighCard(hand1, hand2);
					break;
				}
			}

			return cmp > 0 ? hand1.getName() : cmp < 0 ? hand2.getName() : "Tie.";
		}
	}

	public static void main(String[] args) throws IOException {

		/*
		 ** 2H 3D 5S 9C KD 2C 3H 4S 8C AH 01234567890123456789012345678 2H 4S 4C 2D 4H 2S 8S AS QS 3S 2H 3D 5S 9C KD 2C
		 * 3H 4S 8C KH 2H 3D 5S 9C KD 2D 3H 5C 9S KH
		 */

		/* BufferedReader br = new BufferedReader(new FileReader("new.txt")); */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String twoHands;
		while ((twoHands = br.readLine()) != null) {
			String blackHandString = twoHands.substring(0, twoHands.length() / 2);
			String whiteHandString = twoHands.substring(twoHands.length() / 2 + 1, twoHands.length());
			Hand blackHand = new Hand(blackHandString, "Black");
			Hand whiteHand = new Hand(whiteHandString, "White");
			/* System.out.println(new Hand().getWinner(blackHand, whiteHand)+" wins."); */
			String result = new Hand().getWinner(blackHand, whiteHand);
			if (result.equals("Tie."))
				System.out.println(result);
			else
				System.out.println(result + " wins.");

		}
	}
}
