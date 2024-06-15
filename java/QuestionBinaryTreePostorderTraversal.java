import java.util.*;
/*
 * 已知二叉树元素不重复。且给定前序、中序遍历的数组，求二叉树的后序遍历数组
 */
public class QuestionBinaryTreePostorderTraversal{
	public static int[] postOrderArray(int[] pre, int in[]){
		Map<Integer, Integer> dict = new HashMap();	
		for(Integer i=0; i< in.length; i++){
			dict.put(in[i], i);
		}
		int[] post = new int[in.length];
		setArray(pre, 0, pre.length-1, in, 0, in.length-1, post, 0, post.length-1, dict);	
		return post;
	}


	public static void setArray(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd, int[] post, int postStart, int postEnd, Map<Integer, Integer> dict){
		Integer findValue = pre[preStart];	
		Integer inIndex = dict.get(findValue);
		if(preStart > preEnd ){
			return;
		}
		
		if(preStart ==  preEnd){
			post[postEnd] = pre[preStart];
			return;
		}
		post[postEnd] = pre[preStart];
		setArray(pre,preStart+1, preStart+inIndex-inStart, in, inStart ,inIndex-1,post, postStart, postStart + inIndex-inStart-1, dict);
		setArray( pre, preStart+inIndex-inStart+1, preEnd, in ,inIndex+1, inEnd, post, postStart+inIndex-inStart, postEnd-1, dict);
	}
	
	public static void main(String[] args){
	 	int[] preorder = {3, 9, 20, 15, 7};
		int[] inorder = {9, 3, 15, 20, 7};
		int[] postOrder = postOrderArray(preorder, inorder);
		for(int i : postOrder){
			System.out.println(i);
		}
	}


} 
