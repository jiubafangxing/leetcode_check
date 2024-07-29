/*
 * 给定两个字符串str1和str2，判断str2是不是str1的旋变字符串。
 * Given two strings str1 and str2, determine whether str2 is a rotation of str1.
 */
public class QuestionHighStringRotationChecker {

	public static boolean isRotation(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() != str2.length()){
			return false;
		}
		char[] s1Array = str1.toCharArray();
		char[] s2Array = str2.toCharArray();

		return isRotation(s1Array,s2Array,0,0, s1Array.length);
	}


	public static boolean isRotation2(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() != str2.length()){
			return false;
		}
		char[] s1Array = str1.toCharArray();
		char[] s2Array = str2.toCharArray();
		int N = s1Array.length;
		int[][][] dp = new int[N+1][N][N];
		for(int i=0;i< N;i++){
			for(int j=0;j< N;j++){
				dp[1][i][j] = (s1Array[i]  == s2Array[j]);
			}
		}
		//still need to adjust
		for(int i=2;i< dp.length;i++){
			for(int start1=0;start1< i-1;start1++){
				for(int start2=0;start2< i-1;start2++){
					if((isRotation(s1Array,s2Array, start1, start2,i)&& isRotation(s1Array,s2Array, start1+ i,start2+i, size-i)) 
							||( isRotation(s1Array, s2Array, start1,start2+size-i,i ) && isRotation(s1Array, s2Array,start1+i,start2,size-i ))){
						result = true;
						break;
					}
					if(
						(dp[][start1][start2] && dp[i-1][start1+i][start2+i])
						||
						
						(dp[i-1][start1][start2] && dp[i-1][start1+i][start2+i])

					)

				}
			}

		}
	}

	public static boolean check(char[] str1, char[] str2){
		if(null == str1 || null == str2){
			return false;
		}
		if(str1.length != str2.length){
			return false;
		}
		return true;
	}


	public static boolean isRotation(char[] s1Array, char[] s2Array, int start1, int start2, int size){
		if(size == 1){
			return s1Array[start1] == s2Array[start2];
		}
		boolean result = false;
		for(int i=1; i< size;i++){
			if((isRotation(s1Array,s2Array, start1, start2,i)&& isRotation(s1Array,s2Array, start1+ i,start2+i, size-i)) 
					||( isRotation(s1Array, s2Array, start1,start2+size-i,i ) && isRotation(s1Array, s2Array,start1+i,start2,size-i ))){
				result = true;
				break;
			}
		}
		return result;	
	}





	public static void main(String[] args){
		String str1 = "abcd";
		String str2 = "cdab";
		System.out.println(isRotation(str1,str2));

	}
}
