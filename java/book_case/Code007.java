package book_case;

import java.util.*;


/**
 * Sliding Window Maximum
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Return the max sliding window.
 * https://leetcode.cn/problems/sliding-window-maximum/description/
 */
public class Code007{
	public static int[] getMaxWindow(int[] arr, int windowSize){
		if(null == arr || arr.length < windowSize){
			return null;
		}
		int[] result = new int[arr.length-windowSize+1];
		Deque<Integer> deque = new LinkedList<Integer>();

		for(int i=0;i< arr.length;i++){
			if(deque.isEmpty()){
				deque.addLast(i)	;
			}else {
				if(arr[deque.peekLast()] > arr[i] ){
					deque.addLast(i);	
				}else{
					while(!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]){
						deque.removeLast();
					}
					deque.addLast(i);	
				}
				if(deque.peekFirst() == i-windowSize){
					deque.removeFirst();
				}	
			}
			if(i>=windowSize-1) {
				result[i - windowSize+1] = arr[deque.peekFirst()];
			}
		}
		return result;
	}

	public static void main(String[] args){
//		int[] data = {4,3,5,4,3,3,6,7} 	;
		int[] data = {1}	;	
		int[] result = getMaxWindow(data,1);
		System.out.println("");
		System.out.printf("%s","[");
		for(int resultItem :result){
			System.out.printf("%d,", resultItem);
		}
		System.out.printf("%s","]");
		System.out.println("");
	
	}
}
