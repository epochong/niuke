package www.niuke.class_08;
/*
* 8_4
* 汉诺塔问题
* 小  中  大 杆
* n为左侧层数
* 从最左移到最右，打印步骤
* 移动过程中只能小压大
*
* 从from上移到to上 另外一个为help
* 1.将1 ~ n-1 从from移到help上
* 2.将单独的n 从from移到to上
* 3.1~n-1    从help移到to上
* 于是一些code了
*
* 时间复杂度 T(n)  =  T(n) + 1 + T(n)
* T(n) ~ O(2^n)
* */
public class Code_02_Hanoi {

	public static void hanoi(int n) {
		if (n > 0) {
			func(n, n, "left", "mid", "right");
		}
	}

	public static void func(int rest, int down, String from, String help, String to) {
		if (rest == 1) {
			System.out.println("move " + down + " from " + from + " to " + to);
		} else {
			func(rest - 1, down - 1, from, to, help);
			func(1, down, from, help, to);
			func(rest - 1, down - 1, help, from, to);
		}
	}
	//N:1 ~ N
	public static void process(int N,String from,String to, String help) {
	    if(N == 1) {//直接从from 到 to
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
	        process(N-1,from,help,to);//N-1从from到help 借助 to
            System.out.println("Move " + N + " form " + from + " to " + to);
            process(N-1,help,to,from);//N—1在help上 借助from
        }
    }

	public static void moveLeftToRight(int N) {
		if (N == 1) {
			System.out.println("move 1 from left to right");
		}else {
            moveLeftToMid(N - 1);
            System.out.println("move " + N + "from left to right");
            moveMidToRight(N - 1);
        }

	}

	public static void moveRightToLeft(int N) {

	}

	public static void moveLeftToMid(int N) {
		if (N == 1) {
			System.out.println("move 1 from left to mid");
		}
		moveLeftToRight(N - 1);
		System.out.println("move " + N + "from left to mid");
		moveRightToMid(N - 1);
	}

	public static void moveMidToLeft(int N) {

	}

	public static void moveRightToMid(int N) {

	}

	public static void moveMidToRight(int N) {
		if (N == 1) {
			System.out.println("move 1 from mid to right");
		}
		moveMidToLeft(N - 1);
		System.out.println("move " + N + "from mid to right");
		moveLeftToRight(N - 1);
	}

	public static void main(String[] args) {
		int n = 3;
		hanoi(n);

		process(3,"左","右","中");
	}

}
