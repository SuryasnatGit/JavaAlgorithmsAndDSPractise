package com.companyprep;

import java.util.List;

/**
 * TODO : to complete
 */
public class UberTest {

	public int easyCountUber(List<Coordinate> coordinates) {
		coordinates.stream().map(a -> a.start).sorted();
		return 0;
	}

	class Coordinate {
		int start;
		int end;
	}

}
