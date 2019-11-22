package com.misc;

import java.util.List;

public class TFIDF {

	/**
	 * /** Calculates the tf of term termToCheck
	 * 
	 * @param totalterms  : Array of all the words under processing document
	 * @param termToCheck : term of which tf is to be calculated.
	 * @return tf(term frequency) of term termToCheck
	 */
	public double tf(String[] totalTerms, String termToCheck) {
		int count = 0;
		for (String terms : totalTerms) {
			if (terms.equalsIgnoreCase(termToCheck))
				count++;
		}

		return count / totalTerms.length;
	}

	/**
	 * Calculates idf of term termToCheck
	 * 
	 * @param allTerms    : all the terms of all the documents
	 * @param termToCheck
	 * @return idf(inverse document frequency) score
	 */
	public double idfCalculator(List<String[]> allTerms, String termToCheck) {
		double count = 0;
		for (String[] ss : allTerms) {
			for (String s : ss) {
				if (s.equalsIgnoreCase(termToCheck)) {
					count++;
					break;
				}
			}
		}
		return 1 + Math.log(allTerms.size() / count);
	}

}
