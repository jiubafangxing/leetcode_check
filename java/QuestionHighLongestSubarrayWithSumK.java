/*
* 给定一个无序数组 arr，数组中的每个值都是正数。再给定一个正数 k。
* 问题是：求 arr 的所有子数组中，元素之和等于 k 的最长子数组的长度。
* 要求：时间复杂度为 O(N)，额外空间复杂度为 O(1)。
* 例如：输入：arr=[1,2,1,1,1]，k=3。
* 输出：累加和为 3 的最长子数组为 [1,1,1]，所以结果返回 3。
*/
public class QuestionHighLongestSubarrayWithSumK{

	public static int sumK(int[] arr, int K){
		Integer preIndex = 0;
		Integer postIndex = 0;
		Integer curSum = 0;
		Integer resultLength = 0;
		while(postIndex < arr.length){
			Integer newSum = curSum + arr[postIndex];
			if(newSum < K){
				curSum = newSum;
				postIndex++;
			}else if(newSum == K){
				Integer l  = postIndex - preIndex +1;	
				if( l > resultLength){
					resultLength = l;	
				}
				preIndex++;
				curSum -= arr[preIndex];
			}else{
				preIndex++;
				curSum -= arr[preIndex];
			}
		}
		return resultLength;
	}


	public static void main(String[] args){
		int[] arr = {1,2,1,1,1,1};	
		int result = sumK(arr, 4);
		System.out.println("result is "+ result);
	
	}

}
