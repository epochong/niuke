package www.niuke.class_07;
/*
* 8_2
*
* 输入： 参数1，正数数组costs 参数2，正数数组profits 参数3，
* 正数k 参数4，正数m
* costs[i]表示i号项目的花费 profits[i]表示i号项目在扣除花
* 费之后还能挣到的钱(利润) k表示你不能并行、只能串行的最多
* 做k个项目 m表示你初始的资金
* 说明：你每做完一个项目，马上获得的收益，可以支持你去做下
* 一个 项目。
* 输出： 你最后获得的最大钱数。
*
* 准备一个小根堆
* 在cost中谁的花费低扔到头部
* 在小根堆中依次弹出头部，比初始资金低的全弹出来(可以选择的项目)，放到大根堆中
* 大根堆是按照受益高组成的大根堆
* 大根堆中选头部一个做项目，初始资金增加了
* 重复这个步骤，解锁新的项目在放到大根堆中
* 一直做k个结束
*
* cost    5  10  6  20
* profit  3  2   4  9
* w = 7
* 小根堆弹出  5  6
* 大根堆放着  5  3
*            6  4
* 先做 6  4
* w = 11
* 大根堆   5  3
*         10 2
* 先做 5  3
* w  =14
* 20 不能做
* 只能做大根堆中的
* 10  2
* w = 16
* 还是做不了20的项目，停
* */
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code_03_IPO {
	public static class Node {//每一个项目就是一个点
		public int p;
		public int c;

		public Node(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	public static class MinCostComparator implements Comparator<Node> {
    //每个项目谁花费低放在上面
		@Override
		public int compare(Node o1, Node o2) {
			return o1.c - o2.c;
		}

	}

	public static class MaxProfitComparator implements Comparator<Node> {
    //受益高放在上面
		@Override
		public int compare(Node o1, Node o2) {
			return o2.p - o1.p;
		}

	}
//                                                       利润数组        花费数组
	public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
		Node[] nodes = new Node[Profits.length];//放所有项目 收益  花费
		for (int i = 0; i < Profits.length; i++) {
			nodes[i] = new Node(Profits[i], Capital[i]);//所有项目进数组
		}
        //                 小根堆                          比较器
		PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
		//                  大根堆
		PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
		for (int i = 0; i < nodes.length; i++) {
			minCostQ.add(nodes[i]);//
		}
		for (int i = 0; i < k; i++) {//依次做项目
		    //                            小根堆被解锁的项目
			while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
				maxProfitQ.add(minCostQ.poll());//进到大根堆中
			}
			//
			if (maxProfitQ.isEmpty()) {//很可能做不到k个项目就得停
				return W;
			}
			W += maxProfitQ.poll().p;//做掉了一个项目
		}
		return W;
	}

}
