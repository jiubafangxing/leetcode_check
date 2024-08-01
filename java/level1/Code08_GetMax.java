public class Code08_GetMax{
	
	public static int max(int[] arr){
		if(null == arr || arr.length == 0){
			return Integer.MIN_VALUE;
		}
		int L = 0;
		int R = arr.length -1;
		int result = max(arr,L, R);
		return result;		
	}

	public static int max(int[] arr, int l , int r){
		if(l == r){
			return arr[l];
		}
		int mid = l+ ((r-l)>>1);
		int max1 = max(arr, l, mid);
		int max2 = max(arr, mid+1, r);
		return Math.max(max1, max2);
	}

	public static void main(String[] args){
		int[] arr = {1,2,4,6,7,12,5,45,12,3,12,23,1,12};
		int result = max(arr);
		System.out.println("max value is "+ result);
	}

}
