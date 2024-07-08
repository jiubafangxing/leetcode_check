public class QuestionHighJosephusProblem{
	public static class Node{
		public Node next;
		public String name;		
	
	}
	public static  int findLivePerson(Node head, int m ){
		if(head == null ){
			return -1;
		}
		Node tmp = head;	
		Integer length = 0;
		while(null != tmp){
			length++;	
			tmp = tmp.next ;
		}
		int result = findLive(length,m);
		Integer index =1;
		tmp = head;
		while(index != result ){
			tmp = tmp.next;	
			index++;
		}
		return index;
	}

	public static int findLive(int i , int m){
		if(i == 1){
			return 1;	
		}

		return (findLive(i-1, m) + m-1)%i+1; 
	}

}
