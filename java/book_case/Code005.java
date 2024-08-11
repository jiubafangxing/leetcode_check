import java.util.*;
public class Code005{
	public static class SortedStack {
		public Stack<Integer> dataStack; 
		public Stack<Integer> helpStack; 
		public SortedStack() {
			dataStack = new Stack<>();
			helpStack = new Stack<Integer>();
		}

		public void push(int val) {
			if(dataStack.isEmpty()){
				dataStack.push(val);
			}else{
				if(dataStack.peek() > val){
					dataStack.push(val);
				}else{
					while(!dataStack.isEmpty() && dataStack.peek() < val){
						helpStack.push(dataStack.pop());
					}
					dataStack.push(val);
					while(!helpStack.isEmpty()){
						dataStack.push(helpStack.pop());
					}
					
				}
			}
		}

		public void pop() {
			if(dataStack.isEmpty()){
				return ;
			}
			dataStack.pop();
		}

		public int peek() {
			if(dataStack.isEmpty()){
				return -1;
			}
			return dataStack.peek();

		}

		public boolean isEmpty() {
			return dataStack.isEmpty() && helpStack.isEmpty();
		}
	}

	public static void main(String[] args){
		SortedStack sortedStack = new SortedStack();
		sortedStack.push(1);
		sortedStack.push(2);
		System.out.println(sortedStack.peek());
		sortedStack.pop();
		System.out.println(sortedStack.peek());
	}
}
