/*
 * 给定一棵二叉树的头节点head，已知其中所有节点的值都不一样，找到含有节点最多的搜索二叉子树，并返回这棵子树的头节点。
 * Given the head node of a binary tree, where all nodes have unique values, find the search binary subtree with the most nodes and return the head node of this subtree.
 */
public class QuestionLargestBSTSubtreeFinder{
	public static class TreeNode {
		Integer value;
		TreeNode left;
		TreeNode right;

		public TreeNode(Integer value, TreeNode left, TreeNode right){
			this.value = value;
			this.left = left;
			this.right = right;
		}

	}

	public static class NodeInfo{
		boolean isBst;

		int max;

		int min;

		TreeNode treeNode;

		Integer size;
	}
	public static  TreeNode findLargest(TreeNode root){

		NodeInfo nodeInfo = process(root);
		return nodeInfo.treeNode;	
	}

	public static NodeInfo process(TreeNode treeNode){
		if(null == treeNode){
			return null;
		}
		NodeInfo leftInfo = process(treeNode.left);	
		NodeInfo rightInfo= process(treeNode.right);	
		if(treeNode.value == 15){
			System.out.println("leftInfo is "+ leftInfo.size);
			System.out.println("rightInfo is "+ rightInfo.size);
		}
		NodeInfo result = new NodeInfo();
		result.isBst = true;
		if(null != leftInfo){
			if(!leftInfo.isBst || leftInfo.max >= treeNode.value){
				result.isBst = false;
			}
		}
		if(null != rightInfo){
			if(!rightInfo.isBst || rightInfo.min < treeNode.value){
				result.isBst = false;
			}
		}
		result.min= treeNode.value;
		result.max=  treeNode.value;
		result.size = 0;
		if(result.isBst){
			if(null == rightInfo && null == leftInfo){
				result.size = 1;
			}
			if(null == rightInfo && null != leftInfo){
				result.size += leftInfo.size;
				result.min= null != leftInfo ? leftInfo.min : result.min;
			}
			if(null == leftInfo && null != rightInfo){
				result.size += rightInfo.size;
				result.max= rightInfo.max;
			}
			if(null != rightInfo && null != leftInfo){
				result.min=  leftInfo.min ;
				result.max = rightInfo.max;
				result.treeNode = treeNode;
				result.size = leftInfo.size+rightInfo.size+1;
			}
		}else{
			if(null != leftInfo && leftInfo.isBst){
				if(leftInfo.size > result.size){
					result.size = leftInfo.size;
					result.max = leftInfo.max;
					result.min = leftInfo.min;
					result.treeNode = leftInfo.treeNode;
				}
			}
			if(null != rightInfo && rightInfo.isBst  ){
				if(rightInfo.size > result.size){
					result.size = rightInfo.size;
					result.max = rightInfo.max;
					result.min = rightInfo.min;
					result.treeNode = rightInfo.treeNode;
				}
			}
		}
		return result;
	}
	public static void main(String[] args){

		// 创建第一个左子树 (搜索二叉树)
		TreeNode leftSubTree = new TreeNode(2, new TreeNode(1, null, null), new TreeNode(3, null, null));

		// 创建第一个右子树 (不是搜索二叉树)
		TreeNode rightSubTree = new TreeNode(6, new TreeNode(5, null, null), new TreeNode(7, null, null));

		// 创建根节点
		TreeNode root = new TreeNode(4, leftSubTree, rightSubTree);
		TreeNode node = findLargest(root);
		System.out.println("result is "+ node.value);
	}
}
	