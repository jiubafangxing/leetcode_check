import java.util.*;
public class Code03_InsertSort{
	public static void insertSort(int[] arr){
		if(null == arr || arr.length == 1){
			return;
		}
		for(int i=1; i< arr.length; i++){
			int j=i;
			while(j-1>=0 && arr[j] <  arr[j-1]){
				swap(arr, j,j-1);
				j--;	
			}
		}
	}

	public static void swap(int[] arr, int i , int j){
		int tmp = arr[i];	
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	public static void systemSort(int[] arr){
		Arrays.sort(arr);	
	}


	public static int[] copyArray(int[] arr){
		if(null == arr || arr.length == 0){
			return null;
		}
		int[] c = new int[arr.length];	
		for(int i=0;i< arr.length;i++){
			c[i] = arr[i];	
		}
		return c;
	}


	public static int[] generateArray(int size, int maxNum){
		int[] a = new int[size];	
		for(int i=0;i< a.length; i++){
			a[i] = ((int)((maxNum+1) * Math.random()))- ((int)(maxNum * Math.random()));
		}
		return a;
	}

	public static boolean arrEqual(int[] a1, int[] a2){
		if(a1 == null || a2 == null){
			return false;
		}
		if(a1.length != a2.length){
			return false;
		}
		for(int i=0;i< a1.length;i++){
			if(a1[i] != a2[i]){
				return false;
			}
		}
		return true;
	}


	public static void printArr(int[] arr){
		System.out.println("---");
		System.out.println("");
		for(int a :arr){
			System.out.printf("%d,",a);
		}
		System.out.println("");
	}
	
	public static void main(String[] args){
		for (int i = 0;i< 100;i++){
			int[] a = generateArray(10, 100);
			int[] a1= copyArray(a);
			int[] a2= copyArray(a);
			insertSort(a1);
			systemSort(a2);
			if(!arrEqual(a1, a2)){
				printArr(a1);
				printArr(a2);
			}
		}	
	}
}
