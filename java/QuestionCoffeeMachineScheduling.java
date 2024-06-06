/*
 * Given an array arr and an integer N, where arr[i] represents the number of coffee machines
 * and the time required for the i-th coffee machine to brew a cup of coffee. N indicates the
 * number of people waiting to brew coffee. Each coffee machine can only brew coffee one at a time.
 * 
 * After drinking the coffee, there are two ways for the cups to become clean: using a coffee
 * machine washer or letting them air dry.
 * 
 * There is only one coffee machine washer, which takes time to clean one coffee cup. It can only
 * clean one cup at a time (serially). Each coffee cup can also take time to air dry, and multiple
 * cups can air dry in parallel.
 * 
 * Assuming everyone drinks their coffee immediately after receiving it, find the shortest time
 * from the start of waiting until all coffee cups are clean.
 * 
 * 
 * 咖啡杯变干净
 * 给定数组 arr 和整数 N。 arr[i]的长度代表有几个咖啡机，arr[i] 代表第 i 号咖啡机泡一杯咖啡所需要的时间，
 * N 表示有多少个人正在等待着泡咖啡，每台咖啡机只能轮流泡咖啡。喝完咖啡的杯子有两种方式变干净：用
 * 咖啡机洗 或者 自己挥发 干净。
 * 洗咖啡杯机器只有一台，每次 耗时 a 洗干净 一个咖啡杯，洗完才能洗下一个杯子（串行）；每个咖啡杯也可以
 * 耗时 b 自己挥发干净 ，咖啡杯可以并行挥发。
 * 假设所有人拿到咖啡后立刻喝完，求出从开始等待到所有咖啡杯都变干净的最短时间。
 */
public class QuestionCoffeeMachineScheduling {
	public static class MachineNode implements Comparable<MachineNode>{
		public Integer usedTime;

		public Integer machineNeedTime;

		@Override
		public int compareTo(MachineNode node){
			return (this.usedTime + this.machineNeedTime) - (node.usedTime + node.machineNeedTime);
		}

	}
	public static class MinHeap{
		MachineNode[] arr;

		public int size;
		public MinHeap(int k ){
			arr = new MachineNode[k];
		}
		public void heapInsert(MachineNode node){
			if(size == arr.length){
				return;
			}
			arr[size++] = node;
			Integer cur = size-1;	
			Integer parent = (cur-1)/2;
			while(arr[cur].compareTo(arr[parent]) < 0){
				swap(arr, cur, parent);
				cur = parent;
				parent = (cur-1)/2;
			}
		}


		public void swap(MachineNode[] arr, int a , int b){
			MachineNode machineNode = arr[a] ;
			arr[a] = arr[b];
			arr[b] = machineNode;
		}

		public MachineNode poll(){
			MachineNode node =null;
			if(size > 0){
				node = arr[0];
				swap(arr, size -1, 0);
				arr[size-1] = null;
				size--;
			}
			heapify(0);
			return node;
		}


		public void heapify(int i){
			int leftNode = 2*i+1;
			int rightNode = 2*(i+1);
			int partMinIndex = Integer.MAX_VALUE;
			if(leftNode < size){
				partMinIndex = leftNode;	
			}
			if(rightNode < size){
				if(arr[leftNode].compareTo(arr[rightNode]) >0){
					partMinIndex = rightNode;
				}
			}

			while(arr[partMinIndex].compareTo( arr[i]) <0){
				
					swap(arr, partMinIndex, i);
					i = partMinIndex ;
					leftNode = 2*i+1;
					rightNode = 2*(i+1);
					if(leftNode >= size){
							break;
					}
					partMinIndex = leftNode;
					if(rightNode < size){
						if(arr[leftNode].compareTo(arr[rightNode])>0){
							partMinIndex = rightNode;
						}
					}
				
			}	

		}

	}


	public int getMin(int[] machines, int n , int washingNeedTime, int selfTime){
		//利用小根堆来获取每个人的咖啡机使用时间
		MinHeap minHeap = new MinHeap(machines.length);
		for(int node: machines){
			MachineNode machineNode = new MachineNode();
			machineNode.machineNeedTime = node;
			machineNode.usedTime = 0;
			minHeap.heapInsert(machineNode);	
		}
		int allPeopleSize = n;
		MachineNode[] personNodes= new MachineNode[n];
		while(allPeopleSize > 0){
			MachineNode machineNode = minHeap.poll();
			MachineNode newNode = new MachineNode();
			newNode.usedTime = machineNode.usedTime;
			newNode.machineNeedTime = machineNode.machineNeedTime;
			personNodes[n - allPeopleSize] = newNode;	
			machineNode.usedTime += machineNode.machineNeedTime;
			minHeap.heapInsert(machineNode);
			allPeopleSize--;
		}
		int[] personDrinkTimes = new int[n];
		for(int i=0;i< n;i++){
			MachineNode usedTimeNode = personNodes[i];
			Integer personDrinkTime = usedTimeNode.usedTime+usedTimeNode.machineNeedTime;
			personDrinkTimes[i] = personDrinkTime;
		}
		//递归处理,来获取最合适的时间
		return process(personDrinkTimes, washingNeedTime, selfTime, 0,0);
	}


	public int process(int[] arr, int n, int b, int pos, int washline){
		if(pos == arr.length -1){
			return Math.min(Math.max(washline, arr[arr.length-1]) +n,arr[arr.length-1]+b);
		}	
		int posWashlineEndTime  = Math.max(arr[pos] , washline) +n;
		int otherWashEndTime = process(arr, n, b, pos+1, posWashlineEndTime);
		int washEndTime = Math.max(posWashlineEndTime, otherWashEndTime);

		int posNoWashEndTime =  arr[pos]+b;
		int otherNoWashEndTime = process(arr,n,b,pos+1, washline);	
		int noWashEndTime= Math.max(posNoWashEndTime, otherNoWashEndTime);

		return Math.min(washEndTime, noWashEndTime);
	}


	public static void main(String[] args) {
		int[] arr = {3, 1, 4};
		int N = 5;
		int a = 2;
		int b = 3;
		QuestionCoffeeMachineScheduling schedule = new QuestionCoffeeMachineScheduling();
		int allTime= schedule.getMin(arr,N,a,b );
		System.out.println(allTime);
	}
}
