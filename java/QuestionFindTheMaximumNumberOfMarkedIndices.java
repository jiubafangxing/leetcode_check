import java.util.*;
public class QuestionFindTheMaximumNumberOfMarkedIndices{

	public static int maxNumOfMarkedIndices(int[] nums) {
		if(nums.length ==1){
			return 0;
		}
		Arrays.sort(nums);
		int[]  queue = new int[nums.length];
		int i= nums.length-2;
		int j = nums.length-1;
		boolean[] access = new boolean[nums.length];
		int match=0;
		for(;i<j&&i>=0;){
			if(access[j] == true){
				i--;
				continue;
			}
			if(2 * nums[i] <= nums[j]){
				access[i] = true;
				access[j] = true;
				i--;
				j--;
				match +=2;
			}else{
				i--;	
			}
		}
		return match;
	}
	public static void main(String[] args){
	
		int[] nums = {3,5,2,4};
		int res = maxNumOfMarkedIndices(nums);
		System.out.println("result is "+ res);
	}
}
