/*
 *
 * bfprt
 *在一个无序数组中，求最小的第k个数，
 */
public class QuestionHighKthSmallestFinder{
	public static int find(int[] arr, int start, int end, int limit, int pos){
		if(start ==  end){
			return arr[end];
		}
		int midNum= selectNum(arr, start , end, limit, pos);
		int small = start -1;
		int big = end+1;
		int cur = start;
		while(cur != big){
			if(arr[cur] < midNum){
				swap(arr, cur++, ++small);
			}else if(arr[cur] > midNum){
				swap(arr, cur, --big);
			}else{
				cur++;
			}
		}
  		small = small+1;
		big = big-1;
		if(limit >= small && limit <= big){
			return arr[limit];
		}else if (limit < small){
			return find(arr, start, small-1, limit, pos);
				
		}else{
			return find(arr, big+1, end, limit, pos);
		}
	}

	public static void swap(int[] arr, int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}


	public static int selectNum(int[] arr, int start , int end, int limit, int pos){
		int size = (end -start +1);
		int childSize = size/pos;
		int memLen = childSize;
			memLen = childSize + (size%pos == 0  ? 0: 1);
		int[] memArr = new int[memLen];
		for(int i=0;i< memLen; i++){
			int biginI = start + i*pos;
			int endI = biginI + pos -1;
			int midIdx = getMedian(arr, biginI,Math.min(endI, end) );
			memArr[i] = arr[midIdx];
		}
		return find(memArr, 0, memLen-1, memLen/2,pos);	
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
			while(tmp > start  && arr[tmp-1] >  arr[tmp]){
				swap(arr, tmp-1, tmp);
				tmp--;
			}	
		}
	}
	public static void main(String[] args){
		int[] arr = {6,4,3,2,1,7};	
		int limit = 3;
		int result = find(arr,0, arr.length-1, limit-1,limit-1);
		System.out.println("最小的值是"+result);
	}


	// public static void main(String[] args){
	
	// }
}
