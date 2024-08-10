package book_case;
import java.util.*;
/*
 * Implement a first in first out (FIFO) queue using only two stacks.
 * The implemented queue should support all the functions of a normal queue:
 * - push: Pushes element x to the back of the queue.
 * - pop: Removes the element from the front of the queue and returns it.
 * - peek: Returns the element at the front of the queue.
 * - empty: Returns true if the queue is empty, false otherwise.
 *
 * Notes:
 * - You must use only standard operations of a stack, which means only:
 *   - push to top
 *   - peek/pop from top
 *   - size
 *   - is empty
 *   operations are valid.
 * - Depending on your language, the stack may not be supported natively.
 *   - You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 * https://leetcode.cn/problems/implement-queue-using-stacks/solutions/
 */
public class Code002 {
	public static class MyQueue {
		LinkedList<Integer> pushStack;
		LinkedList<Integer> popStack;

		public MyQueue() {
			pushStack = new LinkedList<Integer>();
			popStack = new LinkedList<Integer>();
		}

		private void movePush2Pop(){
			if(popStack.isEmpty())	{
				while(!pushStack.isEmpty()){
					popStack.push(pushStack.pop())	;
				}
			}
		}


		public void push(int x) {
			pushStack.push(x);
			movePush2Pop();
		}

		public int pop() {
			if(empty()){
				throw new RuntimeException("no elements");
			}
			if(!popStack.isEmpty()){
				return popStack.pop();
			}
			movePush2Pop();
			return popStack.pop();
		}

		public int peek() {
			if(empty()){
				throw new RuntimeException("no elements");
			}
			if(!popStack.isEmpty()){
				return popStack.peek();
			}
			movePush2Pop();
			return popStack.peek();
		}

		public boolean empty() {
			return pushStack.isEmpty() && popStack.isEmpty();
		}
	}

	public static void main(String[] args){
		MyQueue mq = new MyQueue();
		mq.push(1);
		mq.push(5);
		int pop = mq.pop();
		if(pop == 1){
			System.out.println("pop正确");
		}

		mq.push(3);
		mq.push(7);
		pop = mq.pop();
		if(pop == 5){
			System.out.println("pop正确");
		}

	}


}
