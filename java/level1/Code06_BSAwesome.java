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
public class Code06_BSAwesome {

    /**
     * 找到并返回数组中的谷底元素的索引。
     *
     * @param arr 输入的整数数组
     * @return 谷底元素的索引，如果不存在则返回 -1
     */
    public static int smallOfPart(int[] arr) {
        // 如果数组为空或只有一个元素，直接返回
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }

        // 检查数组的首尾元素是否为谷底
        int L = 0;
        if (arr[L] < arr[L + 1]) {
            return L;
        }
        int R = arr.length - 1;
        if (arr[R] > arr[R - 1]) {
            return R;
        }

        // 使用二分查找法查找谷底元素
        int mid, index = -1;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            // 如果 mid 元素比前一个元素大，说明谷底在右侧
            if (arr[mid] > arr[mid - 1]) {
                L = mid + 1;
            } 
            // 如果 mid 元素比后一个元素大，说明谷底在左侧
            else if (arr[mid] < arr[mid + 1]) {
                R = mid - 1;
            } 
            // 如果 mid 元素是谷底
            else {
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
