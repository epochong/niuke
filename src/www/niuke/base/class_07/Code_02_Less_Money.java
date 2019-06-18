package www.niuke.class_07;
/*
*
* 哈夫曼编码问题  贪心算法问题
* 用哈夫曼编码贪出来
* 一致性：总共的代价是由子代价累加，或者累乘，他可能给你一个公式，者都有可能用哈夫曼编码贪出来
* 此题的哈夫曼编码：所谓的哈夫曼编码，是让你分成很多部分，但是每次分的过程中，代价是求和的。(有的问题不仅仅是求和的)
* 所以考虑用哈夫曼编码贪
*
* 问题：一块金条切成两半，是需要花费和长度数值一样的铜板的。比如
* 长度为20的 金条，不管切成长度多大的两半，都要花费20个铜
* 板。一群人想整分整块金 条，怎么分最省铜板？
* 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为
* 10+20+30=60. 金条要分成10,20,30三个部分。 如果， 先把长
* 度60的金条分成10和50，花费60 再把长度50的金条分成20和30，
* 花费50 一共花费110铜板。
* 但是如果， 先把长度60的金条分成30和30，花费60 再把长度30
* 金条分成10和20，花费30 一共花费90铜板。
* 输入一个数组，返回分割的最小代价。
*
* 堆是啥(用来做什么)：在一群数据中拿出满足条件最好的那个东西
* 你通过定义比较器可以实现不同的堆
* 比较器是什么，就是你的标准，就是贪心的标准
* 贪心是一个明确的指标(这里数最小是这个标准)
* 所有堆结构往往解决贪心问题的问题
*
* 1  2  6  4  3  7  1  8  2
* 把所有数放到小根堆里面，每一次从小根堆拿出两个最小的，拿出两个1，产生2 再放到小根堆中依次往复
* 所有代价，就是合并产的所有代价加起来 2  4  7  10  14  18
*
* 10   20   30
* 10 20拼出一个非叶结点，然后往复，拼成一个树
* 先找两个最小的从底向上拼成树 ，从上到下分割，就是代价最小的
*                   60
*                30     30
*             10    20
*
*
* */
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code_02_Less_Money {

	public static int lessMoney(int[] arr) {
		PriorityQueue<Integer> pQ = new PriorityQueue<>();//就是堆，系统实现的堆
		for (int i = 0; i < arr.length; i++) {
			pQ.add(arr[i]);//所有数进队列
		}
		int sum = 0;
		int cur = 0;
		while (pQ.size() > 1) {//知道堆的大小为1
			cur = pQ.poll() + pQ.poll();//一次拿两个
			sum += cur;//代价，累加起来
			pQ.add(cur);//新生成的数进堆
		}
		return sum;
	}

	public static class MinheapComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2; // < 0  o1 < o2  负数
		}

	}

	public static class MaxheapComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1; // <   o2 < o1
		}

	}

	public static void main(String[] args) {
		// solution
		int[] arr = { 6, 7, 8, 9 };
		System.out.println(lessMoney(arr));

		int[] arrForHeap = { 3, 5, 2, 7, 0, 1, 6, 4 };

		// min heap
		PriorityQueue<Integer> minQ1 = new PriorityQueue<>();
		for (int i = 0; i < arrForHeap.length; i++) {
			minQ1.add(arrForHeap[i]);
		}
		while (!minQ1.isEmpty()) {
			System.out.print(minQ1.poll() + " ");
		}
		System.out.println();

		// min heap use Comparator
		PriorityQueue<Integer> minQ2 = new PriorityQueue<>(new MinheapComparator());
		for (int i = 0; i < arrForHeap.length; i++) {
			minQ2.add(arrForHeap[i]);
		}
		while (!minQ2.isEmpty()) {
			System.out.print(minQ2.poll() + " ");
		}
		System.out.println();

		// max heap use Comparator
		PriorityQueue<Integer> maxQ = new PriorityQueue<>(new MaxheapComparator());
		for (int i = 0; i < arrForHeap.length; i++) {
			maxQ.add(arrForHeap[i]);
		}
		while (!maxQ.isEmpty()) {
			System.out.print(maxQ.poll() + " ");
		}

	}

}
