
/*
 *
 * 求两个字符串的最长公共子串
  */
public class QuestionHighLongestCommonSubstring{


	public static String commonStr(String str1, String str2){
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();
		return commonStr(arr1, arr2);
	}

	public static class Pos{
		public int i;
		public int j;	
	}

	public static String commonStr(char[] arr1, char[] arr2){
		Pos pos = new Pos();
		pos.i = arr1.length-1;
		pos.j = 0;
		String resultStr= "";
		while(pos.i != -1 && pos.j != -1){
			if(pos.i == 1 && pos.j == 2){
				System.out.println("i is "+ pos.i + " j is "+ pos.j);
			}
			String commonStr = tryToFindStr(pos, arr1, arr2);
			move(pos, arr1.length, arr2.length);
			if(commonStr.length() > resultStr.length()){
				resultStr = commonStr;	
			}
		}
		return resultStr;
	}


	public static String tryToFindStr(Pos pos, char[] arr1, char[] arr2){
		int i = pos.i;
		int j = pos.j;
		String maxString = "";
		StringBuffer sb= new StringBuffer();
		while(i < arr1.length && j< arr2.length){
			if(arr1[i] == arr2[j]){
				sb.append(arr1[i]);
			}else{
				if(sb.length() > maxString.length()){
					maxString = sb.toString();	
				}
				sb = new StringBuffer();
			}	
			i++;
			j++;	
		}
		if(sb.length() > maxString.length()){
			maxString = sb.toString();	
		}
		return maxString;	
	}

	public static void move(Pos pos , int fromi, int fromj){
		if(pos.i >0){
			pos.i--;
		}else if (pos.j < fromj){
			pos.j++;
		}else{
			pos.i = -1;
			pos.j = -1;
		}
			
	}
	public static void main(String[] args){
		String str1 = "aaxxdbcd";	
		String str2 = "dcaccdbcb";
		String result = commonStr(str1, str2);
		System.out.println("result is "+ result);
	}
}
