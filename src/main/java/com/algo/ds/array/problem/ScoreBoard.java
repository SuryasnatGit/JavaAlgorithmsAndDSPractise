package com.algo.ds.array.problem;

public class ScoreBoard {

	private int numItems = 0;
	private GameEntry[] gameArr;

	public ScoreBoard(int initialNum) {
		gameArr = new GameEntry[initialNum];
	}

	public void add(GameEntry e) {
		int ns = e.getScore();
		if (numItems < gameArr.length || ns > gameArr[numItems - 1].getScore()) {
			if (numItems < gameArr.length)
				numItems++;

			int j = numItems - 1;
			while (j > 0 && gameArr[j - 1].getScore() < ns) {
				gameArr[j] = gameArr[j - 1];
				j--;
			}

			gameArr[j] = e;
		}
	}

	public GameEntry remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= numItems)
			throw new IndexOutOfBoundsException("invalid index :" + index);

		GameEntry temp = gameArr[index];
		for (int j = index; j < numItems - 1; j++)
			gameArr[j] = gameArr[j + 1];

		gameArr[numItems - 1] = null;
		numItems--;

		return temp;
	}
}
