import java.util.*;
/*
 * 求完全二叉树的节点个数
 * the number of nodes in a complete binary tree
 */
public class QuestionCompleteBinaryTreeNodeCounter{
	public static class TreeNode{
		public TreeNode left;

		public TreeNode right;

		public int value;

		public TreeNode(Integer value){
			this.value = value;
		}
	}
	public static int calculateNodesInCompleteBinaryTree(TreeNode root){
		if(null == root){
			return 0;
		}
		Integer height = countLevel(root,1);
		return bs(root, 1, height);
	}

	public static int bs(TreeNode root, int l, int h){
		if(l == h){
			return 1;
		}
		int rightHeight = countLevel(root.right, l+1);
		if(rightHeight == h){
			return (1 << h-l) + bs(root.right, l+1, h);
		}else{
			return  (1 <<( h-l-1))+ bs(root.left, l+1,h);
		}
	}

	public static int countLevel(TreeNode root , Integer currentL){
		TreeNode query = root;
		while(query != null){
			currentL++;
			query = query.left;
		}
		return currentL-1;
	}

	public static TreeNode createCompleteBinaryTree(int[] values) {
		if (values == null || values.length == 0) {
			return null;
		}

		TreeNode root = new TreeNode(values[0]);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		int i = 1;
		while (i < values.length) {
			TreeNode current = queue.poll();

			if (i < values.length) {
				current.left = new TreeNode(values[i++]);
				queue.offer(current.left);
			}

			if (i < values.length) {
				current.right = new TreeNode(values[i++]);
				queue.offer(current.right);
			}
		}

		return root;
	}

	public static void printLevelOrder(TreeNode root) {
		if (root == null) {
			return;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();
			if (current.left != null) {
				queue.offer(current.left);
			}

			if (current.right != null) {
				queue.offer(current.right);
			}
		}
	}

	public static void main(String[] args){
	    	int[] values = {1, 2, 3, 4, 5, 6, 7}; // 完全二叉树的节点值
		TreeNode root = createCompleteBinaryTree(values);	
		int allCount = calculateNodesInCompleteBinaryTree(root);		
		System.out.println("allCount is "+ allCount);
	}

}
