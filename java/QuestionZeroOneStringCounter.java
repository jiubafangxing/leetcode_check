/*
*  题目：
* 给定一个正整数 N，求由 '0' 字符与 '1' 字符组成的长度为 N 的所有字符串中，满足 '0' 字符的左边必须有 '1' 字符的字符串数量。
* 示例：
* N = 1：
* 所有长度为 1 的由 '0' 和 '1' 组成的字符串：'0'、'1'。
* 只有字符串 '1' 满足条件，所以返回 1。
* N = 2：
* 所有长度为 2 的由 '0' 和 '1' 组成的字符串：'00'、'01'、'10'、'11'。
* 只有字符串 '10' 和 '11' 满足条件，所以返回 2。
* N = 3：
* 所有长度为 3 的由 '0' 和 '1' 组成的字符串：'000'、'001'、'010'、'011'、'100'、'101'、'110'、'111'。
* 字符串 '101'、'110'、'111' 满足条件，所以返回 3。

* Question:
* Given an integer N, find the number of strings of length N consisting of '0' and '1' characters, where there must be a '1' character to the left of every '0' character.
* Example:
* N = 1:
* All strings of length 1 consisting of '0' and '1': '0', '1'.
* Only the string '1' satisfies the condition, so return 1.
* N = 2:
* All strings of length 2 consisting of '0' and '1': '00', '01', '10', '11'.
* Only the strings '10' and '11' satisfy the condition, so return 2.
* N = 3:
* All strings of length 3 consisting of '0' and '1': '000', '001', '010', '011', '100', '101', '110', '111'.
* Strings '101', '110', '111' satisfy the condition, so return 3.
**/
public class QuestionZeroOneStringCounter {

	public int count(int num) {
		if (num == 1) {
			return 1;
		}
		if (num == 2) {
			return 2;
		}
		int[][] helpArray = {
				{ 1, 1 },
				{ 1, 0 }
		};
		int[][] matrixResult = powerMatrix(helpArray, num - 2);
		int[][] initStart = {
				{ 2, 1 }
		};
		int[][] result = matrixMultiply(initStart, matrixResult);
		// int sum = 0;
		// for(int i=0;i< result.length;i++){
			// sum+=result[i][0];
		// }

		return result[0][0] ;
	}

	public int[][] powerMatrix(int[][] helpArray, int n) {
		int cur = n;
		int[][] res = new int[helpArray.length][helpArray[0].length];
		for (int i = 0; i < res.length; i++) {
			res[i][i] = 1;
		}
		while (cur > 0 ) {
			if ((cur & 1) == 1) {
				res = matrixMultiply(res, helpArray);
			}
			helpArray = matrixMultiply(helpArray, helpArray);
			cur >>= 1;
		}
		return res;

	}

	public int[][] matrixMultiply(int[][] a, int[][] b) {
		int[][] resultArray = new int[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				int sumItem = 0;
				for (int z = 0; z < a[0].length; z++) {
					sumItem += a[i][z] * b[z][j];
				}
				resultArray[i][j] = sumItem;
			}
		}
		return resultArray;
	}

	public static void main(String[] args) {
		QuestionZeroOneStringCounter counter = new QuestionZeroOneStringCounter();
		int result = counter.count(5);
		System.out.println(result);
	}

}
