public class QuestionHighNumberSplitting{

	public static int ways(int n){
		return process(1,n);
	}


	public static int process(int start, int n ){
		if(n == 0){
			return 1;
		}
		if(start > n){
			return 0;
		}
		Integer result = 0;
		for(int i = start;i<= n;i++){
			result +=process(i, n -i);
		}
		return result;	
	}


	public static int ways2(int n ){
		int[][] dp = new int[n+1][n+1];	
		for(int i=0;i< n+1;i++){
			dp[i][0] = 1;
		}
		for(int pre=n;pre>0;pre--){
			for (int j=pre;j< n+1;j++){
				int result =0;
				for(int i = pre;i<=j; i++){
					result +=dp[i][j-i];
				}
				dp[pre][j] = result;
			}
		}
		return dp[1][n];
	}


	public static void main(String[] args){
		int dest = 4;
		int result = ways(dest);	
		System.out.println("数字"+dest+"有"+result+"种分裂方法");
		result = ways2(dest);	
		System.out.println("数字"+dest+"[2]有"+result+"种分裂方法");
	}	
}
