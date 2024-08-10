public class Code02_BubbleSort2{

	public static void bubbleSort(int[] arr){
		for(int i= arr.length-1; i>0; i--){
			for(int j=1;j<=i; j++){
				if(arr[j] < arr[j-1]){
					swap(arr, j, j-1);	
				}	
			}
		}
	}

	public static void swap(int[] arr, int i , int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static int[] copyArr(int[] arr){
		int[] c = new int[arr.length];	
		for(int i = 0; i < arr.length;i++){
			c[i] = arr[i];	
		}
		return c;
	}

	public static boolean equal(int[] arr1, int[] arr2){
		if(arr1 == null || arr2 == null|| arr2.length != arr1.length){
			return false;
		}	
		for(int i=0; i< arr1.length;i++){
			if(arr1[i] != arr2[i]){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args){
		int[] arr = {1,2,123,5,13,44,123,1,2,4,1241234,1645,345,3,2,6,77,34,23}	;
		bubbleSort(arr);
		System.out.println("");
		for(int i = 0;i< arr.length;i++){
			System.out.printf("%d,",arr[i]);
		}
	
	}
}
