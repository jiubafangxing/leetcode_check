public class QuestionStringToKth{

	public static int g(int i, int l){
		if(l == 1){
			return 1;
		}
		if(l < 1){
			return 0;
		}
		int result = 0;	
		for(int j=0;j<26;j++){
			int n = g(j,l-1);
			result +=n;
		}
		return result;
	}


	public static int t(int i){
		if(i<1){
			return 0;
		}
		if(i==1){
			return 26;
		}
		Integer result = 0;
		for(int j =0;j< 26;j++){
			result += g(j,i-1);	
		}	
		return result;
	}


	public static int stringToKth(String str){
		char[] arr = str.toCharArray();
		if(arr.length == 1){
			return 1;
		}
		Integer result = 0;
		for(int i=1;i< arr.length;i++){
			result +=	t(i);
			
		}
		Integer firstChar = arr[0]-'a';
		for(int i=0; i< firstChar;i++){
			result +=g(i, arr.length);	
		}
		Integer pre = arr[0]-'a';
		for(int t=1; t< arr.length;t++){
			int firstIdx = arr[t] - 'a';
			for(int i =pre;i< firstIdx;i++){
				result +=g(i, arr.length-t);
			}
			pre = arr[t] - 'a';
		}

		return result;
	}

	public static void main(String[] args){
		String str = "ad";	
		int result = stringToKth(str);
		System.out.println(result);
	}


}
