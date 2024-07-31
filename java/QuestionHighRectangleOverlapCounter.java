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
		int result = -1;
		Arrays.sort(rectangles, new DownComparator());
		TreeSet<Rectangle> leftRectanles = new TreeSet<Rectangle>();
		for(Rectangle rectangle:rectangles){
			clearLessThanBottom(leftRectangles, rectangle.down);	
		}
		return result ;	
	}


	public static void clearLessThanBottom(TreeSet<Rectangle> t, int d ){
		List<Rectangle> removes = new ArrayList();
		for(Rectangle r:t){
			if(r.top >= d){
				break;
			}else{
					
			}	
		}	
	
	}
	public static void main(String[] args){
	
	
	}
}
