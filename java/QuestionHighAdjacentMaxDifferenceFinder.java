public class QuestionHighAdjacentMaxDifferenceFinder{
	public static int getAdjacentMaxDiffenence(int[] arr){
		Integer max= Integer.MIN_VALUE;
		Integer min = Integer.MAX_VALUE;
		for(int i = 0; i< arr.length; i++){
			Integer cur = arr[i]	;
			if(cur > max){
				max = cur;
			}
			if(cur < min){
				min = cur;
			}
		}
		if(max == min){
			return 0;
		}
		Integer interval = max - min ;	
		// if use this , maye error ,because interval maybe less than the real interval
		// int avgInterval = interval / (arr.length+1);
		int[][] buckets = new int[arr.length+1][2];
		for(int i = 0; i< arr.length; i++){
			int bucket = (arr[i]- min)*arr.length/interval;
			int[] bucketItems = buckets[bucket];
			if(null == bucketItems){
				bucketItems = new int[2];
				bucketItems[0] = arr[i];	
				bucketItems[1] = arr[i];	
			}else{
				if(bucketItems[0] > arr[i]){
					bucketItems[0] = arr[i];
				}
				if(bucketItems[1] < arr[i]){
					bucketItems[1] = arr[i];
				}
			}
			buckets[bucket] = bucketItems;	
		}
		int pre = buckets[0][1];
		Integer maxresult = Integer.MIN_VALUE;
		for(int i=1; i< buckets.length;i++){
			if(null != buckets[i]){
				Integer curInterval = buckets[i][1] - pre;	
				if(curInterval > maxresult){
					maxresult = curInterval;
				}
				pre = buckets[i][1];
			}
		}
		return maxresult;
	}


	public static void main(String[] args){
		int[] arr = {11,1,6,13,14,16,17,19,21}	;
		int res  = getAdjacentMaxDiffenence(arr);
		System.out.println(res);
	}

}
