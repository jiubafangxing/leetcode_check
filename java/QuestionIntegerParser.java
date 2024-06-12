/*
 * The given task can be translated into English as follows:
 * Given a string, if the string conforms to the way people typically write an integer, return this number as an int type; 
 * if it does not conform or is out of range, return -1 or throw an error.
 * The requirements for conforming to the way people typically write an integer are as follows:
 * - Only the first position is allowed to have a sign, and it must be '-'. If the first position is '-', the second position cannot be '0'.
 * - If the first position is '0', there should be no other digits following it.
 * 题目3
 * 给定一个字符串，如果该字符串符合人们日常书写一个整数的形式，返回int类型的这个数；
 * 如果不符合或者越界返回-1或者报错。
 * 思路
 * 符合人们日常书写一个整数的形式有以下几条要求：
 * - 只有第一位允许是符号且为'-'，如果第一位是'-'，那么第二位不能是0
 */
public class QuestionIntegerParser{

	public int parseStr(String n){
		if(!isValid(n)){
			return 0;
		}
		char[] array = n.toCharArray();
		Boolean isNag = array[0] =='-';
		int nagFlag = 0;
		int startIndex = 0;
	    if(isNag){
			startIndex = 1;
			nagFlag= 1;
		}else{
			nagFlag= -1;
		}	
		int last = Integer.MIN_VALUE % 10;
		int start = 0;
		for(;startIndex< array.length;startIndex++){
			char cur = array[startIndex]	;
			if(start < Integer.MIN_VALUE/10  ){
				return 0;
			}else if(start == Integer.MIN_VALUE/10 && '0'-cur < last  ){
				return 0;
			}
			int ncur = '0' - cur;
			start = start *10+ ncur;
		}
		if(!isNag){
			if(start == Integer.MIN_VALUE){
				return 0;
			}
		}
		return start * nagFlag;	
	}

	public boolean isValid(String n){
		char[] array = n.toCharArray();
		if(null == array){
			return false;
		}
		Integer startNumIndex = 0;
		if(array[0] == '-'){
			if(!isStartNum(array[1])){
				return false;
			}
			startNumIndex = 1;
		}else{
			if(!isStartNum(array[0])){
				return false;
			}
		}
		for(;startNumIndex < array.length;startNumIndex++ ){
			if(!isNum(array[startNumIndex]))	{
				return false;
			}
		}
		return true;
	}

	public boolean isStartNum(char n){
		return n > '0' && n <='9';
	}

	public boolean isNum(char n ){
		return n >= '0' && n <= '9';
	}
	public static void main(String[] args){
		QuestionIntegerParser parser = new QuestionIntegerParser();
		int result = parser.parseStr("12");
		System.out.println(result);

	}


}
