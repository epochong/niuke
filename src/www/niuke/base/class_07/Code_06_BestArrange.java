package www.niuke.class_07;
/*
* 8_3
* 贪心策略
* 方法：脑补自己的贪心策略用对数器验，不用纠结。就靠累积，见了多了就熟悉了
* 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目
* 的宣讲。 给你每一个项目开始的时间和结束的时间(给你一个数
* 组，里面 是一个个具体的项目)，你来安排宣讲的日程，要求会
* 议室进行 的宣讲的场次最多。返回这个最多的宣讲场次。
*
* 贪心策略
* 1. 哪个会最先开始就安排谁--不可行
*    如果一个项目最早开始，但是持续时间长，后面的项目都不能安排
* 2. 哪个项目持续时间段就安排谁--不可行
*     1--------------   3-----------------
*                 2--------
*     如果安排第二个，第一个和第三个都没法讲了
*  两种都得不到最优解
*  3.先看哪个项目早结束--可行
*  1-------
*       2-------
*     3-------
*                 4--------
*                   5----
*                     6----------
*  先2，在找出因为这个不能做的项目1  3
*  然后5  不能做的项目4 6
*  这样选是最多的
*
*
* */
import java.util.Arrays;
import java.util.Comparator;

public class Code_06_BestArrange {

	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}

	public static int bestArrange(Program[] programs, int start) {
		Arrays.sort(programs, new ProgramComparator());//谁结束早在数组前面
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			if (start <= programs[i].start) {//当前时刻，项目开始时间是大于等于当前时刻，这个项目可以安排
				result++;
				start = programs[i].end;//当前时刻变为这个项目结束的时刻
			}
		}
		return result;
	}

	public static void main(String[] args) {

	}

}
