package com.companyprep;

/**
 * A new Amazon-developed scaling computing system checks the average utilization of the computing system every second
 * while it monitors. It implements an autoscale policy to add or reduce instances depending on the current load as
 * described below. Once an action of adding or reducing the number of instances is performed, the system will stop
 * monitoring for 10 seconds. During that time, the number of instances does not change.
 * 
 * Average utilization < 25%: An action is instantiated to reduce the number of instances by half if the number of
 * instances is greater than 1 (take the ceiling if the number is not an integer). If the number of instances is 1, take
 * no action.
 * 
 * 25% <= Average utilization <= 60%: Take no action.
 * 
 * Average utilization > 60%: An action is instantiated to double the number of instances if the doubled value does not
 * exceed 2* 10^8. If the number of instances exceeds this limit upon doubling, perform no action.
 * 
 * Given an array of integers that represent the average utilization at each second, determine the number of instances
 * at the end of the time frame.
 * 
 * Example
 * 
 * instances = 2 averageUtil = [25, 23, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 76, 80]
 * 
 * At second 1, the average utilization averageUtil[0] = 25 <= 25. Take no action.
 * 
 * At second 2, the average utilization averageUtil[1] = 23 < 25. Reduce the number of instances by half, 2/2 = 1.
 * 
 * Since an action was taken, the system will stop checking for 10 seconds, from averageUtil[2] to averageUtil[11].
 * 
 * At averageUtil[12] = 76, 76 > 60 so the number of instances is doubled from 1 to 2.
 * 
 * There are no more readings to consider and 2 is the final answer.
 * 
 * Function Description
 * 
 * Complete the function finallnstances in the editor below.
 * 
 * finallnstances has the following parameter(s):
 * 
 * int instances: an integer that represents the original number of instances running
 * 
 * int averageUtil[n]: an array of integers that represents the average utilization at each second of the time frame
 * 
 * Returns: int: an integer that represents the final number of instances running.
 *
 * Category : Easy
 * 
 */
public class UtilizationChecks {

	public int finalInstances(int instances, int[] averageUtil) {

		for (int i = 0; i < averageUtil.length; i++) {
			if (averageUtil[i] < 25 && instances > 1) {
				// split by half
				double d = (double) instances / 2;
				d = Math.ceil(d);
				instances = (int) d;

				// action done , so rest for 10 secs
				i += 10;
			} else if (averageUtil[i] >= 25 && averageUtil[i] <= 60) {
				// no action
			} else if (averageUtil[i] > 60 && instances < 200000000) {
				// increase by 2 times
				instances *= 2;
				// action done, so rest for 10 secs
				i += 10;
			}
		}

		return instances;

	}

	public static void main(String[] args) {
		UtilizationChecks uc = new UtilizationChecks();
		System.out.println(uc.finalInstances(2, new int[] { 25, 23, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 76, 80 })); // 2
		System.out.println(uc.finalInstances(5, new int[] { 30, 5, 4, 8, 19, 89 })); // 3
		System.out.println(uc.finalInstances(100000000, new int[] { 30, 95, 4, 8, 19, 89 })); // 200000000

	}
}
