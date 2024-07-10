public class QuestionHighMaxSubarraySumK{
	public static int maxSubarraySumk(int[] arr, int k){
		Integer[] minSum = new Integer[arr.length];
		Integer[] minSumIndexEnd = new Integer[arr.length];
		for(int i= arr.length-1;i>=0;i--){
			if(i+1 > arr.length-1){
				minSum[i] = arr[i];
				minSumIndexEnd[i]= i;
			}else{
				if(minSum[i+1] + arr[i] <= k){
					minSum[i] = minSum[i+1]+arr[i];	
					minSumIndexEnd[i]=minSumIndexEnd[i+1 ] ;
				}else{
					minSum[i] = arr[i];	
					minSumIndexEnd[i]=i;
				}
			}
		}
		Integer mostLen = 0;
		Integer start =0;
		while(start < arr.length){
			Integer tmpSum = 0;
			Integer newIndex = start;
			while(newIndex < arr.length ){
				tmpSum += minSum[newIndex];
				if(tmpSum > k){
					break;
				}
				newIndex = minSumIndexEnd[newIndex]+1;	 
			}
			Integer newLen = newIndex - start;
			if(newLen > mostLen){
				mostLen = newLen;
			}
			start++;
		}
		return mostLen;	
	}
	public static void main(String[] args){
		int[] arr = {3,-2,-4,0,6};	
		int result = maxSubarraySumk(arr, -2);
		System.out.println("result is "+ result);

	}
	
}
