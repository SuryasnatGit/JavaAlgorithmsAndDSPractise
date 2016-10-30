package com.algo.ds.array.problem;

public class CeaserCypher {

	private char[] encoded = new char[26];
	private char[] decoded = new char[26];

	// fill the array with rotation
	public CeaserCypher(int rotation) {
		for (int i = 0; i < 26; i++) {
			encoded[i] = (char) ('A' + (i + rotation) % 26);
			decoded[i] = (char) ('A' + (i - rotation + 26) % 26);
		}
	}

	public String encrypt(String plainText) {
		return transform(plainText, encoded);
	}

	public String decrypt(String cipherText) {
		return transform(cipherText, decoded);
	}

	public String transform(String text, char[] cipher) {
		char[] textArr = text.toCharArray();
		for (int i = 0; i < textArr.length; i++) {
			if (Character.isUpperCase(textArr[i])) {
				int j = textArr[i] - 'A';
				textArr[i] = cipher[j];
			}
		}
		return new String(textArr);
	}

}
