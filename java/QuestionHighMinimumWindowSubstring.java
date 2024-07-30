
/*
 * Problem Statement:
 * Given two strings, str1 and str2, find the smallest substring of str1 that contains all the characters of str2.
 *
 * Examples:
 * For str1 = "abcde" and str2 = "ac", the substring "abc" contains all the characters of str2 and is the shortest among all substrings of str1 that satisfy this condition, so the function should return 3.
 * For str1 = "12345" and str2 = "344", there is no substring of str1 that contains all the characters of str2, so the function should return 0.
 *
 * 给定字符串str1和str2，求str1的子串中含有str2所有字符的最小子串长度
 * 【举例】
 * str1="abcde"，str2="ac" 
 * 因为"abc"包含 str2 所有的字符，并且在满足这一条件的str1的所有子串中，"abc"是最短的，返回3。
 * 
 * str1="12345"，str2="344" 
 * 最小包含子串不存在，返回0
 */
public class QuestionHighMinimumWindowSubstring {

	public static int check(String str1, String str2){
		if(null == str1 || null == str2  || str1 == "" || str2 == ""){
			return -1;
		}
		char[] s1Array = str1.toCharArray();		
		char[] s2Array = str2.toCharArray();
		int s1Idx = 0;
		int s2Idx = 0;
		while(s1Idx < s1Array.length){
			if(s1Array[s1Idx] == s2Array[s2Idx] ){
				s2Idx++;		
			}
			s1Idx++;
			if(s2Idx == s2Array.length){
				return s1Idx;
			}
		}
		return -1;
	} 
	public static void main(String[] args) {
			String str1= "abcde";
			String str2= "ac";
			int result = check(str1, str2);
			System.out.println("resut is "+ result);
			str1 = "12345";
			str2 = "344";
			result = check(str1, str2);
			System.out.println("resut is "+ result);
	}
}
