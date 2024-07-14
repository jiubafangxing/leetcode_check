
/*
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是
回文串,返回回文串的个数
 *
 */
public class QuestionHighPalindromePartitioning{
	
	public static int countPartition(String str){
		char[] arr = str.toCharArray();	
		boolean[][] dp = new boolean[arr.length][arr.length];
		for(int i=0;i< arr.length;i++){
			dp[i][i] = true;
			if(i+1 < arr.length){
				if(arr[i] == arr[i+1]){
					dp[i][i+1] = true;	
				}else{
					dp[i][i+1]= false;
				}	
			}	
		}
		for(int i = arr.length; i >= 0 ; i--){
			for(int j= i+1; j< arr.length; j++){
				if(arr[i] == arr[j]){
					if(i+1 <= j-1){
						dp[i][j] = dp[i+1][j-1];	
					}else{
						dp[i][j] = true;	
					}	
				}else{
					dp[i][j] = false;	
				}	
			}		
		}
		return process(arr, 0, dp);
	}

	public static int process(char[] arr, int start, boolean[][] dp ){
		if(start == arr.length){
			return 0;
		}
		Integer ans = Integer.MAX_VALUE;
		for(int i= start; i< arr.length; i++){
			if(dp[start][i])	{
				 ans = Math.min(ans ,process(arr, i+1, dp)+1);	
			}
		}	
		return ans;
	}


	public static void main(String[] args){
		String str = "ACDCDCDAD";
		int result = countPartition(str);	
		System.out.println("result is "+result);
	}
}
