package www.niuke.class_08;
/*
* 8_7
* 给你一个二维数组，二维数组中的每个数都是正数，要求从左上角走到右下角，
* 每一步只能向右或者向下。沿途经过的数字要累加起来。返回最小的路劲和
*
* 汉诺塔不能改动态规划
*
* 先写暴力递归，在改动态规划
*
* 3  2  1  0
* 7  5  0  1
* 3  7  6  2
*
* 这条路：9 = 3  2  1  0   1
*
* 暴力递归改动态规划
* 统一套路：
* 1.写出暴力尝试版本(最重要)
* 2.分析可变参数(i,j)，哪几个可变参数可以代表返回值状态，可变参数是几维的，他就是一张几维表(二维)
* 3.看最终的状态是哪一个，在这个表中点出来
* 4.回到base case 中，把你完全不依赖的位置的值设置好，
* 5.一个普遍位置看看它需要哪些位置，那么逆着回去就是我填表的顺序
* 暴力递归就是这么背改为动态规划，不用强记转移方程，搞不萌白
* 转移方程是递归来的，动态规划就是空间换时间
*
* */
public class Code_07_MinPath {

	public static int minPath1(int[][] matrix) {
		return process1(matrix, matrix.length - 1, matrix[0].length - 1);
	}
    //从(i,j)出发，到大最右下角位置，返回最小路径和
	public static int process1(int[][] matrix, int i, int j) {
		int res = matrix[i][j];
		if (i == 0 && j == 0) {
			return res;
		}
		if (i == 0 && j != 0) {
			return res + process1(matrix, i, j - 1);
		}
		if (i != 0 && j == 0) {
			return res + process1(matrix, i - 1, j);
		}
		return res + Math.min(process1(matrix, i, j - 1), process1(matrix, i - 1, j));
	}

	public static int minPath2(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row][col];
		dp[0][0] = m[0][0];
		for (int i = 1; i < row; i++) {
			dp[i][0] = dp[i - 1][0] + m[i][0];
		}
		for (int j = 1; j < col; j++) {
			dp[0][j] = dp[0][j - 1] + m[0][j];
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
			}
		}
		return dp[row - 1][col - 1];
	}

	// for test
	public static int[][] generateRandomMatrix(int rowSize, int colSize) {
		if (rowSize < 0 || colSize < 0) {
			return null;
		}
		int[][] result = new int[rowSize][colSize];
		for (int i = 0; i != result.length; i++) {
			for (int j = 0; j != result[0].length; j++) {
				result[i][j] = (int) (Math.random() * 10);
			}
		}
		return result;
	}
    //add
    /*
    * 行 列对应的二维表可以装下返回值
    * dp表
    * 最右下角可以直接对应(base case 位置)
    *
    * */
    public static int walk(int[][] matrix,int i,int j) {//可变参数固定返回值固定，无后效性问题，一定可以改成动态规划
	    if(i == matrix.length - 1 && j == matrix[0].length - 1) {
	        return matrix[i][j];
        }
        if(i == matrix.length - 1 ) {
	        return matrix[i][j] + walk(matrix,i,j+1);
        }
        if(j == matrix.length - 1) {
	        return matrix[i][j] + walk(matrix,i + 1,j);
        }
        int right = walk(matrix,i,j + 1);//右边位置到右下角最短路径和
	    int down = walk(matrix,i + 1,j);//下边位置到右下角最短路径和
	    return matrix[i][j] + Math.min(right,down);
    }
	public static void main(String[] args) {
		int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
		System.out.println(minPath1(m));
		System.out.println(minPath2(m));

		m = generateRandomMatrix(6, 7);
		System.out.println(minPath1(m));
		System.out.println(minPath2(m));
       // System.out.println(walk(m,0,0));
	}
}
