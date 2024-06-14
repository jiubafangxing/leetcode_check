/*
 * 为了保证招聘信息的质量问题，公司为每个职位设计了打分系统，打分可以为正数，也可以为负数，正数表示用户认可帖子质量，负数表示用户不认可帖子质量。
 * 打分的分数根据评价用户的等级大小不定，比如可以打1分，10分，30分，-10分等。
 * 假设数组A记录了一条帖子所有的打分记录，现在需要找出帖子曾经得到过最高的分数是多少，用于后续根据最高分数来确认需要对发帖用户做相应的惩罚或奖励。
 * 其中，最高分的定义为：用户所有打分记录中，连续打分数据之和的最大值即认为是帖子曾经获得的最高分。
 * 例如：帖子10001010近期打分记录为：[1,1,-1,-10,11,4,-6,9,20,-2]，那么该条帖子曾经达到过的最高分数为11+4+(-6)+9+20=38。
 * 请实现一段代码，输入为帖子近期的打分记录，输出为当前帖子得到的最高分数。
 * 
 * To ensure the quality of recruitment information, the company has designed a scoring system for each position.
 * Scores can be positive or negative, with positive numbers indicating user recognition of the post quality, and negative numbers indicating non-recognition.
 * The score can vary according to the level of the evaluating user, such as 1 point, 10 points, 30 points, -10 points, etc.
 * Suppose array A records all the scoring records of a post.
 * Now it is necessary to find out the highest score the post has ever received, which will be used to determine the corresponding punishment or reward for the poster based on the highest score.
 * The definition of the highest score is: the maximum sum of continuous scoring data among all user scoring records is considered the highest score the post has ever received.
 * For example, the recent scoring record of post 10001010 is: [1, 1, -1, -10, 11, 4, -6, 9, 20, -2],
 * then the highest score the post has ever received is 11 + 4 + (-6) + 9 + 20 = 38.
 * Please implement a piece of code, with the input being the recent scoring record of the post,
 * and the output being the highest score the current post has received.
 */
public class QuestionPostScoreAnalyzer{
	public static Integer maxScore(Integer[] scoreArray){
		Integer max= 0;
		Integer cur = 0;
		for(Integer score : scoreArray){
			cur+=score;
			if(cur < 0){
				cur = 0;
			}
			if(cur > max){
				max = cur;
			}	
		}
		return max;	
	}

	public static void main(String[] args){
		Integer[] arr = {1, 1, -1, -10, 11, 4, -6, 9, 20, -2}	;
		Integer result = maxScore(arr);
		System.out.println(result);
	}
} 
