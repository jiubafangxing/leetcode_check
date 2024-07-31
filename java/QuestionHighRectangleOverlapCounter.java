import java.util.*;
/*
 *There are n rectangles in a plane. The lower left corner of the i-th rectangle is at coordinates (x1[i], y1[i]), and the upper right corner is at (x2[i], y2[i]). If two or more rectangles share a common area, they are considered to overlap with each other (not considering the boundaries and corners). Please calculate the place where the maximum number of rectangles overlap on the plane, and how many rectangles overlap there.

 */
public class QuestionHighRectangleOverlapCounter {

	public static class Rectangle{

		public 	int left;

		public int down;

		public int right;

		public int top;


		public Rectangle(int left, int down , int right, int top){
			this.left = left;
			this.down = down;	
			this.right = right;	
			this.top = top;	
		}

	

	}

	public static  class DownComparator  implements Comparator<Rectangle>{
		@Override
			public int compare(Rectangle r1, Rectangle r2){
				return r1.down-r2.down;	
			}	
	}

	public static class LeftComparator implements Comparator<Rectangle>{
		@Override
			public int compare(Rectangle r1, Rectangle r2){
				return r1.left -r2.left;	
			}	
	}
	public  static class RightComparator implements Comparator<Rectangle>{
		@Override
			public int compare(Rectangle r1, Rectangle r2){
				return r1.right-r2.right;	
			}	
	}


	public static int count(Rectangle[] rectangles){
		Arrays.sort(rectangles, new DownComparator());
		TreeSet<Rectangle> leftRectangles = new TreeSet<Rectangle>(new LeftComparator());
		int maxSize = 0;
		for(int i=0;i< rectangles.length; i++){
			Rectangle rectangle = rectangles[i];

			int index = i;
			int curDown = rectangle.down;
			while(index< rectangles.length && rectangles[index].down == curDown){
				leftRectangles.add(rectangles[index]);
				index++;
			}
			clearLessThanBottom(leftRectangles, rectangle.down);	

			i = index;

			TreeSet<Rectangle> rights = new TreeSet<Rectangle>(new RightComparator());
			for(Rectangle l :leftRectangles){
				removeLessThanLeft(rights, l.left);
				rights.add(l);
				maxSize = Math.max(maxSize, rights.size());	
			}

		}
		return maxSize;	
	}


	public static void removeLessThanLeft(TreeSet<Rectangle> t, int l){
		if(null == t || t.size() == 0){
			return;
		}
		List<Rectangle> removes = new ArrayList();
		for(Rectangle r:t){
			if(r.right <= l){
				removes.add(r);					
			}else{
				break;
			}	
		}
		for(Rectangle r:removes){
			t.remove(r);
		}
	}


	public static void clearLessThanBottom(TreeSet<Rectangle> t, int d ){
		if(null == t || t.size() == 0){
			return;
		}
		List<Rectangle> removes = new ArrayList();
		for(Rectangle r:t){
			if(r.top < d){
				removes.add(r);
			}
		}
		for(Rectangle r:removes){
			t.remove(r);
		}

	}
	public static void main(String[] args){
		Rectangle[] testCase5 = {
			 new Rectangle(1, 3, 2, 6),
            new Rectangle(1, 3,2, 6),
            new Rectangle(1, 3, 2, 6)
		};
		int result = count(testCase5);
		System.out.println("result is "+ result);


	}
}
