/***
 *某公司招聘，有n个人入围，HR在黑板上依次写下m个正整数A1、A2、……、Am，然后让这n个人围成一个 圈，并按照顺时针顺序为他们编号0、1、2、……、n-1。录取规则是：
第一轮从0号的人开始，取用黑板上的第1个数字，也就是A1黑板上的数字按次序循环取用，即如果某轮用了第m个，则下一轮需要用第1个；如果某轮用到第k个，则下轮需要用第k+1个（k<m）
每一轮按照黑板上的次序取用到一个数字Ax，淘汰掉从当前轮到的人开始按照顺时针顺序数到的第Ax个人，下一轮开始时轮到的人即为被淘汰掉的人的顺时针顺序下一个人 被淘汰的人直接回家，所以不会被后续轮次计数时数到经过n-1轮后，剩下的最后1人被录取所以最后被录取的人的编号与（n，m，A1，A2，……，Am）相关。
输入描述：
第一行是一个正整数N，表示有N组参数从第二行开始，每行有若干个正整数，依次存放n、m、A1、……、Am，一共有N行，也就是上面的N组参数。
 *
 */
public class QuestionHighJosephusProblem2{
	public static int getNo(int i , int[] arr, int index){
		if(i == 1){
			return 0;
		}
		return (getNo(i-1,arr, getNextM(arr.length,index)) + arr[index])%i+1;
	}

	public static int getNextM(int size, int index){
		return (index+1 )% size  == 0 ? 0: index+1;
	}


	public static void main(String[] args){
		int[] arr = {3,1};
		int i = 4;	
		int result = getNo(4,arr, 0);
		System.out.println("result is "+ result);
	}




} 
