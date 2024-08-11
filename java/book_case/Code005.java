package book_case;

import java.util.*;
/*
 * Sort of Stacks LCCI
 * Write a program to sort a stack such that the smallest items are on the top.
 * You can use an additional temporary stack, but you may not copy the elements into any other data structure (such as an array).
 * The stack supports the following operations: push, pop, peek, and isEmpty.
 * When the stack is empty, peek should return -1.
 */
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
