package com.algo.ds;

import java.io.File;

import org.junit.Test;

import com.algo.ds.array.Recursion;
import com.algo.ds.array.Sorting;
import com.algo.ds.array.problem.CeaserCypher;
import com.algo.ds.array.problem.TicTacToe;

public class ArrayTest {

	@Test
	public void testInsertionSort() {
		Sorting sorting = new Sorting();
		sorting.insertionSort("timcook".toCharArray());
	}

	@Test
	public void testCeaserCypher() {
		CeaserCypher ceaserCypher = new CeaserCypher(3);
		String plainText = "meet me at tgif then we will see";
		String encrypt = ceaserCypher.encrypt(plainText.toUpperCase());
		System.out.println("encrypted ==>" + encrypt);
		String decrypt = ceaserCypher.decrypt(encrypt);
		System.out.println("decrypted ==>" + decrypt);
	}

	@Test
	public void testTTT() {
		TicTacToe game = new TicTacToe();
		game.putMark(1, 1);
		game.putMark(0, 2);
		game.putMark(2, 1);
		game.putMark(0, 0);
		game.putMark(0, 1);

		System.out.println(game.getWinner());
	}

	@Test
	public void testRecursion() {
		Recursion recursion = new Recursion();
		recursion.diskUsage(new File("C:\\Users\\Suryasnat\\Music\\songs"));
	}

}
