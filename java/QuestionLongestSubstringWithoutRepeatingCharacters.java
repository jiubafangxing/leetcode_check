public class QuestionLongestSubstringWithoutRepeatingCharacters{

	public static int findLengthOfLongestSubstring(String str){
		char[] testArray = str.toCharArray();
		int[] indexArray= new int[256];
		for(int i =0;i<indexArray.length;i++){
			indexArray[i] = -1;
		}
		Integer prevIdx = 0;
		Integer max = 0;
		for(int i=0;i< testArray.length;i++){
			int idx = indexArray[(int)testArray[i]];
			prevIdx = Math.max(idx,prevIdx);
			int curLen = i - prevIdx;
			if(curLen > max){
				max = curLen;
			}
			indexArray[(int)testArray[i]] = i;
		}
		return max;
	}



	public static void main(String[] args){
		String teststr = "abcabcbb";	
		int result = findLengthOfLongestSubstring(teststr);
		System.out.println("result is "+ result);
		teststr =  "bbbbb";
		result = findLengthOfLongestSubstring(teststr);
		System.out.println("result is "+ result);
		teststr = "pwwkew";
		result = findLengthOfLongestSubstring(teststr);
		System.out.println("result is "+result);
	
	}
}
