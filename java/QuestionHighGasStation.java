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
	
	public static boolean[] res(int[] oil, int[] distance){
		boolean[] result = null;
		if(null == oil || null == distance || oil.length != distance.length){
			result = new boolean[1];
			return result;
		}	
		int[] leftOil= new int[oil.length];
		for(int i =0;i< oil.length;i++){
			leftOil[i]= oil[i]-distance[i];	
		}
		int init = findBiggerThanZero(leftOil);
		if(init == -1) {
			result = new boolean[leftOil.length];
			return result;
		}else{
		
			result = expore(leftOil, init);
			return result;
		}

	}


	public static boolean[] expore(int[] leftOil, int init){
		boolean[] res = new boolean[leftOil.length];
		int start = init;	
		int rest = 0;
		int need = 0;
		int len = leftOil.length;
		int end = nextIndex(init,len);
		do{	
			if(start != init &&  start == lastIndex(end, len)){
 					break;
			}
			if(leftOil[start] < need){
				need -= leftOil[start];	
			}else{
				rest = leftOil[start] - need;
				need = 0;
				while(rest>0 && start != end){
					rest += leftOil[end];
					end = nextIndex(end, len);
				}
				if(rest >= 0 ){
					res[start] = true;
					connectGood(leftOil,lastIndex(start, len), init,res);
					break;
				}
			}
			start = lastIndex(start,len);	
		}while(start != init);
		return res;
	}


	public static int lastIndex(int index, int len){
		return index == 0 ? (len - 1) : index - 1;
	}


	public static int nextIndex(int index, int len){
		return index == len - 1 ? 0 : (index + 1);
	}


	public static void connectGood(int[] leftOil, int last, int init, boolean[] res ){
		int need = 0;
		while(last != init){
			if(leftOil[last]>= need){
				need = 0;	
				res[last] = true;
			}else{
				need -= leftOil[last];
			}
			last = lastIndex(last, leftOil.length);
		}
	}


	public static int findBiggerThanZero(int[] l){
		int res = -1;
		for(int i=0;i<l.length;i++){
			if(l[i]>= 0){	
				res = i;
			}
		}
		return res;
	}

	public static void main(String[] args){
		int[] oil = {3,2,1,4,2};
		int[] distance = {4,1,3,2,1}; 	
		boolean[] results = res(oil, distance);	
		for(int i =0;i< results.length;i++){
			System.out.println("index"+i+", value:"+results[i]);
		}
		int[] oil2 = {30, 5, 10};
		int[] distance2 = {10, 10, 10};
		results = res(oil2, distance2);	
		for(int i =0;i< results.length;i++){
			System.out.println("index"+i+", value:"+results[i]);
		}
	}
}
