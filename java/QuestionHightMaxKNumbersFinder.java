/*
   给定两个一维int数组A和B. 其中：A是长度为m、元素从小到大排好序的有序数组。B是长度为n、元素从小到大排好序的有序数组。希望从A和B数组中，找出最大的k个数字，要求：使用尽量少的比较次数。
   */
public class QuestionHightMaxKNumbersFinder{
	public static int findMaxKNumber(int[] A , int []B, int k){
		int al = A.length;
		int bl = B.length;
		int[] sarr = null;
		int[] larr = null;
		int sl = 0;
		int ll = 0;
		if(al > bl){
			sarr = B;
			sl = B.length;
			larr = A;
			ll = A.length;
		}else{
			sarr = A;
			sl = A.length;
			larr =  B;
			ll = B.length;
		}
		if(k <= sl ){
			return getUpMedian(A, B, 0,k-1, 0, k-1);	
		}else
			if(k > ll){
				if(sarr[k-ll-1] >= larr[ll-1]){
					return sarr[k-ll-1];	
				}
				if(larr[k-sl-1] >= sarr[sl-1]){
					return larr[k-sl-1];
				}
				return getUpMedian(sarr,larr, k-ll, sl-1,k-sl,ll-1);
			}
			else{
				if(larr[k-sl-1] > sarr[sl-1]){
					return larr[k-sl-1];
				}
				return getUpMedian(sarr, larr, 0, sl-1,k-sl,k-1);	
			}

	}

	public static int getUpMedian(int[] A, int[]B , int s1, int e1, int s2, int e2){
		int mid1 = 0;
		int mid2 = 0;
		int offset = 0;
		while(s1 < e1){
			mid1 = (s1+e1)/2;
			mid2 = (s2+e2)/2;
			offset = ((e1-s1+1)&1)^1;
			if(A[mid1] > B[mid2]){
				e1 = mid1;
				s2 = mid2+ offset;
			}else if (A[mid1] < B[mid2]){
				s1 = mid1+ offset;
				e2 = mid2;
			}else{
				return A[mid1];
			}

		}
		return Math.min(A[s1], B[s2]);

	}


	public static void main(String[] args){
		int[] A  = {1,3,5,7};
		int[] B = {2,4,6,8};	
		int result = findMaxKNumber(A, B, 3);
		System.out.println(result);
	}

} 
