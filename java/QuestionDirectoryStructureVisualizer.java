/*
 * 给你一个字符串类型的数组arr，譬如： String[] arr = { "b\\cst", "d\\", "a\\d\\e", "a\\b\\c" };
 * 你把这些路径中蕴含的目录结构给画出来，子目录直接列在父目录下面，并比父目录 向右进两格
 *
 * The task you've described is to visualize a directory structure from a given array of strings that represent paths.
 * Here is the English translation of the task description:
 *
 * Given an array of strings, for example: String[] arr = { "b\\cst", "d\\", "a\\d\\e", "a\\b\\c" };
 * you need to visualize the implied directory structure from these paths.
 * Subdirectories should be listed directly under their parent directories, indented two spaces to the right.
 * The visualization should look like this:
 *
 * a
 *   b
 *     c
 *     d
 *       e
 * b
 *   cst
 * d
 */
import java.util.*;
public class QuestionDirectoryStructureVisualizer{
	public static class Node{
		public Integer pass;
		public Integer end;

		public Map<String,Node> paths;
		public Node(Integer pass, Integer end, Map<String,Node> pathMap){
			this.pass = pass;
			this.end = end;
			this.paths = pathMap;
		}
	}
	public static class Tree{
		public Node root;

		public void insert(String item){
		  	String splitStr = "\\\\";
			Integer level = 1;
			Node parentNode= root;
			String[] dirItems = item.split(splitStr);
			root.pass++;
			for(String dirItem:dirItems){
				Node  subnode = parentNode.paths.get(dirItem);	
				if(null == subnode){
					subnode= new Node(1, 0, new TreeMap<>());
		
					parentNode.paths.put(dirItem, subnode);
				}else{
					subnode.pass++;
				}
				parentNode = subnode;
			}
			parentNode.end++;
		}

		public void printAll(){
			printNode(root, 0);
		}

		public void printNode(Node node, Integer level){
			Map<String,Node> map = node.paths;	
			Set<String> keys = map.keySet();
			String b = blankStr(level);
			for(String key :keys){
				System.out.println(b+key);	
				Node subNode = map.get(key);
				printNode(subNode, level+1);
			}
		
		}

		public String blankStr(int level){
			StringBuffer sb = new StringBuffer();
			for(int i=0; i< level;i++){
				sb.append(" ");	
			}	
			return sb.toString();
		}
	}
	public static void visualizeDirectories(String[] arr){
		Tree tree = new Tree();
		Node node = new Node(0,0, new TreeMap<String,Node>());
		tree.root = node;
		for(String str:arr){
			tree.insert(str);
		}
		tree.printAll();

	}
	public static void main(String[] args){
		String[] arr = { "b\\cst", "d\\", "a\\d\\e", "a\\b\\c" };	
		
		visualizeDirectories(arr);
	}
}
