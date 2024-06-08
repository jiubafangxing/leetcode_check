import java.util.*;
/*
 * The task of reordering an array such that the product of each pair of consecutive elements is a multiple of 4 can be approached by applying some simple rules. 
 * Initially, all even numbers in the array should be clustered together, followed by clustering all the odd numbers. 
 * Subsequently, elements that are divisible by 4 should be grouped, then those divisible by 2 but not by 4, and lastly, those that are not divisible by 2. 
 * 
 * 重新排列数组，使每两个连续元素的乘积为4的倍数这个问题可以通过一些简单的规则来解决。
 * 首先，我们需要将数组中所有的偶数元素放在一起，然后将所有的奇数元素放在一起。
 * 接着，我们需要将能整除4的元素放在一起，再将不能整除4但能被2整除的元素放在一起，最后再将不能被2整除的元素放在一起。
 */
public class QuestionArrayMultiplierArranger{


    public static int[] arrangeArray(int[] array) {
	List<Integer> fourNums = new ArrayList();    
	List<Integer> twoNums= new ArrayList();    
	List<Integer> oddNums= new ArrayList();    
	for(int i=0;i< array.length;i++){
		if(array[i]%2 == 1){
			oddNums.add(array[i]);	
		}else if (array[i]% 4 ==0){
			fourNums.add(array[i]);	
		}else{
			twoNums.add(array[i]);
		}
	}
	//默认无答案
	Integer processCode = -1;
	if(twoNums.size()  ==0){
		if(oddNums.size() > 1){
			if(fourNums.size() >= oddNums.size()-1){
				processCode = 1;
			}
		}else if(oddNums.size() ==1){
			if(fourNums.size() >= 1){
				//奇4序列
				processCode = 1;
			}
		}else{
			//原样输出array即可
			processCode =0;
		}
	}else{
		if(oddNums.size() == 0){
			if(twoNums.size()%2 ==1){
				if(fourNums.size() > 0){
					//偶4奇
					processCode =2;
				}
				
			}else{
				processCode = 2;
			}
			
		}else{
			if(fourNums.size() >= oddNums.size()){
				processCode = 2;
			}
		}
	
	} 

	int[] resultArray = new int[array.length];
	Iterator<Integer> oddIterator = oddNums.iterator();
	Iterator<Integer> fourIterator = fourNums.iterator();
	Integer index =0;
	if(processCode == -1){
		return null;
	}else if (processCode == 0){
		return array;
	}else if (processCode == 1){
		while(index < array.length){
			if(oddIterator.hasNext()){
				resultArray[index++] = oddIterator.next();
			}
			if(fourIterator.hasNext()){
				resultArray[index++] = fourIterator.next();
			}
		}
	}else{
		for(int i=0;i< twoNums.size();i++){
			resultArray[index++] = twoNums.get(i);
		}
		while(index < array.length){
			if(fourIterator.hasNext()){
				resultArray[index++] = fourIterator.next();
			}
			if(oddIterator.hasNext()){
				resultArray[index++] = oddIterator.next();
			}
		}
	}
	return resultArray;
    }

    public static void main(String[] args){
    	int[] inputArray = {1,2,3,4,8,16};
    	int[] inputArray2 = {1,2,3,4,8};
    	int[] inputArray3 = {1,2,3,4};
    	int[] inputArray4 = {1,3,4};
    	int[] inputArray5 = {3,4};
    	int[] inputArray6 = {3};
    	int[] inputArray7 = {4};
	process(inputArray);
	process(inputArray2);
	process(inputArray3);
	process(inputArray4);
	process(inputArray5);
	process(inputArray6);
	process(inputArray7);

    }

    public static void process(int[] array){
	System.out.println("start"+array.length);
    	for(int i=0;i< array.length;i++){
		System.out.print(array[i]+",");
	}
	System.out.println("");
	System.out.println("--------------");
	QuestionArrayMultiplierArranger arranger = new QuestionArrayMultiplierArranger();
	int[] resultArray = arranger.arrangeArray(array);
	if(null != resultArray){
		for(int resultItem :resultArray){
			System.out.print(resultItem+",");
		}
	}else{
		System.out.print("null");
	}
	System.out.println("");
	System.out.println("end");
    }
}

