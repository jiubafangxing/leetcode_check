/*
 *在一个无序数组中，求最小的第k个数，
 */
public class QuestionHighKthSmallestFinder{
	public static int find(int[] arr){
		int num = selectNum(arr);
		for(int i=0;i< arr.length;i++){
		
		}
		return -1;	
	}


	public static void main(String[] args){
		int[] arr = {4,12,5,23,55,12,12,44,21,3234,5,1};	
		int result = find(arr);
		System.out.println("最小的值是"+result);
	}
}
