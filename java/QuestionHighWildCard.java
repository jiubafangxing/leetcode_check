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
	
	
	public static boolean match2(String matchStr, String exp){

		char[] marr= matchStr.toCharArray();	
		char[] earr = exp.toCharArray();
		boolean[][] dp = initdp(marr,earr);
		for(int mi=marr.length-1;mi>=0;mi--){
			for(int ei=earr.length-2;ei>=0;ei--){
				if('*' != earr[ei+1] ){
					dp[mi][ei] =  (earr[ei]=='.' || earr[ei] ==marr[mi]) && dp[ mi+1][ei+1];
				}else{
					int nmi = mi;
					while(marr.length != nmi && (marr[nmi] == earr[ei]|| earr[ei] == '.')){
						if(dp[ nmi][ei+2]){
							dp[mi][ei] =true;			
							break;
						}
						nmi++;	
					}
					if(dp[mi][ei] != true){
						dp[mi][ei] = dp[nmi][ ei+2];
					}
				}
			}
		}
		return dp[0][0];
	}

	public static boolean[][] initdp(char[] mArr, char[] eArr){
		boolean[][] dp = new boolean[mArr.length+1][eArr.length+1];
		dp[mArr.length][eArr.length] = true;
		if(mArr.length > 0 && eArr.length >0 ){
			if((eArr[eArr.length-1] == '.' ) || mArr[mArr.length-1] == eArr[eArr.length-1])	{
				dp[mArr.length-1][eArr.length-1]= true;
			}
		}
		for(int i =eArr.length-2; i>=0;i-=2){
			if(eArr[i] != '*' && eArr[i+1] == '*'){
				dp[mArr.length][i] = true;	
				break;
			}	
		}
		return dp;
	
	}
	public static void main(String[] args){
		String match = "aaaazdasdf";
		String exp  = "a..azdasdf";
	 	boolean matchResult = match(match, exp);
	 	boolean matchResult2 = match2(match, exp);
		System.out.println("match result is "+ matchResult);
		System.out.println("match result 2 is "+ matchResult);

	}
 

}
