/*
 * 给定一个数组arr，长度为N且每个值都是正数，代表N个人的体重。
 * 再给定一个正数limit，代表一艘船的载重。
 * 以下是坐船规则，
 * 1）每艘船最多只能做两人；
 * 2）乘客的体重和不能超过limit。
 * 返回如果同时让这N个人过河最少需要几条船。
 */
public class QuestionHighMinBoat{


	public static void shellSort(int[] arr){
		int gap = arr.length/2;	
		while(gap > 0){
			
			for(int i=gap;i< arr.length; i++){
				for(int j=i; j >=gap && arr[j] < arr[j-gap];j=j-gap)	{
					int tmp = arr[j];
					arr[j] = arr[j-gap];	
					arr[j-gap] = tmp;
				}
			}
			gap = gap/2;	
		}

	} 

	public static int minBoat(int[] arr, int limit){
		shellSort(arr);
		int cmp = limit/2;
		int pos = -1;
		for(int j=arr.length-1; j>=0; j--){
			if(arr[j] <= cmp){
				pos = j;
				break;
			}	
		}
		if(pos <0){
			return arr.length;
		}
		if(pos == arr.length-1){
			return arr.length /2;
		}
		int lessNotUse = 0;
		int use = 0;
		int moreNotUse = 0;
		int lessIdx = pos;
		int moreIdx = pos+1;
		int matchSize = 0;
		while(lessIdx >=0 && moreIdx < arr.length){
			if(arr[lessIdx]+arr[moreIdx+matchSize] <= limit){
				matchSize++;
			}else{
				if(matchSize > 0){
					if(lessIdx == 0){
						System.out.println(1);
					}
					if(lessIdx - matchSize +1 >= 0){
						lessIdx -=matchSize;
						moreIdx += matchSize;
						use += matchSize;	
					}else{
						use = lessIdx +1;
						moreNotUse = arr.length-lessIdx-1;
						moreIdx = arr.length;
					}
				}else{
					lessNotUse++;
					lessIdx--;	
				}
			}	
		}
		if(lessIdx > 0){
			lessNotUse = lessIdx +1;
		}
		if(moreIdx < arr.length){
			moreNotUse = arr.length -moreIdx;	
		}
		return (lessNotUse+1)/2 + use+ moreNotUse;
	}


	public static void main(String[] args){
		int[] arr ={1,2,3,3,2,1,12};
		int result = minBoat(arr, 4);
		System.out.println("最少需要几条船 "+ result);
	}
}
