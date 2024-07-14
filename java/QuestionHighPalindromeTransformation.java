/**
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 */
public class QuestionHighPalindromeTransformation{

	public static int[][] transformationNum(String str){
		char[] arr = str.toCharArray();			
		int[][] dp = new int[arr.length][arr.length]; 
		for(int i=0; i< arr.length; i++){
			dp[i][i] = 0;	
			if(i+1 < arr.length){
				if(arr[i] != arr[i+1]){
					dp[i][i+1] = 1;
				}else{
					dp[i][i+1] = 1;
				}	
			}
		}

		for(int i=arr.length-1; i>= 0; i--){
			for(int j=i+1;j< arr.length;j++){
				Integer result = -1;
				if(arr[i] == arr[j]){
					result = dp[i+1][j-1];	
				}else{
					result = dp[i+1][j-1] +2;
					result = Math.min(result, dp[i][j-1]+1);
				}
				dp[i][j] = result;	
			}
		}
		return dp;
	}

	public static String trans(String str){
		int[][] dp = transformationNum(str);
		char[] carry  =  str.toCharArray();
		int start=0;
		int end = str.length()-1;
		int num = dp[0][str.length()-1];
		char[] resultArray = new char[str.length()+num];
		int moveEnd = resultArray.length-1;
		int moveStart = 0;
		while(dp[start][end] != 0){
			if(start == 2 && end == 4){
				System.out.println("start"+start+"end"+end);
			}
				Integer way = 0;
				if(carry[start] == carry[end]){
					way = 0;
					start = start+1;
					end = end-1;
				}else{
					int t1 = dp[start+1][end-1] +2;
					int t2 = dp[start][end-1]+1;
					if(t1 < t2){
						way = 1;
						start = start+1;
						end =  end-1;
					}else{
						way = 2;
						end = end-1;
					}
				}

				if(way == 0){
					resultArray[moveEnd--]  = carry[end+1]; 
					resultArray[moveStart++] = carry[start-1];
				}else if (way == 1){
					resultArray[moveEnd--] = carry[start-1];	
					resultArray[moveStart++] = carry[start-1];
					resultArray[moveStart++] = carry[end+1];
					resultArray[moveEnd--] = carry[end+1];
				}else{
					resultArray[moveStart++] = carry[end+1];	
					resultArray[moveEnd--] = carry[end+1]; 
				}
		}
		for(int i = start;i<= end; i++){
			resultArray[moveStart++] = carry[i];
		}	
		StringBuffer sb = new StringBuffer();
		for(int i=0; i< resultArray.length; i++){
			sb.append(resultArray[i]);			
		}
		return sb.toString();
	}


	public static void main(String[] args){
		String str = "aavdcaa";
		String result= trans(str);
		System.out.println("结果"+result);
	
	}
}
