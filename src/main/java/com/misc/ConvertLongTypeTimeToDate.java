package com.misc;

public class ConvertLongTypeTimeToDate {

	private static final long MS_IN_NORMAL_YEAR = 365 * 24 * 60 * 60 * 1000L;
	private static final long MS_IN_LEAP_YEAR = 366 * 24 * 60 * 60 * 1000L;
	// we denote month by 1,2,.. 12 (index wise should start from 0)
	private static final int[] DAYS_IN_MONTH = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public void convert(long timeInMs) {
		int year = 1970; // inception year
		boolean isLeapYear = isLeapYear(year);
		long msInYear = isLeapYear ? MS_IN_LEAP_YEAR : MS_IN_NORMAL_YEAR;
		while (timeInMs > msInYear) {
			timeInMs -= msInYear;
			year += 1;
			isLeapYear = isLeapYear(year);
			msInYear = isLeapYear ? MS_IN_LEAP_YEAR : MS_IN_NORMAL_YEAR;
		}

		int month = 1;
		long msInMonth = getMsInMonth(month, isLeapYear);
		while (timeInMs > msInMonth) {
			timeInMs -= msInMonth;
			month += 1;
			msInMonth = getMsInMonth(month, isLeapYear);
		}

		long day = (timeInMs / (24 * 60 * 60 * 1000)) + 1;
		System.out.println("Day :" + day + " Month :" + month + " Year :" + year);
	}

	/**
	 * To determine whether a year is a leap year, follow these steps:
	 * 
	 * 1. If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
	 * 
	 * 2. If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
	 * 
	 * 3. If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
	 * 
	 * 4. The year is a leap year (it has 366 days).
	 * 
	 * 5. The year is not a leap year (it has 365 days).
	 */
	private boolean isLeapYear(int year) {
		return (year % 4 == 0 ? (year % 100 == 0 ? (year % 400 == 0 ? true : false) : true) : false);
	}

	private long getMsInMonth(int month, boolean isLeapYear) {
		if (isLeapYear && month == 2) {
			return 29 * 24 * 60 * 60 * 1000L;
		}
		return DAYS_IN_MONTH[month] * 24 * 60 * 60 * 1000L;
	}

	public static void main(String[] args) {
		ConvertLongTypeTimeToDate con = new ConvertLongTypeTimeToDate();
		long timeInMs = 1477715454741L;
		con.convert(timeInMs);
	}
}
