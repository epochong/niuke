package www.niuke.class_08;
/*
* 祖师娘：第一个程序员 拜伦的女儿
* 图灵之前：明确知道算法我只是让计算机代替我而已
* 图灵：我不知到怎么算，但是我知道怎么试
* p  和 np  问题
* p：多项式时间，我知道怎么算，我让计算机帮我算
* np：我不知道怎么算，我知道怎么试
*
* 工程上，平时写代码：大部分都是p
* 在怎么试方面非常没有感觉，也就是平时写方法递归怎么写你不会 平时写代码怎么怎么尝试极为缺少
* 带来后果，你理解不了动态规划
*
* 动态规划就是优化暴力尝试
*
* 暴力递归是怎么到动态规划的，接下来尝试
* */
public class Code_01_Factorial {
    //递归版本
    /*
    * 分解为子问题
    * 这两个方法有联系
    * 尝试顺序如果定了n  n-1  ...  2  1
    * 将计算顺序逆过来就是p过程
    * */
	public static long getFactorial1(int n) {
		if (n == 1) {
			return 1L;
		}
		return (long) n * getFactorial1(n - 1);
	}
//求n的阶乘 我知道怎么算，计算机给我算
	public static long getFactorial2(int n) {
		long result = 1L;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	public static void main(String[] args) {
		int n = 5;
		System.out.println(getFactorial1(n));
		System.out.println(getFactorial2(n));
	}

}
