public class Code01_MergeSort{
	public static void mergeSort(int[] arr){
		if(null == arr || arr.length <= 1){
			return;
		}
		mergeSort(arr, 0, arr.length-1);
	}

	public static void mergeSort(int[] arr, int l, int r){
		if(l == r){
			return ;
		}
		int L = l;
		int R = r;
		int mid = L+((R-L)>>1);
		mergeSort(arr, L,mid);
		mergeSort(arr, mid+1,R);
		merge(arr, L, mid, R);
	}


	public static void merge(int[] arr, int l, int mid, int r){
		int p1 = l;
		int p2 = mid+1;
		int[] help = new int[arr.length];
		int idx = 0;
		while(p1 <= mid && p2 <=r){
			if(arr[p1]> arr[p2]){
				help[idx++] = arr[p1++];
			}else{
				help[idx++] = arr[p2++];
			}	
		}
		if(p1 <= mid){
			help[idx++] = arr[p1++];
		}
		if(p2 <= r){
			help[idx++] = arr[p2++];
		}
		for(int i=0;i< arr.length;i++){
			arr[i] = help[i];
		}
	}

	public static void main(String[] args){
		int[] arr = {1,12,5,34,23,13,345,5};	
		mergeSort(arr);
		for(int i=0;i< arr.length;i++){
			System.out.println(arr[i]);
		}

	}
}
