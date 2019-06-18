package www.niuke.class_04;
/*
*
* 折纸问题
*
* 1      1条折痕
* 2      3
* 3      7
* ...
* n      2^n -1
* 找规律
* 1             下
* 2       下          上
* 3    下     上    下    上
* 二叉树中序遍历
* */
public class Code_05_PaperFolding {

	public static void printAllFolds(int N) {
		printProcess(1, N, true);
	}
	/*
	*                 1T
	*            2T        2F
	*        3T     3F   3T   3F
	*     4T    4F
	* 假设一共三层 4 是空，利用了二叉树的中序遍历但没使用，通过递归实现
	* 递归路径： 1T -> 2T -> 3T -> 4T -> 3t -> 4f -> 2T -> 3F ---
	*
	* */
    //                                  当前层  一共有多少层  当前过程是上还是下 下 true  上 false
	public static void printProcess(int i, int N, boolean down) {
		if (i > N) {
			return;
		}
		printProcess(i + 1, N, true);
		System.out.println(down ? "down " : "up ");
		printProcess(i + 1, N, false);
	}

	public static void main(String[] args) {
		int N = 4;
		printAllFolds(N);
	}
}
