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

	public static void suffle(int[] arr, int start, int end){
		if(start >= end){
				return;
		}
		int length = end - start +1;
		int times =  times( length);
		int firstPart =( (int)Math.pow(3,times-1))-1;
		int firstPartEndIdx = start + firstPart -1;
		int firstPostPartStartIdx = start+ firstPart/2 ;
		int mid = start + length/2 -1;
		if(firstPart < length){
			int secondPartStartIdx = firstPartEndIdx +1;
			reverse(firstPostPartStartIdx, mid , arr);
			reverse(mid+1, firstPartEndIdx+(length-firstPart)/2, arr);
			reverse(firstPostPartStartIdx, firstPartEndIdx+(length-firstPart)/2, arr);
			suffle(arr, secondPartStartIdx, end);		
		}
		for(int i = 0;i<  times-1; i++){
				int p =((int) Math.pow(3,i))-1;
				int holdValue = arr[p+start];
				int moveSize = firstPart/(times-1);

				while(moveSize > 0){
					if(isFirstPartLeft(p,  firstPart)){
						int dest = (p+1)*2 -1 +start;
						System.out.println("当前位置"+p+"目标位置"+dest);
						int tmp = arr[dest];
						arr[dest] = holdValue;
						holdValue = tmp;
						p = dest -start;
					}else{
						int dest =( p+1-firstPart/2)*2 -2 +start;
						System.out.println("当前位置"+p+"目标位置"+dest);
						int tmp = arr[dest];
						arr[dest] = holdValue;
						holdValue = tmp;
						p = dest -start;
					}
					moveSize--;
				}


		}
	}

	public static boolean isFirstPartLeft(int idx,  int length){

		return (idx+1) <= length/2;
	}

	public static void reverse(int start, int end, int[] arr){
		while(start < end){
			swap(arr, start++, end--);
		}

	}

	public static void swap(int[] arr, int idx1, int idx2){
		int tmp = arr[idx1];
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
		int[] arr = {1,3,5,7,9,2,4,6,8,10};
		suffle(arr, 0 , arr.length-1);
		for(int i=0; i< arr.length;i++){
			System.out.printf("%d,",arr[i]);
		}
		System.out.println("");
	}

}
