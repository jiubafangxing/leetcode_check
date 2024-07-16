
public class QuestionQuickSort{
	public static void quickSort(int[] arr, int low , int high){
		if(low >= high){
			return ;
		}
		int idx = partition(arr,low, high);
		quickSort(arr, low,idx-1 );
		quickSort(arr, idx+1 , high);
		return ;	
	}

	public static int partition(int[] arr, int low, int high){
		int provit = arr[low];
		while(low < high){
			while(arr[high] >=  provit && low < high){
				high--;
			}
			arr[low] = arr[high];
			while(arr[low] < provit && low < high){
				low++;
			}
			arr[high] = arr[low];
		}	
		arr[low] = provit;
		return low;
	}



	public static void main(String[] args){
		int[] arr = {1,2,5,12,23,5,34,12,423,4,5,213}	;
		quickSort(arr, 0, arr.length-1);
		System.out.println("");
		for(int i=0;i< arr.length;i++){
			System.out.print(arr[i]+",");
		}
	}

}
