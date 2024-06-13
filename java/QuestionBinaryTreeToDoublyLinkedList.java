/*
 * The structure of a doubly linked list node is the same as that of a binary tree node, 
 * if you consider 'last' to be equivalent to 'left' and 'next' to be equivalent to 'right'. 
 * Given the head node of a binary search tree, please transform it into an ordered doubly linked list 
 * and return the head node of the list.
 */
public class QuestionBinaryTreeToDoublyLinkedList {
	public static class TreeNode {

		public Integer value;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(Integer value) {
			this.value = value;
		}
	}

	public static class LinkedListItem {
		public TreeNode head;

		public TreeNode tail;
	}

	public static TreeNode convert(TreeNode root) {
		LinkedListItem item = process(root);
		return item.head;
	}

	public static LinkedListItem process(TreeNode node) {
		if (null == node) {
			return null;
		}
		LinkedListItem result = new LinkedListItem();
		result.head = node;
		TreeNode leftTreeNode = node.left;
		LinkedListItem leftItem = null;
		if (null != leftTreeNode) {
			leftItem = process(leftTreeNode);
			TreeNode nodet = leftItem.tail;
			nodet.right = node;
			node.left = nodet;
			result.head = leftItem.head;
		}
		TreeNode rightTreeNode = node.right;
		LinkedListItem rightItem = null;
		if (null != rightTreeNode) {
			rightItem = process(rightTreeNode);
			TreeNode rightH = rightItem.head;
			rightH.left = node;
			node.right = rightH;
			result.tail = rightItem.tail;

		}
		// 如果经过了上述步骤，尾部节点依然为空，则以当前节点为尾部节点
		if(result.tail == null)
			result.tail = node;
		// 如果经过了上述步骤，头部节点依然为空，则以当前节点为头部节点
		if(result.head == null)
			result.head = node;
		return result;
	}

	public static void main(String[] args) {
		// 创建根节点
		TreeNode root = new TreeNode(1);

		// 创建左子树
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		// 创建右子树
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		QuestionBinaryTreeToDoublyLinkedList converter = new QuestionBinaryTreeToDoublyLinkedList();
		TreeNode node = converter.convert(root);
		while (node != null) {
			System.err.println(node.value);
			node = node.right;
		}
	}

}
