import java.util.*;
/*
 * Perfect Shuffle Problem
 * Given an array `arr` with an even length, assumed to be N * 2,
 * consisting of two parts: the left part `arr[L1...Ln]` and the right part `arr[R1...Rn]`.
 * Rearrange `arr` so that it becomes `arr[L1, R1, L2, R2, L3, R3, ..., Ln, Rn]`.
 * The requirements are:
 * - Time complexity: O(N)
 * - Extra space complexity: O(1)
 */
public class QuestionHighPerfectShuffle{


	public static void suffle(char[] arr){
			if(null != arr && arr.length > 0 && (arr.length &1) == 0){
					suffle(arr, 0 , arr.length-1);

			}

	}

	public static void suffle(char[] arr, int L, int R){
		while(R -L +1 >0){
				int len = R-L+1;
				int base = 3;
				int k = 1;
				while(base <= (len+1)/3){
					base *=3;
					k++;
				} 
		

				int half = (base-1)/2;
				int mid = (L+R)/2;
				rotate(arr, L+half,mid,mid+half);
				cycles(arr, L, base-1,k);
				L = L+base-1;

		}


	
	}
	private static void cycles(char[] arr, int start, int len, int k) {
		for(int i=0, trigger= 1;i<k;i++,trigger *=3){
				char preValue = arr[trigger +start-1];
				int cur = modifyIndex(trigger, len);
				while(cur != trigger){
					char tmp = arr[cur+start-1];
					arr[cur+start-1]= preValue;
					preValue =tmp;
					cur= modifyIndex(cur, len);
				}
				arr[cur+start-1] = preValue;
		}
	}

	private static void rotate(char[] arr, int L, int M, int R) {
			reverse(arr, L, M);
			reverse(arr, M+1, R);
			reverse(arr, L, R);
	}

	public static int modifyIndex(int i, int len){
		return (2*i)%(len+1);
	}

	public static boolean isFirstPartLeft(int idx,  int length){

		return (idx+1) <= length/2;
	}

	public static void reverse( char[] arr, int start, int end){
		while(start < end){
			swap(arr, start++, end--);
		}

	}

	public static void swap(char[] arr, int idx1, int idx2){
		char tmp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = tmp;

	} 

	public static int times(int arrLength){
		int i = 1;
		while(((int)Math.pow(3,i) -1) <= arrLength){
			i++;
		}
		return i;
	}

	public static void main(String[] args){
		char[] arr = {'a','b','c','d','e','f'};
		suffle(arr, 0 , arr.length-1);
		for(int i=0; i< arr.length;i++){
			System.out.printf("%c,",arr[i]);
		}
		System.out.println("");
	}

}
