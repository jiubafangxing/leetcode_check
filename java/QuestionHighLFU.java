import java.util.*;
/*
 * The task you've described is to implement a Least Frequently Used (LFU) cache replacement algorithm, which has the following features:
 *
 * void set(int key, int value): Adds or updates the value associated with the key.
 * int get(int key): Retrieves the value associated with the key.
 *
 * The cache can hold up to K entries. If a new entry (the (K+1)th) is to be added, the algorithm must evict one entry from the cache based on the following strategy:
 *
 * - The key that has been called the least number of times since it was added to the cache is evicted.
 * - If there are multiple keys with the least number of calls, the one that was accessed earliest is evicted.
 *
 * Here is the translation of the task into English:
 *
 * Implement a data structure with the following functionalities:
 *
 * - void set(int key, int value): Add or modify the value for a given key.
 * - int get(int key): Query the value for a given key.
 *
 * However, the cache can hold a maximum of K records. If a new record, the (K+1)th, is to be added, it is necessary to follow a strategy to remove one record from the cache before adding the new one.
 *
 * The strategy is as follows: Among the K records in the cache structure, the key that has been called the least number of times since it was entered into the cache structure should be removed; if there are multiple keys with the minimum number of calls, the key that was last accessed the earliest is removed.
 *
 * This is the LFU cache replacement algorithm. Implement this structure with K as a given parameter.
 */
public class QuestionHighLFU{
	public static class Node{
		public int key;

		public int value;

		public int freq;

		public Node pre;

		public Node post;


		public Node(){
			this.key =-1;
			this.value = -1;
			this.freq = 0;
			this.pre = null;
			this.post = null;
		}
		
	}


	public static class LinkedList{
		public int freq;

		public Node head;

		public Node tail;	
		
		public LinkedList pre;

		public LinkedList post;
		public LinkedList(int freq){
			this.freq = freq;
			head = new Node();
			tail = new Node();
			head.post = tail;
		    tail.pre = head;	
			
		}

		public boolean isEmpty(){
			return head.post == tail;
		}


		public void addNode(Node node){
			Node lastNode = tail.pre;
			lastNode.post = node;
			node.pre = lastNode;
		}

		public void deleteNode(Node node){
			Node preNode = node.pre;	
			Node postNode = node.post;
			preNode.post = postNode;
			postNode.pre = preNode;
		}

		public Node getLastNode(){
			if(isEmpty()){
				return new Node();
			}else{
				return tail.pre;	
			}
		}
		
	}


	public static class LFUCache{
		Map<Integer,Node> nodeMap;

		Map<Integer,LinkedList> freqMap;	

		Integer capacity;	

		Integer minFreq;

		
		public LFUCache(int capacity){
			this.capacity= capacity;
			this.minFreq = 0;
			this.nodeMap = new HashMap<>();
			this.freqMap = new HashMap<>();
		}

		public void put(int key , int value){
			//first check if the key exist
			Node node = nodeMap.get(key);
			boolean isNew = false;
			if(null == node){
				node = new Node();
				node.key = key;
				node.value = value;	
				isNew = true;
				if(capacity == 0){
					releaseMinFreqNode(minFreq);	
				}
			}
			increment(node, isNew);
		}


		public void releaseMinFreqNode(Integer freq){
			LinkedList list = freqMap.get(freq);	
			Node node = list.tail.pre ;
			list.deleteNode(node);
			nodeMap.remove(node.key);
			if(list.isEmpty()){
				LinkedList newMinFreqList = list.post;
				newMinFreqList.pre = null;
				this.minFreq = newMinFreqList.freq;
				this.freqMap.remove(list.freq);
			}
			this.capacity++;
		}

		public int get(int key){
			Node node = this.nodeMap.get(key);
			if(null != node){
				increment(node, false);
				return node.value;
			}else{
				return -1;
			}
		}
		public void increment(Node node, boolean isNew){
			boolean buildFreq = false;
			Integer oldFreq = node.freq;
			LinkedList newNodeFreq = this.freqMap.get(oldFreq+1);
			if(null == newNodeFreq){
				buildFreq = true;
			}
			if(buildFreq){
				newNodeFreq = buildList(oldFreq+1);
			}
			if(!isNew){
				LinkedList oldList = this.freqMap.get(oldFreq);
				oldList.deleteNode(node);
				if(oldList.isEmpty()){
					LinkedList oldPostList = oldList.post;
					this.freqMap.remove(oldList.freq);		
					if(oldList.freq == minFreq){
						if(null != oldPostList)	{
							minFreq = oldPostList.freq;
						}
					}
				}
			}else{
				this.capacity--;
			}
			node.freq = oldFreq+1;
			insertNewNode(node,newNodeFreq);
		}


		public void insertNewNode(Node node, LinkedList newNodeFreq){
			Node head = newNodeFreq.head;
			Node postNode = head.post;
			head.post = node;
			node.post = postNode;
			node.pre = head;
			postNode.pre = node;
			this.nodeMap.put(node.key, node);			
		}


		public LinkedList buildList(Integer newFreq){
			if(this.minFreq ==0){
				LinkedList linkedList = new LinkedList(newFreq);
				linkedList.freq = newFreq;
				this.minFreq = newFreq;
				this.freqMap.put(newFreq, linkedList);
				return linkedList;
			}else{
				Integer oldFreq = newFreq-1;
				LinkedList oldFreqList = this.freqMap.get(oldFreq);
				LinkedList oldPostFreqList =oldFreqList.post;
				LinkedList linkedList = new LinkedList(newFreq);
				linkedList.post = oldPostFreqList;
				linkedList.pre = oldFreqList;
				if(null != oldPostFreqList){
					oldPostFreqList.pre =  linkedList;
				}
				oldFreqList.post = linkedList;
				this.freqMap.put(newFreq, linkedList);
				if(minFreq > newFreq){
					this.minFreq = newFreq;
				}
				return linkedList;
			}	
		}

		public int peek(int key){
			Node node = this.nodeMap.get(key);
			if(null != node){
				return node.value;
			}else{
				return -1;
			}
		}
	}

	public static void main(String[] args){
		LFUCache lfuCache = new LFUCache(3);
		lfuCache.put(0, 0);
		lfuCache.put(1, 1);
		lfuCache.put(2, 2);
		lfuCache.put(3, 3);
		lfuCache.put(4, 4);
		System.out.println(lfuCache.get(3));
		System.out.println(lfuCache.get(3));
		System.out.println(lfuCache.get(3));
		lfuCache.put(5, 5);	
		lfuCache.put(6, 6);	
		System.out.println("----");
		for(int i = 0; i < 7; i++){
			System.out.println("key"+i+"value"+lfuCache.peek(i));
		}
	}
}
