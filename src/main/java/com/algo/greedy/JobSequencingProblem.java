package com.algo.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of tasks with deadlines and total profit earned on completion of a task, find maximum profit earned by
 * executing the tasks within the specified deadlines. Assume any task will take one unit of time to execute and any
 * task cant execute beyond its deadline. Also, only one task can be executed at a time.
 * 
 * Solution : Use greedy approach. Consider each task in decreasing order of their profit and schedule it in the latest
 * possible free slot that meets its deadline. If no such slot is there then don't schedule the task.
 * 
 * https://www.techiedelight.com/job-sequencing-problem-deadlines/
 *
 */
public class JobSequencingProblem {

	public int scheduleJobs(List<Job> jobs) {
		int t = 15;

		int[] slot = new int[t];
		Arrays.fill(slot, -1);

		// sort the jobs in descending order of their profits
		Collections.sort(jobs, (a, b) -> b.profit - a.profit);

		int maxProfit = 0;
		for (Job job : jobs) {
			for (int i = job.deadline - 1; i >= 0; i--) {
				if (i < t && slot[i] == -1) {
					slot[i] = job.jobId;
					maxProfit += job.profit;
					break;
				}
			}
		}

		System.out.println("jobs :");
		Arrays.stream(slot).filter(v -> v != -1).forEach(a -> System.out.println(a));
		System.out.println("max profit :");

		return maxProfit;
	}

	public static void main(String[] args) {
		// List of given jobs. Each job has an identifier, a deadline and
		// profit associated with it
		List<Job> jobs = Arrays.asList(new Job(1, 9, 15), new Job(2, 2, 2), new Job(3, 5, 18), new Job(4, 7, 1),
				new Job(5, 4, 25), new Job(6, 2, 20), new Job(7, 5, 8), new Job(8, 7, 10), new Job(9, 4, 12),
				new Job(10, 3, 5));

		System.out.println(new JobSequencingProblem().scheduleJobs(jobs));
	}

}

class Job {
	int jobId;
	int deadline;
	int profit;

	public Job(int id, int dl, int p) {
		this.jobId = id;
		this.deadline = dl;
		this.profit = p;
	}
}
