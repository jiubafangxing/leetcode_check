package book_case;
import java.util.*;
public class  Code006{
	
	public static int process(int num){
		return process(num, "left","mid","right", "left","right");
	}

	public static int process(int level, String left, String mid, String right, String from ,String to){
		if(level == 1 ){
			if(from.equals(mid) || to .equals( mid)){
				System.out.println("move"+1+"from"+ from+ "to"+ to);
				return 1;
			}else{
				System.out.println("move"+1+"from"+ from+ "to"+ mid);
				System.out.println("move"+1+"from"+ mid+ "to"+to);
				return 2;
			}

		}else{

			if(from.equals(mid) || to .equals( mid)){
				String another = (from .equals(left)|| to.equals(left)) ? right : left;
				int part1 = process(level-1, left, mid, right, from , another);
				int part2 = 1;	
				System.out.println("move"+1+"from"+ from+ "to"+ to);
				int part3 = process(level-1, left, mid, right, another, to);
				return part1+ part2+part3;
			}else{
				int part1 = process(level-1, left, mid, right, from , to);
				System.out.println("move"+level+"from"+ from+"to"+ mid);
				int part2 = process(level-1, left, mid, right, to, from);
				System.out.println("move"+level+"from"+ mid+"to"+ to);
				int part3 = process(level-1, left, mid, right, from , to);
				return part1 +part2 +part3 +2;	
			
			}
		
		
		}


	}

	public static void main(String[] args){
		int result = process(2);	
		System.out.println("共需"+result +"步");
	}
}
