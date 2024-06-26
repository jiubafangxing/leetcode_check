/*
 * 对于一个只由0(假)、1(真)、&(逻辑与)、|(逻辑或)和^(异或)五种字符组成的逻辑表达式，
 * 再给定一个结果值。现在可以对这个没有括号的表达式任意加合法的括号，
 * 返回得到能有多少种加括号的方式，可以达到这个结果。
 * 
 * 给定一个字符串表达式exp及它的长度len，同时给定结果值ret，
 * 请返回方案数。保证表达式长度小于等于300。
 * 为了防止溢出，请返回答案Mod 10007的值。
 * 
 * 测试样例：
 * "1^0|0|1",7,0
 * 返回：2 
 *
 * For a logical expression composed only of the characters 0 (false), 1 (true), & (logical AND),
 * | (logical OR), and ^ (logical XOR), and given a result value,
 * you can now add legal parentheses to this expression without any existing parentheses.
 * Return the number of ways to add parentheses that can achieve this result.
 * 
 * Given a string expression exp and its length len, as well as a result value ret,
 * please return the number of schemes. It is guaranteed that the length of the expression is less than or equal to 300.
 * To prevent overflow, please return the value of the answer modulo 10007.
 * 
 * Test case:
 * "1^0|0|1", 7, 0
 * Return: 2
 */
public class QuestionLogicExpressionEvaluator{

	public static int evaluate(String expression, Integer expected){
		char[] array = expression.toCharArray();	
		return p(array, 0, array.length-1, expected);
	}

	public static int p(char[] arr , Integer L, Integer R, Integer expected){
		if(L == R){
			if(arr[L] == '1'){
				if(expected == 1){
					return 1;
				}else{
					return 0;
				}
			}else{
				if(expected == 0){
					return 1;
				}else{
					return 0;
				}
			}
		}
		if(!valid(arr, L, R)){
			return 0;
		}

		Integer ways = 0;
		for(int i=0; L+2*i+1 < R+1;i++){
			int right = L+2*i;
			int left =L+ 2*(i+1);
			char ch = arr[L+2*i+1];
			if(expected == 1){
				if(ch ==  '|'){
					Integer leftWaysTrue	=p(arr, L, right,1);	
					Integer rightWaysTrue = p(arr,left, R,1);	
					Integer leftWaysFalse	=p(arr, L, right,0);	
					Integer rightWaysFalse = p(arr,left, R,0);	
					ways += leftWaysTrue * rightWaysTrue;
					ways += leftWaysFalse * rightWaysTrue;
					ways += leftWaysTrue * rightWaysFalse;
				}else if (ch == '&'){
					Integer leftWaysTrue	=p(arr, L,right,1);	

					Integer rightWaysTrue = p(arr,left, R,1);	
					ways += leftWaysTrue * rightWaysTrue;
				}else{
					Integer leftWaysTrue	=p(arr, L, right,1);	
					Integer rightWaysTrue = p(arr, left, R,1);	
					Integer leftWaysFalse	=p(arr, L, L+2*i,0);	
					Integer rightWaysFalse = p(arr,left, R,0);	
					ways += leftWaysFalse * rightWaysTrue;
					ways += leftWaysTrue * rightWaysFalse;
				}
			}else if (expected == 0){
				if(ch ==  '|'){
					Integer leftWaysFalse	=p(arr, L, right,0);	
					Integer rightWaysFalse = p(arr,left, R,0);	
					ways += leftWaysFalse * rightWaysFalse;
				}else if (ch == '&'){
					Integer leftWaysTrue	=p(arr, L,right,1);	
					Integer rightWaysTrue = p(arr, left, R,1);	
					Integer leftWaysFalse	=p(arr, L, right,0);	
					Integer rightWaysFalse = p(arr, left, R,0);	
					ways += leftWaysFalse * rightWaysTrue;
					ways += leftWaysTrue * rightWaysFalse;
					ways += leftWaysFalse * rightWaysFalse;
				}else{
					Integer leftWaysTrue	=p(arr, L, right,1);	
					Integer rightWaysTrue = p(arr, left, R,1);	
					Integer leftWaysFalse	=p(arr, L, right,0);	
					Integer rightWaysFalse = p(arr, left, R,0);	
					ways += leftWaysTrue * rightWaysTrue;
					ways += leftWaysFalse * rightWaysFalse;
				}

			}
		}
		return ways;
	}

	public static int evaluate2(String exp , Integer expected){
		char[] arr = exp.toCharArray();
		int[][] dpTrue = new int[arr.length][arr.length];
		int[][] dpFalse = new int[arr.length][arr.length];
		for(int i=0;i< arr.length;i= i+2){
			dpTrue[i][i] = arr[i] == '1'? 1:0;			
			dpFalse[i][i] = arr[i] == '0'? 1:0;			
		}

		for(int i = arr.length-3; i >=0 ; i = i-2){
			for(int j = i+2; j< arr.length; j= j+2){
				for(int z= i+1; z< j ; z+=2)	{
					int right = z-1;
					int left =z+1;
					int ways = dpTrue[i][j];
						if(arr[z] ==  '|'){
							Integer leftWaysTrue	=dpTrue[i][right];	
							Integer rightWaysTrue =dpTrue[left][j];	
							Integer leftWaysFalse	=dpFalse[i][right];	
							Integer rightWaysFalse = dpFalse[left][j];	
							ways += leftWaysTrue * rightWaysTrue;
							ways += leftWaysFalse * rightWaysTrue;
							ways += leftWaysTrue * rightWaysFalse;
						}else if (arr[z] == '&'){
							Integer leftWaysTrue	=dpTrue[i][right];	
							Integer rightWaysTrue =dpTrue[left][j];	
							ways += leftWaysTrue * rightWaysTrue;
						}else{
							Integer leftWaysTrue	=dpTrue[i][right];	
							Integer rightWaysTrue =dpTrue[left][j];	
							Integer leftWaysFalse	=dpFalse[i][right];	
							Integer rightWaysFalse = dpFalse[left][j];	
							ways += leftWaysFalse * rightWaysTrue;
							ways += leftWaysTrue * rightWaysFalse;
						}
						dpTrue[i][j] = ways;
						int falseWays = dpFalse[i][j];
						if(arr[z]==  '|'){
							Integer leftWaysFalse	=dpFalse[i][right];	
							Integer rightWaysFalse = dpFalse[left][j];	
							falseWays += leftWaysFalse * rightWaysFalse;
						}else if (arr[z] == '&'){
							Integer leftWaysTrue	=dpTrue[i][right];	
							Integer rightWaysTrue =dpTrue[left][j];	
							Integer leftWaysFalse	=dpFalse[i][right];	
							Integer rightWaysFalse = dpFalse[left][j];	
							falseWays += leftWaysFalse * rightWaysTrue;
							falseWays += leftWaysTrue * rightWaysFalse;
							falseWays += leftWaysFalse * rightWaysFalse;
						}else{
							Integer leftWaysTrue	=dpTrue[i][right];	
							Integer rightWaysTrue =dpTrue[left][j];	
							Integer leftWaysFalse	=dpFalse[i][right];	
							Integer rightWaysFalse = dpFalse[left][j];	
							falseWays += leftWaysTrue * rightWaysTrue;
							falseWays += leftWaysFalse * rightWaysFalse;
						}
						dpFalse[i][j] = falseWays;
					}
				}
			}
			return expected == 1? dpTrue[0][arr.length-1]: dpFalse[0][arr.length-1];
	}
	public static boolean valid(char[] arr, int start, int end){
		for(int i=0;i< end+1;i++){
			char c = arr[i];
			if(i%2 == 0){
				if(c != '1' && c != '0'){
					return false;
				}
			}else{
				if(c != '|' && c != '&' && c!= '^'){
					return false;	
				}
			}
		}	
		return true;
	}


	public static void main(String[] args){
		String expression = "1^0|0|1";
		Integer result = evaluate2(expression, 0);
		System.out.println("result is "+ result);

	}

}
