/*
 * Title: Niu Niu's Knapsack Problem
 * Description:
 * Niu Niu is preparing to go on a spring outing organized by his school. Before departing, Niu Niu plans to pack some snacks into his backpack, which has a capacity of w.
 * There are n bags of snacks at Niu Niu's home, and the volume of the i-th bag of snacks is v[i].
 * Niu Niu wants to know how many ways there are to pack the snacks without exceeding the backpack's capacity (including the case where the total volume is 0).
 * Input Description:
 * The input consists of two lines.
 * The first line contains two positive integers n and w (1 <= n <= 30, 1 <= w <= 2 * 10^9), representing the number of snacks and the capacity of the backpack.
 * The second line contains n positive integers v[i] (0 <= v[i] <= 10^9), indicating the volume of each bag of snacks.
 * Output Description:
 * Output a positive integer, indicating the total number of snack packing methods that Niu Niu has.
 * 牛牛的背包问题
 * 题目描述
 * 牛牛准备参加学校组织的春游, 出发前牛牛准备往背包里装入一些零食, 牛牛的背包容量为w。
 * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]。
 * 牛牛想知道在总体积不超过背包容量的情况下,他一共有多少种零食放法(总体积为0也算一种放法)。
 * 输入描述:
 * 输入包括两行
 * 第一行为两个正整数n和w(1 <= n <= 30, 1 <= w <= 2 * 10^9),表示零食的数量和背包的容量。
 * 第二行n个正整数v[i](0 <= v[i] <= 10^9),表示每袋零食的体积。
 * 输出描述:
 * 输出一个正整数, 表示牛牛一共有多少种零食放法
 */

import java.util.Arrays;

public class QuestionSnackPackProblem{
	public int countWaysToPackSnacks( int w, int[] v) {
		Arrays.sort(v);
		Integer allSum = 0;
		for(int i=0;i<=w;i++){
			allSum += process(0,i,v);
		}
		// System.out.println(process(0,w,v));
		return allSum;
	}
	//递归方式解决
	public int process(int n , int w, int[] v){
		if(w == 0){
			return 1;
		}
		if(w < 0){
			return 0;
		}
		if(n ==v.length){
			return 0;
		}
		int used = 	process(n+1, w-v[n],v);
		int dontUse =	process(n+1, w,v);
		return used+dontUse ; 
	}
	
	public int countWaysToPackSnacks2(int w,int[] v){
		int[][] dp = new int[w+1][v.length+1];
		for(int i=0; i< v.length+1;i++){
			dp[0][i]= 1;	
		}
		for(int j = 0; j<w+1; j++ ){
			dp[j][v.length] = 0;
		}

		for(int i=1; i<=w; i++){
			for(int j=v.length-1; j >=0  ;j--){
				Integer sumItem =0;
				if(j == 0){
					System.out.println(j);
				}
				if(i -v[j]==0){
					sumItem += 1;
				}else if( i - v[j] > 0 ){
				  	sumItem += dp[i-v[j]][j+1];
				}
				sumItem += dp[i][j+1];
				dp[i][j] = sumItem;
			}	
		
		}
		Integer result = 0;
		for(int i=0;i< w+1;i++){
			result+=dp[i][0];	
		}
		return result;
	}	

	public static void main(String[] args){
		// 假设零食的数量
		int n = 5;
		// 假设背包的容量
		int w = 10;
		// 定义一个零食数组，这里使用硬编码的值
		int[] snacks = {2, 3, 4, 5, 6};
		QuestionSnackPackProblem snack = new QuestionSnackPackProblem();
		int result = snack.countWaysToPackSnacks2(w,snacks);
		System.out.println(result);
	}
}
