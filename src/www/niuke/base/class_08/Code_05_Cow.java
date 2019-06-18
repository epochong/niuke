package www.niuke.class_08;
/*
* 8_7
* 母牛每年生一只母牛，新生出的母牛成长三年后也能每年生一只母牛，假设不会死，求N年后，母牛的数量
* 1   2   3   4   5    6
* A   A   A   A   A    A
*     B   B   B   B    B
*         C   C   C    C
*             D   D(A) D
*                 F(B) F
*                      H
*                      I
* F(n) = F(n-1) + F(n-3)
* 今年    去年     3年前的牛都能生小牛
*
*
* */
public class Code_05_Cow {

	public static int cowNumber1(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		return cowNumber1(n - 1) + cowNumber1(n - 3);
	}

	public static int cowNumber2(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		int res = 3;
		int pre = 2;
		int prepre = 1;
		int tmp1 = 0;
		int tmp2 = 0;
		for (int i = 4; i <= n; i++) {
			tmp1 = res;
			tmp2 = pre;
			res = res + prepre;
			pre = tmp1;
			prepre = tmp2;
		}
		return res;
	}

	public static void main(String[] args) {
		int n = 20;
		System.out.println(cowNumber1(n));
		System.out.println(cowNumber2(n));
	}

}
