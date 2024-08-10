
/*
 * In an ordered array, find the leftmost position that is greater than or equal to a certain number.
 * 在一个有序数组中，找>=某个数最左侧的位置 
 */
public class Code05_BSNearLeft{

	public static int nearestIndex(int[] arr, int dest){
		if(null == arr || arr.length == 0){
			return -1;
		}
		int L = 0;
		int R = arr.length-1;
		int mid = 0;
		int index = 0;
		while(L<=R){
			mid = L +((R-L) >>1);	
			if(arr[mid] >= dest){
				index = mid;	
				R = mid -1;
			}else{
				L = mid+1;
			}
		}
		return index;
	} 
	

	public static void main(String[] args){
		int[] arr = {1,2,3,4,5,7,10};
		int dest = 5;
		int result = nearestIndex(arr,dest);
		System.out.println("dest"+dest+"position is "+result);
		// dest = 6;
		// result = nearestIndex(arr,dest);
		// System.out.println("dest"+dest+"position is "+result);
	}
}
