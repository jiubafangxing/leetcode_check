/*
 * There are N gas stations forming a circle. Given two arrays of non-negative integers, oil and dis, both of length N (where N > 1),
 * oil[i] represents how many kilometers the car can travel with the amount of oil available at the i-th gas station,
 * and dis[i] represents the distance between the i-th gas station and the next gas station in the circle.
 *
 * Assuming you have a car with a sufficiently large fuel tank but no oil at the start,
 * if the car starts from the i-th gas station and can eventually return to this gas station,
 * then the i-th gas station is considered a good starting point; otherwise, it is not.
 *
 * Please return a boolean array res of length N, where res[i] indicates whether the i-th gas station
 * is a good starting point or not.
 *
 * N个加油站组成一个环形，给定两个长度都是N的非负数组 oil和dis(N>1)，oil[i]代表
 * 第i个加油站存的油可以跑多少千米，dis[i]代表第i个加油站到环中下一个加油站相隔
 * 多少千米。
 * 假设你有一辆油箱足够大的车，初始时车里没有油。如果车从第i个加油站出发，最终
 * 可以回到这个加油站，那么第i个加油站就算良好出发点，否则就不算。
 * 请返回长度为N的boolean型数组res，res[i]代表第 i 个加油站是不是良好出发点。
 */
public class QuestionHighGasStation{
	
	public static int[] res(int[] oil, int[] distance){
		if(oil.length == 1){
			 int[] result = {false};
			 result[0] = (oil[0]-distance[0] >=0	);
		}
		int[] leftOil = new int[oil.length];
		for(int i=0;i< oil.length;i++){
			leftOil[i] = oil[i]- distance[i];
		}
		int len = leftOil.length;
		int start =0;
		int end = 1;
		int rest = 0;
		int need = 0;
		if(leftOil[0]>0){
			rest = leftOil[0];
		}else{
			need = need -leftOil[0];
		}
		while(nextStartPos(start,len) == end ){
			
		}
	
	}

	public static int nextStartPos(int cur, int len){
		int start = cur-1;
		if(start <0){
		   start = len + start	;
		}
		return start;	
	}


	public static int nextEndPost(int cur, int len){
		int end = cur+1;
		if(end >= len){
			return len - end;
		}
		return end;

	}

	

	public static void main(String[] args){
		int[] oil = {3,2,1,4,2};
		int[] distance = {4,1,3,2,1}; 	
	
	}
}
