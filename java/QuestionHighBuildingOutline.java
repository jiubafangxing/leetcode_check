import java.util.*;
/*
 *  给定一个 N×3 的矩阵 matrix，对于每一个长度为 3 的小数组 arr，都表示一个大楼的三个数 据。arr[0]表示大楼的左边界，arr[1]表示大楼的右边界，
 *  arr[2]表示大楼的高度(一定大于 0)。 每座大楼的地基都在 X 轴上，大楼之间可能会有重叠，请返回整体的轮廓线数组。
 * 【举例】 matrix ={{2,5,6}, {1,7,4}, {4,6,7}, {3,6,5}, {10,13,2}, {9,11,3}, {12,14,4},{10,12,5} }
 * 		  返回: {{1,2,4},{2,4,6}, {4,6,7}, {6,7,4}, {9,10,3}, {10,12,5}, {12,14,4}}
 */
public class QuestionHighBuildingOutline{

	public static class Node implements Comparable<Node>	{
		public int v;
		public int h;
		public boolean isAdd;
		@Override
		public int compareTo(QuestionHighBuildingOutline.Node o) {
				if(o.v != this.v){
					return this.v - o.v;
				}
				if(this.isAdd != o.isAdd){
					return this.isAdd ? -1 : 1;
				}
				return 0;
		} 
	}
	public static Integer[][] outline(int[][] martix){
		List<Node> nodes = new ArrayList<>();
		for(int column = 0; column < martix.length; column++){
				int from = martix[column][0];
				int to = martix[column][1];
				int high = martix[column][2];
				Node node = new Node();
				node.v = from;
				node.h = high                                ;
				node.isAdd = true;
				Node minusNode= new Node();
				minusNode.v = to;
				minusNode.h = high;
				minusNode.isAdd = false;
				nodes.add(node);
				nodes.add(minusNode);
		}
		//mark the times of dest height 
		TreeMap<Integer,Integer> timesMap = new TreeMap<>(); 	
		//mark the height of the index 
		TreeMap<Integer,Integer> heightMap = new TreeMap<>();
		Collections.sort(nodes);
		for(Node node:nodes){
				Integer times = timesMap.get(node.h);	
				if(null == times){
					times = 0;
				}
				if(node.isAdd){
					times++;
					timesMap.put(node.h, times);
				}else{
					times--;
					if(times == 0){
						timesMap.remove(node.h);
					}else{
						timesMap.put(node.h, times);
					}

				}
				if(!timesMap.isEmpty()){
					heightMap.put(node.v, timesMap.lastKey());
				}else{
					heightMap.put(node.v,0);
				}
		}
		Set<Integer> keys =heightMap.keySet();
		Integer start = 0;
		Integer index = 0;
		Integer preHeight =0;
		Integer[][] resultArray = new Integer[keys.size()][3];

		for(Integer key :keys){
			Integer[] resultItems = new Integer[3];
			resultItems[0]= start;
			resultItems[1] = key;
		    	resultItems[2] = preHeight; 
			Integer maxHeight = 	heightMap.get(key);	
			if( preHeight != maxHeight){
				if(preHeight != 0 ){
					resultArray[index++] = resultItems;
				}
				start = key;
				preHeight = maxHeight; 
			}
			
		}
		return resultArray;
	
	}
	public static void printArray(Integer[] arr){
		System.out.print("[");
		for(int i=0;i< arr.length; i++){
			System.out.print(arr[i]+",");
		}
		System.out.print("]");
		System.out.println("");
	}
	public static void main(String[] args){
		int[][] matrix ={{2,5,6}, {1,7,4}, {4,6,7}, {3,6,5}, {10,13,2}, {9,11,3}, {12,14,4},{10,12,5} };
		Integer[][] outline = outline(matrix);
		for(Integer[] outlineItem : outline){
			printArray(outlineItem);
		}	
	}

}
