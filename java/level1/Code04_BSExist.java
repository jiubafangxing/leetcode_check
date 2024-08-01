public class Code04_BSExist{

	public static  boolean exist(int[] arr, int destNum){
		int L = 0;
		int R = arr.length-1;
		while(L<R){
			int mid = (L+R)>>1;
			if(arr[mid]> destNum){
				R = mid;
			}else if(arr[mid]< destNum){
				L = mid;
			}else{
				return true;
			}
		}
		return false;		
	}

	public static void main(String[] args){
		int[] arr = {1,3,5,6,7};
		int dest = 4;	
		System.out.println("dest:"+dest+"存在"+exist(arr,dest));
		dest = 5;
		System.out.println("dest:"+dest+"存在"+exist(arr,dest));
	}
} 
