/*
 *
CC 里面有一个土豪很喜欢一位女直播 kiki 唱歌，平时就经常给她点赞、送礼、私聊。最近 CC 直播平台在举行中秋之星直播唱歌比赛，假设一开始该女主播的初始人气值为 cur，能够晋升下一轮人气需要刚好达到 end，土豪给主播增加人气可以采取一下三种方法：

点赞；花费 x C币，人气 + 2
送礼；花费 y C 币，人气 * 2
私聊；花费 z C币，人气 - 2
其中 end 远大于 cur 且 end 为偶数，请写一个程序帮助土豪计算一下，最少花费多少 C 币就能帮助该主播 kiki 将人气刚好达到 end，从而能够晋级下一轮？
限制 0 < x , y , z < = 10000 ; 0 < s t a r t , e n d < 1000000 0<x,y,z<=10000 ;\quad 0<cur,end<10000000<x,y,z<=10000;0<cur,end<1000000
【例如】
输入：cur = 3，end = 100，x = 1，y = 2，z = 6
输出：6
 *
 */
public class QuestionLiveStreamPopularity{

	

	public static int minCcoins1(int add, int times, int del, int start, int end) {
        if (start > end) {
            return -1;
        }
        return process(0, start, end, add, times, del, ((end - start) / 2) * add);
    }

    /** start 人气向 end 改变
     * @param cost      之前已经花了多少钱【可变】
     * @param start     起始人气【可变】
     * @param end       目标人气 【固定】
     * @param add       点赞花费 C 币 【固定】
     * @param times     送礼花费 C 币 【固定】
     * @param del       私聊花费 C 币 【固定】
     * @param limitCoin 已经使用的币大到什么程度不需要再尝试了 【固定】
     * @return
     */
    public static int process(int cost, int start, int end, int add, int times, int del,
                              int limitCoin) {
        if (cost > limitCoin) {
            return Integer.MAX_VALUE;
        }
        if (start < 0) {
            return Integer.MAX_VALUE;
        }
        if (start > (2 * end)) {
            return Integer.MAX_VALUE;
        }
        if (start == end) {
            return cost;
        }

        int min = Integer.MAX_VALUE;
        //让人气-2的方式
        int p1 = process(cost + add, start + 2, end, add, times, del, limitCoin);
        if (p1 != Integer.MAX_VALUE) {
            min = p1;
        }
        //让人气+2的方式
        int p2 = process(cost + del, start - 2, end, add, times, del, limitCoin);
        if (p2 != Integer.MAX_VALUE) {
            min = Math.min(min, p2);
        }
        //让人气*2的方式
        int p3 = process(cost + times, start * 2, end, add, times, del, limitCoin);
        if (p3 != Integer.MAX_VALUE) {
            min = Math.min(min, p3);
        }
        return min;
    }

    public static void main(String[] args) {
        int add = 1;
        int times = 2;
        int del = 3;
        int start = 3;
        int end = 100;
        System.out.println(minCcoins1(add, times, del, start, end));
    }

}
