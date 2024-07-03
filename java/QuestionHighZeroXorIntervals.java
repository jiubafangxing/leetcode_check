import java.util.*;

/*
 *给出n个数字 a_1,…,a_n，问最多有多少不重叠的非空区间，使得每个区间内数字的xor（异或和）都等于0。
 */
public class QuestionHighZeroXorIntervals{

	public static int findMaxIntervals(int[] arr){
		if(null == arr){
			return 0;
		}
		if(arr.length == 1 && arr[0] != 0){
			return 0;
		}
		Map<Integer,Integer> sumIndex= new HashMap<>();
		Integer[] dp = new Integer[arr.length];
		sumIndex.put(0, -1);
		Integer sum = 0;

		for(int i=0; i< arr.length; i++){
			Integer nsum = sum ^ arr[i];		
			Integer lastIndex = sumIndex.get(nsum);
			if(null != lastIndex && lastIndex  != -1){
				Integer curTimes = dp[lastIndex] +1;
				Integer postTimes = dp[i-1];
				if(curTimes > postTimes){
					dp[i] = curTimes;
				}else{
					dp[i] = postTimes;
				}
			}else{
				if(i ==0 ){
					dp[i] = nsum == 0?1:0;	
				}else{
					dp[i] = dp[i-1];
				}
			}
			sumIndex.put(nsum, i);
			sum = nsum;
		}
		return dp[arr.length-1];	
	}

	public static void main(String[] args){
		int[] arr  = {1,2,3,0,3,3,6,7,1,0,2,3,2,1,0};
		int result = findMaxIntervals(arr);	
		System.out.println(result);
	}

}
