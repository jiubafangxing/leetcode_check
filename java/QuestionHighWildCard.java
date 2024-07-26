/*
 *
 *The task is to determine if a string composed of characters from the set [a-z] matches a pattern string that includes the wildcard characters '?' and ''. The wildcard '?' matches any single character, and the '' matches zero or more of any characters. The length of the strings will not exceed 100, and the strings are not empty.

Input Description:

The input consists of two strings: str and pattern. The length of each string is between 1 and 100 characters.
Output Description:

Return true to indicate a match, or false to indicate no match.
 */
public class QuestionHighWildCard{
	
	public static boolean match(String matchStr, String exp){
		char[] mArr = matchStr.toCharArray();	
		char[] expArr = exp.toCharArray();
		return match(mArr, expArr, 0,0);
	}

	public static boolean match(char[] marr, char[] earr, int mi, int ei){
		if(ei == earr.length){
			return marr.length == mi;
		}
		if(ei+1 == earr.length || earr[ei+1] != '*'){
			return (mi != marr.length) && (earr[ei]=='.' || earr[ei] ==marr[mi]) && match(marr,earr, mi+1,ei+1);
		}
		while(marr.length != mi && (marr[mi] == earr[ei]|| earr[ei] == '.')){
			if(match(marr, earr, mi,ei+2)){
				return true;
			}
			mi++;	
		}
		return match(marr, earr, mi, ei+2);
		
	}

	public static void main(String[] args){
		String match = "aaaazdasdf";
		String exp  = "a..azdasdf";
	 	boolean matchResult = match(match, exp);
		System.out.println("match result is "+ matchResult);

	}
 

}
