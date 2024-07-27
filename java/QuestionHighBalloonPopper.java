/**
 * Given an array arr representing a row of balloons with scores. Each time a balloon is popped, a score is obtained.
 * Let's assume the score for popping a balloon is X. The rules for obtaining the score are as follows:
 * 
 * If there is an unpopped balloon to the left of the popped balloon, find the nearest balloon to the popped balloon,
 * and assume its score is L; if there is an unpopped balloon to the right of the popped balloon, find the nearest one,
 * and assume its score is R. The score obtained is L * X * R.
 * 
 * If there is an unpopped balloon to the left of the popped balloon, find the nearest one, and assume its score is L;
 * if all balloons to the right of the popped balloon have already been popped. The score obtained is L * X.
 * 
 * If all balloons to the left of the popped balloon have already been popped; if there is an unpopped balloon to the right,
 * find the nearest one, and assume its score is R; if all balloons to the right have already been popped.
 * The score obtained is X * R.
 * 
 * If all balloons to the left and right of the popped balloon have already been popped. The score obtained is X.
 * 
 * The goal is to pop all balloons and obtain the score for each pop. By choosing the order in which to pop the balloons,
 * different total scores can be achieved. Please return the maximum score that can be obtained.
 *
 * KMP算法扩展题目二 给定一个数组 arr，代表一排有分数的气球。每打爆一个气球都能获得分数，假设打爆气球 的分数为 X，
 * 获得分数的规则如下:
 * 
 * 1) 如果被打爆气球的左边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为 L;
 *    如果被打爆气球的右边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为 R。
 *    获得分数为 L*X*R。
 * 
 * 2) 如果被打爆气球的左边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为 L;
 *    如果被打爆气球的右边所有气球都已经被打爆。获得分数为 L*X。
 * 
 * 3) 如果被打爆气球的左边所有的气球都已经被打爆;
 *    如果被打爆气球的右边有没被打爆的气球，找到离被打爆气球最近的气球，假设分数为 R;
 *    如果被打爆气球的右边所有气球都已经被打爆。获得分数为 X*R。
 * 
 * 4) 如果被打爆气球的左边和右边所有的气球都已经被打爆。获得分数为 X。
 * 
 * 目标是打爆所有气球，获得每次打爆的分数。通过选择打爆气球的顺序，可以得到不同总分，请返回能获得的最大分数。
 */
public class QuestionHighBalloonPopper {


	public static int maxCoin(int[] arr){
		int[] narr = ext(arr);	
		return process(narr,1, narr.length-2);
	}


	public static int process(int[] arr, int start, int to){
		if(start == to){
			return arr[start-1] * arr[to+1] * arr[start];
		}
		Integer max = Math.max(  arr[start-1]* arr[start]* arr[to+1]+process(arr, start+1, to), arr[start-1]*arr[to]*arr[to+1]+process(arr, start, to-1));
		int i = start+1;
		while(i< to){
			max = Math.max(max,arr[start-1] *arr[i]* arr[to+1] + process(arr, start, i-1)+ process(arr,i+1, to));
			i++;		
		}
		return max;
	
	}
	

	public static int maxCoin2(int[] arr){
		int[] narr = ext(arr);	
		int[][] dp = new int[narr.length][narr.length];
		for(int i=1;i< narr.length-2;i++){
			dp[i][i] = narr[i]* narr[i+1]*narr[i-1];		
		}
		for(int i=narr.length-2; i>0 ;i-- ){
			for(int j=i+1;j<=narr.length-2;j++
			   ){
				int start = i;
				int to = j;
				Integer max = Math.max(  narr[start-1]* narr[start]* narr[to+1]+dp[ start+1][ to], narr[start-1]*narr[to]*narr[to+1]+dp[ start][ to-1]);
			
				int ni = start+1;
				while(ni< to){
					max = Math.max(max,narr[start-1] *narr[ni]* narr[to+1] + dp[ start][ ni-1]+ dp[ni+1][to]);
					ni++;		
				}
				dp[i][j]= max;
			}
		}
		return dp[1][narr.length-2];		
	}

	public static int[] ext(int[] arr){
		int[] narr = new int[arr.length+2];	
		narr[0] = 1;
		narr[narr.length-1] = 1;
		for(int i=1; i< narr.length-1; i++){
			narr[i] = arr[i-1];	
		}
		return narr;
	}

	public static void main(String[] args){
		int[] arr = {3,2,5};	
		int result = maxCoin(arr);
		int result2 = maxCoin2(arr);
		System.out.println("balloon popper result is "+ result);
		System.out.println("balloon popper result2 is "+ result2);
			
	}    
}
