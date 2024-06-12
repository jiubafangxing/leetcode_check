/*
 * 为了找到自己满意的工作，牛牛收集了每种工作的难度和报酬。
 * 牛牛选工作的标准是在难度不超过自身能力值的情况下，牛牛选择报酬最高的工作。
 * 在牛牛选定了自己的工作后，牛牛的小伙伴们来找牛牛帮忙选工作，牛牛依然使用自己的标准来帮助小伙伴们。
 * 牛牛的小伙伴太多了，于是他只好把这个任务交给了你。
 *
 * 输入描述:
 * 每个输入包含一个测试用例。
 * 每个测试用例的第一行包含两个正整数，分别表示工作的数量N(N<=100000)和小伙伴的数量M(M<=100000)。
 * 接下来的N行每行包含两个正整数，分别表示该项工作的难度Di(Di<=1000000000)和报酬Pi(Pi<=1000000000)。
 * 接下来的一行包含M个正整数，分别表示M个小伙伴的能力值Ai(Ai<=1000000000)。
 * 保证不存在两项工作的报酬相同。
 *
 * 输出描述:
 * 对于每个小伙伴，在单独的一行输出一个正整数表示他能得到的最高报酬。一个工作可以被多个人选择。
 *
 * 示例1
 * 输入
 * 3 3
 * 1 100
 * 10 1000
 * 1000000000 1001
 * 9 10 1000000000
 * 输出
 * 100
 * 1000
 * 1001
 *
 * In order to find a job that satisfies himself, Niuniu has collected the difficulty and compensation of each job.
 * Niuniu's standard for choosing a job is to choose the job with the highest compensation that does not exceed his own ability value.
 * After Niuniu has chosen his own job, Niuniu's friends come to ask Niuniu for help in choosing a job, and Niuniu still uses his own standard to help his friends.
 * There are too many friends of Niuniu, so he had to hand over this task to you.
 *
 * Input Description:
 * Each input contains a test case.
 * The first line of each test case contains two positive integers, representing the number of jobs N (N ≤ 100,000) and the number of friends M (M ≤ 100,000).
 * The following N lines each contain two positive integers, representing the difficulty Di (Di ≤ 1,000,000,000) and the compensation Pi (Pi ≤ 1,000,000,000) of each job.
 * The next line contains M positive integers, representing the ability values Ai (Ai ≤ 1,000,000,000) of M friends.
 * It is guaranteed that no two jobs have the same compensation.
 *
 * Output Description:
 * For each friend, output a positive integer on a separate line representing the highest compensation he can get. A job can be chosen by multiple people.
 *
 * Example 1
 * Input
 * 3 3
 * 1 100
 * 10 1000
 * 1000000000 1001
 * 9 10 1000000000
 * Output
 * 100
 * 1000
 * 1001
 */

import java.util.*;

public class QuestionJobSelector {

	public static class Job implements Comparable<Job> {

		public Integer difficulty;
		public Integer reward;

		public Job(Integer difficulty, Integer reward) {
			this.difficulty = difficulty;
			this.reward = reward;
		}

		@Override
		public int compareTo(QuestionJobSelector.Job o) {
			return this.difficulty == o.difficulty ? o.reward - this.reward : this.difficulty - o.difficulty;
		}
	}

	public static void main(String[] args) {
		// 模拟实际输入数据，包含至少15个工作实例
		Job[] jobs = {
				new Job(5, 200),
				new Job(3, 150),
				new Job(7, 250),
				new Job(2, 100),
				new Job(6, 210),
				new Job(1, 90),
				new Job(4, 180),
				new Job(8, 300),
				new Job(1, 120), // 相同难度，更高报酬
				new Job(9, 320),
				new Job(5, 220), // 相同难度，更高报酬
				new Job(10, 350),
				new Job(3, 160), // 相同难度，更高报酬
				new Job(11, 380),
				new Job(7, 260) // 相同难度，更高报酬
		};
		System.out.println("难度" + 10 + "reward" + findMaxReward(jobs, 10));
		System.out.println("难度" + 1 + "reward" + findMaxReward(jobs, 1));
		System.out.println("难度" + 7 + "reward" + findMaxReward(jobs, 7));
	}

	public static int findMaxReward(Job[] jobs, int ability) {
		Arrays.sort(jobs);
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		treeMap.put(jobs[0].difficulty, jobs[0].reward);
		Job pre = jobs[0];
		for (Job job : jobs) {
			if (job.difficulty != pre.difficulty && job.reward > pre.reward) {
				treeMap.put(job.difficulty, job.reward);
				pre = job;
			}
		}
		Integer result = treeMap.floorKey(ability);

		return result != null ? treeMap.get(result) : -1;

	}
}
