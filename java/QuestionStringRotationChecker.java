/**
 * 判断两个字符串是否互为旋转词
 * 【题目】如果一个字符串为str，把字符串str前面任意的部分挪到后面形成的字符串叫作str的旋转词。
 * 比如str=＂12345＂，str的旋转词有＂12345＂、＂23451＂、＂34512＂、＂45123＂和＂51234＂。
 * 给定两个字符串a和b，请判断a和b是否互为旋转词。
 *
 * If a string is named "str", the string formed by moving any part from the front to the back of "str" is called its "rotation word". 
 * For example, if "str" is "12345", its rotation words include "12345", "23451", "34512", "45123", and "51234". 
 * Given two strings, a and b, please determine if a and b are rotation words of each other.
 */
public class QuestionStringRotationChecker{

	public int ratate(String a, String b){
		if(a.length() != b.length()){
			return 0;
		}
		String newA = a+a;
		return kmpCheck(newA, b);
	}


	public int kmpCheck(String a, String b){
		int[] nexts = nexts(a);
		char[] array = a.toCharArray();
		char[] compare= b.toCharArray();
		int destFrom= 0;
		int compareFrom = 0;
		while(destFrom < a.length() && compareFrom < b.length() ){
			// If the characters at the current positions are equal, continue comparing
			if(array[destFrom] == compare[compareFrom]){
				destFrom++;
				compareFrom++;
			// If the current position being compared is at index 0 and the characters at the current positions are not equal,
			// there is no need to continue comparing the current position, so move to the next position to be compared	
			}else if(nexts[compareFrom] == -1){
				destFrom++;	
			}else{
				// If the characters at the current positions are not equal,
				// and the previous part of the string at the compare position has a non-zero value in the nexts array,
				// for example, in the string "aabaac", the current position is 'c' and 'aa' is similar,
				// so the next comparison can start directly from position 'b'
				compareFrom = nexts[compareFrom];
			}
		}
		return compareFrom == b.length() ? 1: 0;
	}


	public int[] nexts(String a ){
		char[] toGenerateNexts = a.toCharArray();
		int[] nexts = new int[a.length()];
		if(a.length() ==1){
			nexts[0] = -1;
			return nexts;	
		}else if (a.length() == 2){
			nexts[0] = -1;
			nexts[1] = 0;
			return nexts;
		}else{
			nexts[0] = -1;
			nexts[1] = 0;
			int cur = 0; 
			int index = 2;
			while(index < toGenerateNexts.length){
				if(toGenerateNexts[cur] == toGenerateNexts[index-1]){
					nexts[index++]=++cur;
				}else if(cur > 0){
					cur = nexts[cur];
				}else{
					nexts[index++] = 0;
				}
				
			}	
		}
		return nexts;

	}

	public static void main(String[] args){
		QuestionStringRotationChecker checker = new QuestionStringRotationChecker();
		int result = checker.ratate("12345","22451")	;	
		System.out.println(result);
		result = checker.ratate("12345","23451")	;	
		System.out.println(result);
	}

}
