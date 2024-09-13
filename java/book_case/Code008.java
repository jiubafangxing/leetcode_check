public class Code008{

	public static int getMaxNeighborhoodNum(int[] arr){
		return (arr.length-2)*2 +1;
	}

	public static void main(String[] args){
		int[] arr = {3,1,2,4,5};
		int result = getMaxNeighborhoodNum(arr);	
		System.out.println("result is "+ result);
	}
}
