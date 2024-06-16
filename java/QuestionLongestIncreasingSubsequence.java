/*
 * longestIncreasingSubsequence
 * 最长递增子序列
 */
public class QuestionLongestIncreasingSubsequence{
	public static int longestIncreasingSubsequence (int[] testarray){
		int[] helpArray = new int[testarray.length];
		Integer mostLen = 0;
		for(int i=0;i< testarray.length; i++){
			if(mostLen ==0 ){
				helpArray[0] = testarray[0];
				mostLen++;
				continue;
			}
			if(testarray[i] > helpArray[mostLen-1] ){
				mostLen++;
				helpArray[mostLen-1] = testarray[i];
			}else{
				int pos = binarySearchPos(helpArray, mostLen, testarray[i]);
				if(pos > 0){
					helpArray[pos] = testarray[i];
				}
			}

		}
		return mostLen;	
	}

	public static int binarySearchPos(int[] arr, int len ,int v ){
		int from = 0;
		int to = len-1;
		int result = -1;
		while( to >= from ){
			int mid = from + (to-from)/2;
			if(arr[mid] > v){
				to = mid -1;
				result = mid;
			}else{
				from = mid +1;
			}
		}	
		return result;
	}
	public static void main(String[] args){
		    int[] nums1 = {1, 3, 6, 7, 9, 4, 10, 5, 6};
			int length = longestIncreasingSubsequence(nums1);
			System.out.println(length);
			int[] nums2 = {0, 1, 0, 3, 2, 3};
			length = longestIncreasingSubsequence(nums2);
			System.out.println(length);
	} 
}
