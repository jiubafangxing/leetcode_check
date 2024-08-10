/**
 * 寻找数组中的谷底元素。
 * <p>
 * 谷底元素是指其值严格小于左右相邻值的元素。
 * 给定一个整数数组 nums，找到谷底元素并返回其索引。如果数组包含多个谷底，
 * 则返回任何一个谷底所在位置即可。
 * 可以假设 nums[-1] = nums[n] = ∞，即数组的首元素前和尾元素后都是无穷大。
 * 必须实现一个时间复杂度为 O(log n) 的算法来解决此问题。
 * </p>
 * <p>
 * 例如，对于数组 {3, 1, 4, 5, 12, 6, 11, 3, 6}，1 是一个谷底元素，因为它小于它左右两边的元素。
 * 该方法将返回 1，这是谷底元素的索引。
 * </p>
 */
public class Code09_FindOneLessValueIndex2 {

    /**
     * 找到并返回数组中的谷底元素的索引。
     *
     * @param arr 输入的整数数组
     * @return 谷底元素的索引，如果不存在则返回 -1
     */
    public static int smallOfPart(int[] arr) {
	if(null == arr|| arr.length == 1){
		return -1;
	}    
	int L = 0;
	if(arr[L] < arr[L+1]){
		return L;
	}
	int R = arr.length-1;
	if(arr[R] < arr[R-1]){
		return R;
	}
	int index = -1;
	int mid = 0;
	while(L<=R){
		mid = L+((R-L)>>1);
		if(arr[mid] > arr[mid+1]){
			L = mid+1;	
		}else if(arr[mid] > arr[mid-1]){
			R = mid-1;	
		}else{
			index = mid;
			break;
		}
	}
        return index;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 5, 12, 6, 11, 3, 6};
        int index = smallOfPart(arr);
        System.out.println("位于index:" + index + "位置的" + arr[index] + "是谷底");
    }
}
