public class QuestionHighLongestPalindromicSubsequence{

	public static int lps(String s){
		char[] arr = s.toCharArray();
		int[][] dp= new int[arr.length][arr.length];
		//设置基础的baseCase
		for(int i=0; i< arr.length;i++){
			dp[i][i] = 1;
			if(i+1 < arr.length){
				if(arr[i] == arr[i+1]){
					dp[i][i+1] = 2;	
				}else{
					dp[i][i+1] = 0;
				}
			}
		}

		for(int i=arr.length-1; i>= 0; i--){
			for(int j=i+1; j < arr.length;j++){
				char start = arr[i];	
				char end = arr[j];
				int result = 0;

				result =Math.max(dp[i][j-1], result);
				if(start == end){
					result = Math.max(dp[i+1][j-1]+2, result);
				}
				result = Math.max(dp[i+1][j-1], result);
				result =Math.max(dp[i+1][j], result);
				dp[i][j] = result;			
			}
		}
		for(int i=0; i< arr.length;i++){
			for(int j=0; j< arr.length;j++){
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}

		return dp[0][arr.length-1];

			
	}



	public static void main(String[] args){
		String str = "abac";	
		int result = lps(str);
		System.out.println("最长回文子序列长度为"+result);
	}

}
