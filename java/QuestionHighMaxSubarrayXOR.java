/*
 * Defining the XOR sum of an array: 
 * The value obtained by performing XOR operations on all the numbers in the array.
 * Given an array arr of integers, which may include positive, negative, and zero values, 
 * the task is to find the maximum XOR sum of any subarray within the array.
 * 
 * 数组异或和的定义是将数组中的所有数字进行异或操作，最终得到的值。
 * 给定一个整型数组 arr，数组中可能包含正数、负数和零。
 * 问题的目标是找到数组中任意子数组的最大异或和。
 */
public class QuestionHighMaxSubarrayXOR {

	public static class Node{
		public Node[] nexts = new Node[2];
		
	}

	public static class NodeTrie{
		Node node = new Node();


		public void addNode(int sum){
			Node cur= node;
			for(int i=31; i>=0; i--){
				int v = (sum >> i) &1;	
				if(null == cur.nexts[v]){
					cur.nexts[v] = new Node();	
				}
				cur = cur.nexts[v];
			}	
		}
		
		public int max(int sum){
			Node cur = node;	
			int res = 0;
			for(int i=31;i>=0; i--){
				int v = (sum >> i) &1;	
				int best = i == 31? v:(v^1);
				int dest = cur.nexts[best] != null ? best: best^1;
				best = v ^ dest;
				res = best << i | res;
				cur = cur.nexts[dest];
			}
			return res;
		}
	}

	public static int maxXor3(int[] arr){
		if(null == arr|| arr.length == 0){
			return 0;
		}
		int[] pre = new int[arr.length];
		pre[0] = arr[0];
		NodeTrie nodeTrie = new NodeTrie();
		//以i位置的数结尾
		Integer sum = 0;
		Integer max = 0;
		nodeTrie.addNode(sum);
		for(int i=0;i< arr.length;i++){
			sum = i == 0? arr[i]:(sum ^ arr[i])	;
			max = Math.max(nodeTrie.max(sum),max);
			nodeTrie.addNode(sum);
		}
		return max;
	
	}
	public static int maxXor2(int[] arr){
		if(null == arr|| arr.length == 0){
			return 0;
		}
		int[] pre = new int[arr.length];
		pre[0] = arr[0];
		for(int i=1; i< arr.length;i++){
			pre[i] = pre[i-1] ^ arr[i];
		}

		//以i位置的数结尾
		Integer sum = 0;
		Integer max = 0;
		for(int i=0;i< arr.length;i++){
			//以j位置的数开始
			for(int j=0; j<= i;j++){
				for(int z = j; z<=i;z++){
					sum =arr[j] ^ (z==0? 0 : pre[i-1]);
					max = Math.max(max,sum);
				}	
			}
		}
		return max;
	}

	public static int maxXor(int[] arr){
		if(null == arr|| arr.length == 0){
			return 0;
		}
		//以i位置的数结尾
		Integer sum = 0;
		Integer max = 0;
		for(int i=0;i< arr.length;i++){
			//以j位置的数开始
			for(int j=0; j<= i;j++){
				for(int z = j; z<=i;z++){
					sum = sum ^ arr[z];	
					max = Math.max(max,sum);
				}	
			}
		}
		return max;
	}





	public static void main(String[] args){
		int[] arr = {3, -28, -29, 2};
		int result = maxXor(arr);	
		int result2 = maxXor2(arr);	
		int result3 = maxXor3(arr);	
		System.out.println("result is "+result);
		System.out.println("result2 is "+result2);
		System.out.println("result3 is "+result3);
	}

}
