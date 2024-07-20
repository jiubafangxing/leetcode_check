import java.util.*;
public class QuestionHighMaxBSTTopology{
	public static class Node{
		public  Integer v;	
		public  Node left;
		public  Node right;

		public Node(int v ){
			this.v = v;
		}

	}
	
	public static class Record{
		public  Integer l;

		public 	Integer r; 
	}


	public static int process(Node head){
		Map<Node, Record> recordmap = new HashMap<>();
		return process(head, recordmap);		
	}

	public static int process(Node head, Map<Node, Record> map ){
		if(null == head	){
			return 0;
		}
		int lSize = process(head.left, map);
		int rSize = process(head.right,map);
		if(head.v == 12){
			System.out.println(head);
		}
		modify(head.left,head.v, map, true);
		modify(head.right,head.v, map, false);
		Record lRecord = map.get(head.left);
		Record rRecord = map.get(head.right);
		int hlSize =( (lRecord == null)?0:(lRecord.l +lRecord.r+1));
		int hrSize =( (rRecord == null)?0:(rRecord.l +rRecord.r+1));
		Record record = new Record();
		record.l = hlSize;
		record.r = hrSize;
		map.put(head, record);
		return Math.max(hlSize + hrSize+1 , Math.max(lSize,rSize));
	}

	public static int modify(Node h, int v , Map<Node,Record> map , boolean isLeftChild){
		if(h == null || !map.containsKey(h)){
			return 0;
		}	
		Record r= map.get(h);
		if(isLeftChild && h.v > v || ((!isLeftChild) && h.v < v)){
			map.remove(h);
			return r.l +r.r+1;
		}else{
		 	int minus = modify(isLeftChild?h.right:h.left, v, map, isLeftChild);	
			if(isLeftChild){
				r.r -= minus;		
			}else{
				r.l -=minus;
			}
			map.put(h,r);
			return minus;
		}
	}

	public static Node buildTree() {
		Node head = new Node(6);
		head.left = new Node(1);
		head.left.left = new Node(0);
		head.left.right = new Node(3);
		head.right = new Node(12);
		head.right.left = new Node(10);
		head.right.left.left = new Node(7);
		head.right.left.left.left = new Node(2);
		head.right.left.left.right = new Node(5);
		head.right.left.right= new Node(14);
		head.right.left.right.left = new Node(11);
		head.right.left.right.right = new Node(15);
		head.right.right = new Node(13);
		head.right.right.left = new Node(20);
		head.right.right.right	= new Node(16);
		return head;
	}

	public static void main(String[] args){
		Node head = buildTree();
		int result = process(head);
		System.out.println("这颗树拓扑结构:"+ result);
	} 

}
