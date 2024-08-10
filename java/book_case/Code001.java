package book_case;

import java.util.*;
/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * Implement the MinStack class:
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 */
public class Code001{

	public static class MyStack{
		private LinkedList<Integer> dataStack;
		private LinkedList<Integer> minStack;
		public MyStack(){
			this.dataStack = new LinkedList<Integer>();
			this.minStack = new LinkedList<Integer>();
		}

		public Integer getMin(){
			if(minStack.isEmpty()){
				throw new RuntimeException("stack is Empty");
			}
			return minStack.peek();
		}

		public Integer pop(){
			if(dataStack.isEmpty()){
				throw new RuntimeException("stack is Empty");
			}
			int popResult = dataStack.pop();
			if(popResult == minStack.peek()){
				minStack.pop();
			}
			return popResult;
		}

		public void push(Integer num){
			if(minStack.isEmpty()){
				minStack.push(num);
			}else{
				if(minStack.peek()  >= num){
					minStack.push(num);
				}
			}
			dataStack.push(num);
		}
	}
	public static void main(String[] args){
		MyStack mystack = new MyStack();
		mystack.push(5);
		mystack.push(12);
		mystack.push(4);
		mystack.push(8);
		System.out.println(mystack.getMin());
		mystack.pop();
		System.out.println(mystack.getMin());
		mystack.pop();
		System.out.println(mystack.getMin());

	}
}
