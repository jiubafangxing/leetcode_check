public class QuestionMaximumNumberOfRobotsWithinBudget{
	public static int max = 10001;
	public static int[] data = new int[max];
	public static int l ;
	public static int r ;
	public static void addHeap(int i, int v){
	
	}
	
	public static int getMax(){
			
	}
	public static int getCurPos(){
	
	}
	public static void heapify(int pos){
		int parentPos = pos;
		while(parentPos*2+1 < r){
			int left = parentPos*2+1;	
			int right = parentPos*2+2;	
			int mostBig = left;
			if(right < r){
				mostBig = a[data[left]] > a[data[right]] ? left: right;	
			}
			if(a[data[mostBig]] > a[parentPos]){
				swap(data, mostBig, parentPos)	;
			}

		}
	}

	public static void heapInsert(int i){

	}
	public static void swap(int[] data, int mostBig, int parentPos){
		int tmp = data[mostBig];	
		data[mostBig] = data[parentPos];
		data[parentPos] = tmp;

	}
	public static int process(int[] a, int [] b, int v){

	}	
}
