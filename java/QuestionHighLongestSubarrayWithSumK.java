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
