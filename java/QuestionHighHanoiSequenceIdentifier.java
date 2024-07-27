/*
 * 汉诺塔游戏的要求把所有的圆盘从左边都移到右边的柱子上，给定一个整型数组arr，
 * 其中只含有1、2和3，代表所有圆盘目前的状态，1代表左柱，2代表中柱，3代表右柱，
 * arr[i]的值代表第i+1个圆盘的位置。
 * 比如，arr=[3,3,2,1]，代表第1个圆盘在右柱上、第2个圆盘在右柱上、第3个圆盘在中柱上、第4个圆盘在左柱上
 * 如果arr代表的状态是最优移动轨迹过程中出现的状态，返回arr这种状态是最优移动轨迹中的第几个状态；
 * 如果arr代表的状态不是最优移动轨迹过程中出现的状态，则返回-1。
 * The Hanoi Tower game requires moving all the disks from the left pole to the right pole.
 * Given an integer array arr, which contains only 1, 2, and 3, representing the current position of all the disks.
 * 1 represents the left pole, 2 represents the middle pole, and 3 represents the right pole.
 * The value of arr[i] represents the position of the i+1th disk.
 * For example, arr=[3,3,2,1] represents that the first disk is on the right pole, the second disk is on the right pole,
 * the third disk is on the middle pole, and the fourth disk is on the left pole.
 * If the state represented by arr is a state that appears in the optimal movement trajectory, return the position of arr in the optimal movement trajectory;
 * If the state represented by arr is not a state that appears in the optimal movement trajectory, return -1.
 */
public class QuestionHighHanoiSequenceIdentifier{


	public static int steps(int[] arr, int i , int from , int other, int to){
		if(i == -1){
			return 0;
		}
		if(arr[i] != from && arr[i] != to){
			return -1;
		}
		if(arr[i] == from){
			return steps(arr, i-1,from ,to , other);	
		}else{
			int existSteps = steps(arr, i-1, other,from ,to);
			if(existSteps < 0){
				return existSteps;
			}
			return 1 << i + existSteps;
				
		}
		
	}

	public static void main(String[] args){
		int[] arr= {3,3,2,1}	;
		int stepResult = steps(arr, arr.length-1, 1,2,3);
		System.out.println("当前处于第"+stepResult+"步");
		steps(arr, arr.length-1, 1,2,3);
	
	}
}
