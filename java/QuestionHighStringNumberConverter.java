/*
 * 问题描述：
 * 问题背景：
 * 给定一个字符数组 chs，其中所有字符都是唯一的。需要实现两个函数，分别用于将整数转换为字符串和将字符串转换为整数，遵循特定的对应关系。
 * 对应关系规则：
 * 字符数组 chs 中的每个字符代表一个数字，从1开始。
 * 字符串由 chs 中的字符组成，按照字符顺序排列，形成递增的数字序列。
 * 例如，如果 chs = ['A', 'B', 'C']，那么 "A" 对应数字1，"B" 对应数字2，"C" 对应数字3，"AA" 对应数字4，以此类推。
 * 示例：
 * 如果 chs = ['A', 'B', 'C']，则对应关系如下：
 * "A" 对应 1
 * "B" 对应 2
 * "C" 对应 3
 * "AA" 对应 4
 * "AB" 对应 5
 * ...
 * "CCC" 对应 39
 * 性能要求：
 * 数字转字符串的复杂度应为 O(log n)
 * 字符串转数字的复杂度应为 O(字符串长度)
 * 输入描述：
 * 第一行包含两个整数 opt 和 base，分别表示问题类型和字符数组 chs 的长度。
 * 第二行包含 base 个字符，表示字符数组 chs。
 * 如果 opt = 1，则下一行包含一个整数 N，表示需要转换为字符串的数字。
 * 如果 opt = 2，则下一行包含一个字符串，表示需要转换为数字的字符串。
 * 输出描述：
 * 根据 opt 的值，输出转换后的字符串或数字。
 * 实现要求：
 * 使用 Java 语言实现。
 * 需要定义一个类，包含两个方法：numberToString 和 stringToNumber。
 * 示例类名：
 * 可以命名为 StringNumberConverter。
 */
public class QuestionHighStringNumberConverter{


	public static String numberToString(int number){
		int k = 0;
		int result = number /( (int)Math.pow(26,k));
		while(result >  0 ){
			k++;
			result = number /( (int)Math.pow(26,k));
		}
		int tmp = number;		
		StringBuffer sb  = new StringBuffer();
		for(int i = k-1; i>=0 ; i--){
			int index = tmp / ((int)Math.pow(26,i))	;	
			char c = (char)('A' + (index - 1));
			sb.append(c);
			tmp = tmp %((int)Math.pow(26,i));
		}
		return sb.toString();
	}

	public static void main(String[] argc){
		String result = numberToString(2321)	;
		System.out.println("result is "+ result);
		result = numberToString(40)	;
		System.out.println("result is "+ result);
	}

}
