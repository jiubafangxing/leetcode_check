import java.util.*;
/**
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 */
public class QuestionRemoveDuplicateLetters{


	public static String p(char[] charArray){
		Map<Character,Integer> chartimesMap = new HashMap<>();
		Map<Character,Integer> chartimesMap2 = new HashMap<>();
		for(char c:charArray){
			Integer times = chartimesMap.get(c);	
			if(null == times){
				times = 1;
			}else{
				times++;
			}
			chartimesMap.put(c, times);
			chartimesMap2.put(c, times);
		}
		if(chartimesMap.size() == charArray.length){
			StringBuilder sb = new StringBuilder();
			for(char a : charArray){
				sb.append(a+"");
			}
			return sb.toString();	
		}
		Integer mostSmallCharIndex =  0;
		char mostSmallChar = charArray[0];
		for(int i=0;i< charArray.length;i++){
			if(charArray[i] < mostSmallChar ){
				mostSmallChar = charArray[i];	
				mostSmallCharIndex = i;
			}
			Integer ntimes = chartimesMap.get(charArray[i])-1;
			chartimesMap.put(charArray[i], ntimes);
			if(ntimes == 0){
				break;
			}
		}	
		int newArrayLength = charArray.length-mostSmallCharIndex-chartimesMap2.get(mostSmallChar);
		char[] newArray = new char[newArrayLength];
		int j = 0;
		for(int i=mostSmallCharIndex+1;i< charArray.length;i++){
			if(charArray[i] != mostSmallChar ){
				newArray[j++] = charArray[i];
			}
		}
		return mostSmallChar+""+ p(newArray);
	}

	public static String remove(String str){
		char[] arr = str.toCharArray();
		return p(arr);
	}

	public static void main(String[] args){
		String a = "cbacdcbc";
		String b  = remove(a);
		System.out.println("result is  :"+ b);
	}
}
