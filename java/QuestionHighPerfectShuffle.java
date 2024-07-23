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
		int times = times( length);
		int step = length / times;
		int firstPart =( (int)Math.pow(3,times))-1;
		int firstPartEndIdx = start + firstPart -1;
		int firstPostPartStartIdx = start+ firstPart/2 ;
		if(firstPart < length){
			int secondPartStartIdx = firstPartEndIdx +1;
			reverse(firstPostPartStartIdx, firstPartEndIdx, arr);
			reverse(firstPartEndIdx+1, end, arr);
			reverse(firstPostPartStartIdx, end, arr);
			suffle(arr, secondPartStartIdx, end);		
		}
		for(int i = start;i<= start + times; i++){
			int stepStart = 0;

			int steps = 0;
			int idx = stepStart+step*steps;
			int valueHold = arr[idx];
			while(idx <= firstPartEndIdx)	{
				if(isFirstPartLeft(idx, start, firstPart)){
					int dest = stepStart + 2*(steps+1) -1;
					int tmp= arr[dest];
					arr[dest] = valueHold;
					valueHold =  tmp;
				}else{
					int dest = idx - firstPostPartStartIdx;
					dest = (dest+1)*2;
					int tmp= arr[dest];
					arr[dest] = valueHold;
					valueHold =  tmp;

				}	
				steps++;
				idx = stepStart+step*steps;			
			}
		}
	}

	public static boolean isFirstPartLeft(int idx, int start, int length){
		int cmp = start + length/2 -1;	
		return idx <= cmp;
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
		while(((int)Math.pow(3,i) -1) < arrLength){
			i++;
		}
		return i-1;
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
