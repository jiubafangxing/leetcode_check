public class QuestionRemovingStarsFromString{

	public static int max = 100001;
	public static char[] stack = new char[max];
	public static int right = 0;
	public String removeStars(String s) {
		if(null == s ||s.equals("")){
			return "";
		}
		right =0;
		char[] data = s.toCharArray();
		for(int i=0;i< data.length;i++){
			if(data[i] == '*' && right >0){
				right--;
				continue;	
			}
			stack[right++] = data[i];
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<right;i++){
			sb.append(stack[i]);
		}
		return sb.toString();
	}

}
