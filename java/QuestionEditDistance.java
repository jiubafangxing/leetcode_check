/*
 * Given two strings str1 and str2, and three integers ic, dc, and rc, which represent the costs of inserting, deleting, and replacing a character respectively, 
 * find the minimum cost to transform str1 into str2.
 *
 * The data range is as follows:
 * - The length of str1 and str2 is between 0 and 5000 characters, inclusive.
 * - The costs ic, dc, and rc are between 0 and 10,000, inclusive.
 * - 0 ≤ |str1|, |str2| ≤ 5000,
 * - 0 ≤ ic, dc, rc ≤ 10000.
 */
public class QuestionEditDistance {


    public static int editDistance(String str1, String str2, int ic, int dc, int rc){
	int len1 = str1.length();    
	int len2 = str2.length();    
	int[][] editArray = new int[len1+1][len2+1];
	char[] str1Chars = str1.toCharArray();
	char[] str2Chars = str2.toCharArray();
	for(int i=0;i< len1+1;i++){
		for(int j=0;j< len2+1;j++){
			if(i == 0){
				editArray[i][j] = j * ic;					
			}
			if(j == 0){
				editArray[i][j] = i * dc;					
			}	
		}	
	}
	for(int i=1;i< len1+1;i++){
		for(int j=1;j< len2+1 ;j++){

			char str1char = str1Chars[i-1];	
			char str2char = str2Chars[j-1];	
			int pain = 0;
			if(str1char == str2char){
				pain = editArray[i-1][j-1];	
			}else{
				pain = editArray[i-1][j-1]+rc;	
			}
			pain = Math.min(pain,editArray[i-1][j] +dc);
			pain = Math.min(pain, editArray[i][j-1]+ ic);
			editArray[i][j] = pain;
		}
	}
   	return editArray[len1][len2]; 
    }


    public static void main(String[] args){
        String str1 = "horse";
        String str2 = "ros";
        int ic = 1; // 插入的代价
        int dc = 1; // 删除的代价
        int rc = 1; // 替换的代    
		int result  = editDistance(str1, str2, ic,dc,rc);    
		System.out.println("result is "+result);
    }

}
