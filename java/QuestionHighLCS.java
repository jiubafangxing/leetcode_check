
public class QuestionHighLCS{

	public static String lcs(String str1, String str2){
		char[] char1s= str1.toCharArray();
		char[] char2s = str2.toCharArray();
		String result = lcs(char1s, char2s);
		return result;
	}

	public static class Info {
		int size;
		String str;	
	}
	public static String lcs(char[] arr1, char[] arr2){
		Info[][] dp = new Info[arr1.length][arr2.length];	
		for(int i=0; i< arr1.length; i++){
			Integer result = 0;
			Info info = new Info();
			info.str = "";
			if(arr1[i] == arr2[0]){
				result = 1;
				info.str = arr1[i]+"";
			}
			info.size = result;
			dp[i][0]= info;
		}

		for(int j=0; j< arr2.length; j++){
			Integer result = 0;
			Info info = new Info();
			info.str = "";
			if(arr1[0] == arr2[j]){
				result = 1;
				info.str = arr2[j]+"";
			}
			info.size = result;
			dp[0][j] = info;
		}

		for(int i=1; i< arr1.length;i++){
			for(int j=1;j<arr2.length;j++){
				if (arr1[i]	== arr2[j]){
					Info lastInfo =  dp[i-1][j-1];
					String newStr = lastInfo.str + ""+arr1[i];
					Info newInfo = new Info();
					newInfo.size = newStr.length();
					newInfo.str = newStr;
					dp[i][j] = newInfo;
				}else{
					Info info1 = dp[i-1][j];
					Info info2 = dp[i][j-1];
					if(info1.size > info2.size){
						dp[i][j] = info1;	
					}else{
						dp[i][j] = info2;
					}
				}
			}
		}
		return dp[arr1.length-1][arr2.length-1].str;
	}


	public static void main(String[] args){
		String a = "addbcii1";
		String b = "abckkidlai";
		String result = lcs(a,b);
		System.out.println("result is "+ result);
	}



}
