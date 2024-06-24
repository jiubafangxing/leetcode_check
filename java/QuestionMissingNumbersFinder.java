import java.util.*;
/*
 * 给定一个整数数组A，长度为n，有1 <= A[i] <= n，且对于[1，n]的整数，其中部分整数会重复出现而部分不会出现。
 * 实现算法找到[1，n]中所有未出现在A中的整数。
 * 提示：尝试实现0(n)的时间复杂度和0(1)的空间复杂度(返回值不计入空间复杂度)
 * 输入描述：
 * 一行数字，全部为整数，空格分隔
 * AO A1 A2 A3...
 * 输出描述:
 * 一行数字，全部为整数，空格分隔
 * RO R1 R2 R3...
 * 示例1:
 * 输入
 * 1 3 4 3
 * 输出
 * 2
 *
 * Given an array A of integers, with a length of n, where 1 ≤ A[i] ≤ n, and for the integers in the range [1, n],
 * some integers will appear multiple times while others will not appear at all.
 * Implement an algorithm to find all the integers in the range [1, n] that do not appear in A.
 *
 * Hint: Try to implement the algorithm with O(n) time complexity and O(1) space complexity
 * (the return value is not counted in the space complexity).
 * Input Description:
 * A line of numbers, all integers, separated by spaces
 * AO A1 A2 A3...
 * Output Description:
 * A line of numbers, all integers, separated by spaces
 * RO R1 R2 R3...
 * Example 1:
 * Input
 * 1 3 4 3
 * Output
 * 2
 */
public class QuestionMissingNumbersFinder{
	public static Object[]  findMiss(Integer[] arr){
		for(int i =0;i< arr.length;i++){
			Integer j = i;
			Integer tmp = arr[j];
			while(null != tmp && tmp != j+1  ){
				arr[tmp-1] = tmp;
				j = tmp-1;
				tmp = arr[j];
				arr[j] = null;
			}
		}
		List<Integer> resultArr = new ArrayList();
		for(int i=0; i< arr.length;i++){
			if(arr[i] == null){
				resultArr.add(i+1);	
			}
		}
		return  resultArr.toArray();
	}
	public static void main(String[] args){
		Integer[] arr = {1,3,2,3,5,7,7};
		Object[] result =findMiss(arr);
		for(Object re:result){
			System.out.println(re);
		}

	}

}
