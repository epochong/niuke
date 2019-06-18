package www.niuke.class_08;

public class Code_08_Money_Problem {

	public static boolean money1(int[] arr, int aim) {
		return process1(arr, 0, 0, aim);
	}

	public static boolean process1(int[] arr, int i, int sum, int aim) {
		if (sum == aim) {
			return true;
		}
		// sum != aim
		if (i == arr.length) {
			return false;
		}
		return process1(arr, i + 1, sum, aim) || process1(arr, i + 1, sum + arr[i], aim);
	}

	public static boolean money2(int[] arr, int aim) {
		boolean[][] dp = new boolean[arr.length + 1][aim + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i][aim] = true;
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = aim - 1; j >= 0; j--) {
				dp[i][j] = dp[i + 1][j];
				if (j + arr[i] <= aim) {
					dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
				}
			}
		}
		return dp[0][0];
	}

	//add
    /*
    * 给你一个数组arr，和一个整数aim。如果可以任意选择arr中的数字，能不能累加得到aim，返回true或者false
    * (和子序列一样)
    * 0  1  2  3
    * 3  2  7  13      数9
    * 要3不要3两种
    *                             f(0,0)
    *                   f(1,3)                   f(1,0)
    *       f(2,5)               f(2,3)          ...
    *  f(3,12)  f(3,5)     f(3,10)   f(3,3)
    * 如果你发现有一个累积为true 一路返回都是true
    *
    * 看是不是无后效性问题
    * 0  1  2
    * 3  2  5...
    * 要3 要2
    * 后序状态  f(3,5)
    * 需要的是f(3,5)这个状态
    * 它和没 要3 没要2 要5返回值状态一样
    * 所以是无后效性问题，和我怎么选择，怎么到达这个状态的没关系
    * 我不管你之前做的什么选择，只要是我从某个位置开始，之后的数自由选择，而且之前形成的累加和是固定的，这两个参数一点确定，返回值一定确定
    *
    * 确定：arr  aim
    * 可变：i位置形成的累加和sum
    * 这两个可变参数代表整个状态 二维表
    *
    * sum 最大 所有数加起来，返回值肯定不会出这个值，一定能装到这个表里(全正数)，sum是边界
    * 要的是什么状态，回到原函数中 0，0状态
    * 哪些位置可以确定
    * i = arr.length的时候(递归的时候可以碰到最后一个位置而不是N-1)，只要到了aim 的值后面的都是false
    * 再找普通位置(i,sum)需要什么状态，看子过程 看函数，下一个是i+1位置，需要下一排的值
    * 可以推出(i+1,sum)  (i+1,sum+arr[i]) 依次推 是true 是 false 直接返回
    * 有了倒数第一行可以退出倒数第二行，一次类推，推各个位置T还是F,看0,0是T还是F
    *
    *                          aim                          sum
    * 0,0   0
    *
    *
    *                  (i,sum)
    *                  (i+1,sum)         (i+1,aum+arr[i])
    *
    *
    *
    *
    *
    *
    *       N                   T   F  F  F  F  F  F  F...
    * */
    public static boolean isSum(int[] arr, int i, int sum, int aim) {
        if(i == arr.length) {
            return sum == aim;
        }
        return isSum(arr, i+1, sum, aim)||isSum(arr, i+1, sum + arr[i], aim);
    }
	public static void main(String[] args) {
		int[] arr = { 1, 4, 8 };
		int aim = 12;
		System.out.println(money1(arr, aim));
		System.out.println(money2(arr, aim));

		//isSum(arr,0,0,aim)
	}

}
