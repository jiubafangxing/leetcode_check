package book_case;

import java.util.LinkedList;

public class Code003 {

	public static void reverseStack(LinkedList<Integer> stack){
		if(stack.isEmpty()){
			return;
		}
		int elem = getAndRemoveLastElem(stack);	
		reverseStack(stack);
		stack.push(elem);
		return;	
	}

	public static int getAndRemoveLastElem(LinkedList<Integer> stack){
		Integer elem = stack.pop();
		if(stack.isEmpty()){
			return elem;
		}else{
			Integer result = getAndRemoveLastElem(stack);
			stack.push(elem);
			return result;
		}
	}
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.push(1);
		list.push(2);
		list.push(3);
		list.push(4);
		list.push(5);
		reverseStack(list);
		if (1 != list.pop()){
			System.out.println("err");
		}
		if(2 != list.pop()){
			System.out.println("err");
		}
	}
}
