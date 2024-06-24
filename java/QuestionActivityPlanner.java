/*
 *
 *
 *
 *
 *
 * CC直播的运营部门组织了很多运行活动，每个活动需要花费一定的时间参与，主播每参加一个活动即可得到一定的奖励，参与活动可以从任意活动开始，
 * 但一旦开始，就需要将后续活动参加完毕（注意，最后一个活动必须参与），活动之间存在一定的依赖关系（不存在环的情况），，现在给出所有的活动时间与依赖关系，
 * 以及给出有限的时间，请帮主播计算在有限的时间内，可以获得的最大奖励以及需要的最少时长。
 * 活动 | 活动所需时间（天） | 获得奖励（C）
 * A 3 2000
 * B 3 4000
 * C 2 2500
 * D 1 1600
 * E 4 3800
 * F 2 2600
 * G 4 4000
 * H 3 3500
 *
 * 如上图所示，给定有限时间为10天，可以获得的最大奖励为11700，需要的时长为9天，参加的活动为BDFH四个
 *
 * 输入描述：
 * 第一行输入数据N与D，表示有N项活动，D表示给予的时长，0<N<=1000, 0 < D <= 10000,
 * 从第二行开始到N+1行，每行描述一个活动的信息，其中第一项表示当前活动需要花费多少时间，
 * 第二项表示可以获得的奖励a，之后有N项数据，表示当前活动与其他活动的依赖关系，1表示有依赖，0表示无依赖，每项使用空格分开。
 *
 * 输出描述：
 * 输出两项数据A与T， 用空格分开，A表示所获得的最大奖励，T表示所需要的时长。
 *
 * 输入：
 * 8 10
 * 3 2000 0 1 1 0 0 0 0 0
 * 3 4000 0 0 0 1 1 0 0 0
 * 2 2500 0 0 0 1 0 0 0 0
 * 1 1600 0 0 0 0 1 1 1 0
 * 4 3800 0 0 0 0 0 0 0 1
 * 2 2600 0 0 0 0 0 0 0 1
 * 4 4000 0 0 0 0 0 0 0 1
 * 3 3500 0 0 0 0 0 0 0 0
 *
 * 输出：
 * 11700 9
 */
import java.util.*;
public class QuestionActivityPlanner{
	public static class Node{
		public int money;
		public int day;
		public int row;
		public int in;
		public int out;
		public List<Node> nexts;
		public List<Node> prevs;
	}

	public static class NodeInfo{
		public int money;

		public int day;
	}
	public static class Graph{
		public Node[]  nodes;


		public Node findInZero(){
			Node resultNode = null;
			for(Node node:this.nodes){
				if(node.out == 1){
					continue;
				}else{
					resultNode	 = node;
				}
			}
			return resultNode;
		}



		public void bfs(List<NodeInfo>[] nodeInfos ){
			Node node = findInZero();
			LinkedList<Node> queue = new LinkedList<>();
			Set<Integer> walkSet = new HashSet<>();
			queue.offer(node);
			List<Node> prevsList = node.prevs;
			while(!prevsList.isEmpty()){
				Node pollNode= queue.peekLast();
				if(!walkSet.contains(pollNode)){
					if(null != prevsList && prevsList.size()>0){
						for(Node prevNode :prevsList)	{
							if(!walkSet.contains(prevNode.row)){
								queue.offer(prevNode);	
							}else if(walkSet.contains(prevNode.row)){
								queue.remove(prevNode);
								queue.offer(prevNode);	
							}
							walkSet.add(prevNode.row);	
						}
					}
					prevsList = pollNode.prevs;
				}
			}

			while(!queue.isEmpty()){
				Node pollNode = queue.poll();
				List<NodeInfo> curNodeInfos = nodeInfos[pollNode.row];
				List<Node> nextsList = pollNode.nexts;
				if(null != nextsList && nextsList.size()> 0){
					for(Node nextnode : nextsList){
						List<NodeInfo> nodeInfo = nodeInfos[nextnode.row];	
						for(NodeInfo prevNodeInfo :nodeInfo){
							NodeInfo curNodeInfo = new NodeInfo();
							curNodeInfo.money +=prevNodeInfo.money;
							curNodeInfo.money +=pollNode.money;
							curNodeInfo.day +=prevNodeInfo.day;
							curNodeInfo.day +=pollNode.day;	
							curNodeInfos.add(curNodeInfo);	
						}
					}
				}else if(nextsList == null || nextsList.size() == 0){
					NodeInfo nodeInfo = new NodeInfo();
					nodeInfo.money = pollNode.money;
					nodeInfo.day = pollNode.day;
					curNodeInfos.add(nodeInfo);		
				}

			}
		}
	}


	public static Graph createGraph(List<String> ways){
		Node[] nodes = new Node[ways.size()];
		Integer[][] depArr = new Integer[ways.size()][];
		for(int i = 0;i< ways.size(); i++){
			Node node = new Node();
			String[] waysArray =ways.get(i).split(" ");	
			String dayStr = waysArray[0];
			String moneyStr = waysArray[1];
			node.day = Integer.parseInt(dayStr);
			node.money = Integer.parseInt(moneyStr);
			node.row = i;
			node.nexts = new ArrayList<>();
			node.prevs = new ArrayList<>();
			nodes[i] = node;
			depArr[i] = new Integer[ways.size()];
			for(int j=2;j< waysArray.length;j++){
				depArr[i][j-2] = Integer.parseInt(waysArray[j]); 
			}
		}
		for(int i=0;i< nodes.length; i++){
			for(int j=0;j< nodes.length;j++){
				if(depArr[i][j] == 1){
					nodes[i].nexts.add(nodes[j]);
					nodes[i].out = nodes[i].out +1;
					nodes[j].prevs.add(nodes[i]);
					nodes[j].in = nodes[j].in +1;
				}
			}
		}
		Graph graph = new Graph();
		graph.nodes = nodes;
		return graph;
	}


	public static int getMaxMoney(List<String> data ){
		String start = data.get(0);
		String[] metaArray = start.split(" ");
		Integer day= Integer.parseInt(metaArray[1]);
		List<String> ways = data.subList(1, data.size());
		Graph graph = createGraph(ways);
		List<NodeInfo>[] nodeInfos= new ArrayList[data.size()]; 
		for(int i=0;i< data.size();i++){
			nodeInfos[i]= new ArrayList<>();
		}
		graph.bfs(nodeInfos);
		Integer maxMoney = Integer.MIN_VALUE;
		for(List<NodeInfo> nodeInfoList: nodeInfos){
			for(NodeInfo nodeInfoItem : nodeInfoList){
				if(nodeInfoItem.day <= day && nodeInfoItem.money > maxMoney){
					maxMoney = nodeInfoItem.money;
				}	
			
			}
		}
		return maxMoney;

	}




	public static void main(String[] args){
		String[] data = {
			"8 10",
			"3 2000 0 1 1 0 0 0 0 0",
			"3 4000 0 0 0 1 1 0 0 0",
			"2 2500 0 0 0 1 0 0 0 0",
			"1 1600 0 0 0 0 1 1 1 0",
			"4 3800 0 0 0 0 0 0 0 1",
			"2 2600 0 0 0 0 0 0 0 1",
			"4 4000 0 0 0 0 0 0 0 1",
			"3 3500 0 0 0 0 0 0 0 0"
		};
		List<String> list = new ArrayList<String>();
		list.addAll(Arrays.asList(data));
		Integer maxMoney = getMaxMoney(list )	;	
		System.out.println(maxMoney);
	}

}
