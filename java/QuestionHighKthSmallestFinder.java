/*
 *
 * bfprt
 *在一个无序数组中，求最小的第k个数，
 */
public class QuestionHighKthSmallestFinder{
	public static int find(int[] arr, int start, int end, int limit){
		if(start > end){
			return -1;
		}
		int midNum= selectNum(arr, start , end, limit);
		int i = start;
		int j = end;
		while(i !=  j){
		
		}
		arr[i] = midNum;

		if(i == limit-1){
			return arr[i];
		}else if (i > limit-1){
			return find(arr,start, i-1, limit);	
		}else{
			return find(arr, i+1, end, limit);	
		}
	}

	public static void swap(int[] arr, int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}


	public static int selectNum(int[] arr, int start , int end, int limit){
		int size = (end -start +1);
		if(size <=3){
			insertSort(arr, start, end);
			return arr[limit];
		}
		int childSize = size/limit;
		int memLen = size/limit + (size%limit == 0  ? 0: 1);
		int[] memArr = new int[memLen];
		for(int i=0;i< memLen; i++){
			int[] childArr = new int[limit];
			int num = i;
			int no = 0;
			while((num+no*childSize) < arr.length && no < childArr.length){
				int ppos = num+no*childSize;
				childArr[no] = arr[ppos];
				no++;
			}
			int med = getMedian(childArr,0, no-1);
			if(med == 12){
				System.out.println("med:"+med);
			}
			memArr[i] = childArr[med];

		}
		return find(memArr, 0, memLen-1, memLen/2);	
	}


	public static int getMedian(int[] arr, int start , int end){
		if(start == end){
			return start;
		}
		insertSort(arr, start, end);	
		int size = (end-start+1);
		int offset = size %2 ==1 ? 1: 0;
		int midIdx = start+size/2 + offset-1;
		return midIdx;
	}

	public static void insertSort(int[] arr, int start, int end){
		for(int i=start;i<= end;i++){
			int tmp = i;
			while(tmp >start  && arr[tmp-1] >  arr[tmp]){
				int exc = arr[tmp-1]	;
				arr[tmp-1] = arr[tmp];
				arr[tmp] = exc;
				tmp--;
			}	
		}
	}
	// public static void main(String[] args){
	// 	int[] arr = {5,4,3,2,1,7,8,6,8,9};	
	// 	int result = find(arr,0, arr.length-1, 5);
	// 	System.out.println("最小的值是"+result);
	// }


	public static void main(String[] args){
		int[] arr = {5,4,3,2,1,7,8,6,8,9};
		int provit = 7;
		int small = -1;
		int cur = 0;
		int big = arr.length;
		while(cur != big){
			if(arr[cur] < provit){
				swap(arr, ++small, cur++);
			}else if (arr[cur] > provit){
				swap(arr, cur, --big);
			}else{
				cur++;
			}

		}
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
	}
}
