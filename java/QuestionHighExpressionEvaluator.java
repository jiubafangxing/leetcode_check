import java.util.LinkedList;

/**
 * 给定一个字符串str，str表示一个公式，公式里可能有整数、加减乘除符号和左右括号。
 * 返回公式的计算结果，难点在于括号可能嵌套很多层。
 * 例如，str="48((70-65)-43)+81"，返回-1816。str="3+14"，返回7。str="3+(14)"，返回7。
 *
 * 【说明】
 * 1. 可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查。
 * 2. 如果是负数，就需要用括号括起来，比如“4(-3)”。
 *    但如果负数作为公式的开头或括号部分的开头，则可以没有括号，比如"-34"和"(-3*4)"都是合法的。
 * 3. 不用考虑计算过程中会发生溢出的情况
 */
public class QuestionHighExpressionEvaluator{

	public static class Info{
		public Integer v;

		public Integer i;

	}

	public static Info complexExpress(char[] arr, Integer start, Integer end){
		LinkedList<String> stack= new LinkedList<>();
		for (int i=start;i<end;i++){
			if(arr[i] == '('){
				Info info = complexExpress(arr,i+1,end);
				i = info.i-1;
				String symbolize = stack.peek();
				push(symbolize, stack, info.v);

			}else if(arr[i] == ')') {
				Integer tmpResult = getResult(stack);
				Info info = new Info();
				info.v = tmpResult;
				info.i = i+1;
				return info;
			}else{
				StringBuilder sb = new StringBuilder();
				if(arr[i] >= '0' && arr[i] <= '9'){
					while(i<arr.length && arr[i] >= '0' && arr[i] <= '9'){
						sb.append(arr[i]);
						i++;
					}
					i--;
					if(!stack.isEmpty()){
						String symbolize = stack.peek();
						int repush = Integer.parseInt(sb.toString());
						push(symbolize, stack,repush);
					}else{
						stack.push(sb.toString());
					}
				}else{
					sb.append(arr[i]);
					stack.push(sb.toString());
				}
			}

		}
		Integer result = getResult(stack);
		Info info = new Info();
		info.v = result;
		info.i = end ;
		return info;
	}

	private static Integer  getResult(LinkedList<String> stack) {
		Integer tmpResult = 0;
		while(!stack.isEmpty()){
			String lastNum  = stack.pop();
			if(!stack.isEmpty()){
				String esym = stack.pop();
				String efirNum = stack.pop();
				Integer rePutResult = 0;
				if(esym .equals( "+")){
					rePutResult = Integer.parseInt(efirNum) + Integer.parseInt(lastNum);
				}else{
					rePutResult = Integer.parseInt(efirNum) - Integer.parseInt(lastNum);
				}
				stack.push(rePutResult+"");
			}else{
				tmpResult = Integer.parseInt(lastNum);
				break;
			}
		}
		return tmpResult;
	}

	private static void push(String symbolize, LinkedList<String> stack, Integer repush) {
		if(null != symbolize && (symbolize.equals( "*") || symbolize.equals( "/"))){
			String sym = stack.pop();
			String firNum = stack.pop();
			Integer result = null;
			if(sym.equals( "*")){
				result = Integer.parseInt(firNum)  * repush;
			}else{
				result = Integer.parseInt(firNum) / repush;
			}
			stack.push(result.toString());
		}else{
			stack.push(repush.toString())	;
		}
	}


	public static int express(char[] arr, Integer start, Integer end){
		LinkedList<String> stack= new LinkedList<>();
		for(int i=start;i<end;i++){
			StringBuilder sb = new StringBuilder();
			if(arr[i] >= '0' && arr[i] <= '9'){
				while(i<arr.length && arr[i] >= '0' && arr[i] <= '9'){
					sb.append(arr[i]);
					i++;
				}
				i--;
				if(!stack.isEmpty()){
					String symbolize = stack.peek();
					int repush = Integer.parseInt(sb.toString());
					push(symbolize, stack,repush);
				}else{
					stack.push(sb.toString());
				}
			}else{
				sb.append(arr[i]);
			}
		}
		Integer result = getResult(stack);
		return result;	
	}


	public static void main(String[] args){
	
		String exp = "(21*3)+1-12*2";
		Info info = complexExpress(exp.toCharArray(), 0, exp.length());
		System.out.println(info.v);
//		int result = express(exp);
//		System.out.println("result is "+ result);

	}
}
