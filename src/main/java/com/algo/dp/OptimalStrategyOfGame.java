package com.algo.dp;

/**
 * Since very long time Tom and Jerry have been fighting with each other for a
 * piece of Cheese. So finally you came to rescue and decided that the result of
 * the fight will be decided by a mathematical game , in which you will write a
 * number N . Tom and Jerry will play the game alternatively and each of them
 * would subtract a number n [n< N] such that N%n=0. The game is repeated turn
 * by turn until the one,who now cannot make a further move looses the game.
 * 
 * The game begins with Tom playing first move.It is well understood that both
 * of them will make moves in optimal way.You are to determine who wins the game
 * 
 * Print 1 if Tom wins and Print 0 if Jerry wins in a separate line.
 * 
 * @author M_402201
 *
 */
public class OptimalStrategyOfGame {

	private int count;
	private boolean turn;

	public OptimalStrategyOfGame() {
		turn = true; // tom will start the game
		count = 0;
	}

	public void optimalStrategy(int n) {
		while (count <= n) {
			if (n % count == 0) {
				n -= count;
			}
		}
	}

}
