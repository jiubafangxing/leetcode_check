/**
 * 对于一个字符串，从前开始读和从后开始读是一样的，我们就称这个字符串是回文串。
 *
 * 例如"ABCBA","AA","A"是回文串，而"ABCD","AAB"不是回文串。
 *
 * 牛牛特别喜欢回文串，他手中有一个字符串s,牛牛在思考能否从字符串中移除部分(0个或多个)字符使其变为回文串，并且牛牛认 为空串不是回文串。
 *
 * 牛牛发现移除的方案可能有很多种，希望你来帮他计算一下一共有多少种移除方案可以使s变为回文串，对于两种移除方案如果移除的字符依次构成的序列不一样就是不同的方法。
 *
 * 输入描述
 *
 * 输入包括一个字符串s(1 <= iength(s) <= 50)，s中只包含大写字母。
 *
 * 输出描述
 *
 * 对于每个测试用例，输出一个正整数表示方案数。
 */
public class QuestionHighPalindromeRemovalSchemes{

	public static int count(String str){
		char[] arr = str.toCharArray();
		int[][] dp = new int[arr.length][arr.length];
		for(int i=0;i< arr.length; i++){
			if(i+1 < arr.length){
				dp[i][i] = 1;
				if(arr[i] != arr[i+1]){
					dp[i][i+1] = 2;
				}else{
					dp[i][i+1] = 3;
				}
			}
		}
		for(int i=arr.length-1; i>=0; i--){
			for(int j=i+2; j< arr.length; j++){
				int result = 0;
				if(i == 0 && j == 2){
					System.out.println("i"+i+"j"+j);
				}
				System.out.println("i"+i+"j"+j);
				result +=dp[i][j-1];
				System.out.println(dp[i][j-1]);
				result +=dp[i+1][j];
				System.out.println(dp[i+1][j]);
				result -=dp[i+1][j-1];
				System.out.println(dp[i+1][j-1]);
				if(arr[i] == arr[j]){
					result += dp[i+1][j-1]+1;	
				}
			  	dp[i][j] = result;	
			
			}
		}
		for (int i=0; i< arr.length; i++){
			for(int j=0; j< arr.length; j++){
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		return dp[0][arr.length-1];
	}

	public static void main(String[] args){
		String str = "ABA"	;
		int result = count(str);
		System.out.println("共"+result+"种方式变成回文串");
	}
}
