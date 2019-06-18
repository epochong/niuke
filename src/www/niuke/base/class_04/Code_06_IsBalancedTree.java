package www.niuke.class_04;
//初级5_5
/*
* 以每个节点为头的整棵树是不是平衡的
* 1.左树是否平衡
* 2.右树是否平衡
* 3.左右平衡后，左树的高数
* 4.左右平衡后，右树的高度
* */
public class Code_06_IsBalancedTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
    //add
    public static class ReturnData {
	    public boolean isB;//是否平衡
	    public int h;
	    public ReturnData(boolean isB, int h) {
	        this.isB = isB;
	        this.h = h;
        }
    }
    //add 递归过程
    /*
    *                     1
    *                 2      3
    *               n   4   n  5
    *                  n n    n n
    * 1递归2
    * 2递归左树得到 true 0
    * 2递归右树得到 true 1
    * 2返回1得到 true 2
    * */
    //列可能性
    public static ReturnData process(Node head) {
	    if(head == null) {
	        return new ReturnData(true, 0);
        }
        ReturnData leftData = process(head);//假设左树可以给我这样的类型
	    if(!leftData.isB) {//左树不平衡，不用后序判断了
	        return new ReturnData(false,0);
	    }
	    ReturnData rightData = process(head.right);
	    if(!rightData.isB) {
	        return new ReturnData(false,0);
	    }
	    if(Math.abs(leftData.h - rightData.h) > 1) {//说明左树也平衡右树也平衡
	        return  new ReturnData(false,0);
	    }
	    return new ReturnData(true,Math.max(leftData.h,rightData.h) + 1);
    }
	public static boolean isBalance(Node head) {
		boolean[] res = new boolean[1];
		res[0] = true;
		getHeight(head, 1, res);
		return res[0];
	}

	public static int getHeight(Node head, int level, boolean[] res) {
		if (head == null) {
			return level;
		}
		int lH = getHeight(head.left, level + 1, res);
		if (!res[0]) {
			return level;
		}
		int rH = getHeight(head.right, level + 1, res);
		if (!res[0]) {
			return level;
		}
		if (Math.abs(lH - rH) > 1) {
			res[0] = false;
		}
		return Math.max(lH, rH);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		System.out.println(isBalance(head));

	}

}
