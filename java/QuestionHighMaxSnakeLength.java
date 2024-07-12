/**
 * 定一个二维数组matrix，每个单元都是一个整数，有正有负。最开始的时候小Q操纵 一条长度为0的蛇蛇从矩阵最左侧任选一个单元格进入地图，蛇每次只能够到达当前位 置的右上相邻，右侧相邻和右下相邻的单元格。蛇蛇到达一个单元格后，自身的长度会瞬间加上该单元格的数值，任何情况下长度为负则游戏结束。小Q是个天才，他拥有一 个超能力，可以在游戏开始的时候把地图中的某一个节点的值变为其相反数(注:最多 只能改变一个节点)。
 *
 * 问在小Q游戏过程中，他的蛇蛇最长长度可以到多少?
 * 比如:
 * 1   -4   10
 * 3   -2   -1
 * 2   -1   0
 * 0   5    -2
 * 最优路径为从最左侧的3开始，3 -> -4(利用能力变成4) -> 10。所以返回17。
 *
 */
public class QuestionHighMaxSnakeLength{

	public static class Info{
		public Integer yes;

		public Integer no;
	}

	public static int maxSnakeLength(int[][] matrix){
		if(matrix == null || matrix.length == 0){
			return 0;
		}
		Integer maxResult = 0;
		for(int i=0; i< matrix.length; i++){
			for(int j=0; j< matrix[i].length;j++){
				Info result = p(matrix,0,2);
				int maxInInfo = Math.max(result.no,result.yes);
				if(maxInInfo > maxResult){
					maxResult =maxInInfo;
				}
			}
		}
		return maxResult;


	}

	public static Info p(int[][] matrix, int i , int j){
		if(j == 0){
			Info baseInfo = new Info();
			baseInfo.yes = matrix[i][j]*-1;
			baseInfo.no =  matrix[i][j];
			return baseInfo;
		}

		Integer preYesResult = Integer.MIN_VALUE;
		Integer preNoResult = Integer.MIN_VALUE;
		if(i > 0){
			Info postInfo1 = p(matrix,i-1,j-1);
			if(postInfo1.no > preNoResult){
				preNoResult = postInfo1.no ;
			}				
			if(postInfo1.yes > preYesResult){
				preYesResult = postInfo1.yes;
			}
		}
		Info innerInfo1= p(matrix,i,j-1);

		if(innerInfo1.no > preNoResult){
			preNoResult = innerInfo1.no;
		}				
		if(innerInfo1.yes > preYesResult){
			preYesResult = innerInfo1.yes;
		}
		if(i < matrix.length-1){
			Info rearInfo1 = p(matrix,i+1,j-1);
			if(rearInfo1.no > preNoResult){
				preNoResult = rearInfo1.no;
			}				
			if(rearInfo1.yes > preYesResult){
				preYesResult = rearInfo1.yes;
			}
		}
		Integer yesResult = 0;
		Integer noResult= 0;
		if(preNoResult >= 0){
			yesResult = preNoResult+ matrix[i][j]*-1;
		}
		if(preYesResult >= 0){
			noResult = preYesResult + matrix[i][j];
			noResult = Math.max(noResult, preNoResult + matrix[i][j]);
		}
		Info resultInfo = new Info();
		resultInfo.yes = yesResult;
		resultInfo.no = noResult;
		return resultInfo;
	}
	public static void main(String[] args){
		int[][] matrix = {
			{1,-4,10},
			{3,-2,-1},
			{2,-1, 0},
			{0,5,-2}
		};
		int result = maxSnakeLength(matrix);	
		System.out.println("result is "+ result);
	}

} 
