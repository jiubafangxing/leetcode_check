public class QuestionHighUniqueCoinCombinations {

	public static int calculateCombinations(int[] regularCoins, int[] commemorativeCoins, int targetAmount) {
		int[][] dp1 = new int[regularCoins.length][targetAmount + 1];
		int[][] dp2 = new int[commemorativeCoins.length + 1][targetAmount + 1];

		dp1 = findresult1(regularCoins, targetAmount);
		// dp2 含义 从cur开始到结尾 满足和可以是target的方法个数
		dp2 = findresult2(commemorativeCoins, targetAmount);
		Integer result = 0;
		for (int i = 0; i <= targetAmount; i++) {
			int dp1targetAmount = i;
			int dp2targetAmount = targetAmount - i;
			int dp1R = dp1[regularCoins.length - 1][dp1targetAmount];
			int dp2R = 0;
			dp2R += dp2[0][dp2targetAmount];
			if (dp1R != 0 && dp2R != 0) {
				result += dp1R * dp2R;
			}
		}
		return result;
	}

	public static int[][] findresult1(int[] regualarCoins, int targetAmount) {
		Integer l = regualarCoins.length;
		int[][] dp = new int[l][targetAmount + 1];
		for (int i = 1; i < targetAmount + 1; i++) {
			int num = regualarCoins[0];
			if (i % num == 0) {
				dp[0][i] = 1;
			} else {
				dp[0][i] = 0;

			}
		}
		for (int i = 0; i < l; i++) {
			dp[i][0] = 1;
		}
		for (int  i= 1; i < dp.length; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				if(i == 1 && j == 2){
					System.out.println("debug");
				}
				Integer result = 0;
				if (i - 1 >= 0) {
					int lastColumn = dp[i - 1][j];
					result += lastColumn;
				}
				if (j - regualarCoins[i] >= 0) {
					int lastRow = dp[i][j - regualarCoins[i]];
					result += lastRow;
				}
				dp[i][j] = result;
			}
		}
		return dp;
	}

	public static int[][] findresult2(int[] c, int t) {
		int[][] dp = new int[c.length ][t + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		for (int i = dp.length -1 ; i >= 0 ; i--) {
			for (int j = 1; j < dp[i].length; j++) {
				int result = 0;
				if(j > c[i]) {
					if(i+1 < dp.length){
					result += dp[i+1][j - c[i]];
					}
				}else if(j == c[i]) {
					result += 1;
				}
				if(i+1 < dp.length){
					result += dp[i + 1][j];
				}
				dp[i][j] = result;
			}
		}
		return dp;
	}

	public static int p(int target, int[] c, int cur) {
		if (target == 0) {
			return 1;
		}
		if (cur == c.length) {
			return 0;
		}
		int result = 0;
		if (target - c[cur] > 0) {
			result += p(target - c[cur], c, cur + 1);
		} else if (target - c[cur] == 0) {
			result += 1;
		}
		result += p(target, c, cur + 1);
		return result;
	}

	

	public static void main(String[] args) {
		int[] arr1 = { 1, 2, 3 };
		int[] arr2 = { 1 };
		int target = 5;
		int result = calculateCombinations(arr1, arr2, target);
		System.out.println("result is " + result);
	}
}
