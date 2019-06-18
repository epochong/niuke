package www.niuke.class_04;
//5_7
/*
* 已知一个完全二叉树，求其节点的个数
* 要求：时间复杂度低于O(N)，N为这棵树的节点个数
* 结论：一颗满二叉树高度为h，节点个数为2^h - 1
* 1.遍历左边界(O(logN))求出树的高度h
* 2.求右子树的左边界是否到h
*   a.到了，说明左子树是满二叉树，利用公式2^(h-1)-1+1 (+1是当前节点)
*   b.没到，说明右子树为为少一层的满二叉树，然后递归左子树
* 3.递归右子树
* 每一层遍历节点的个数只有一个O(logN)，遍历这个节点会遍历这个节点的左边界O(logN)
* O((logN)^2)
* */
public class Code_08_CompleteTreeNodeNumber {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int nodeNum(Node head) {//传进来一个完全二叉树，题意是这个
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}
    //node：当前节点 l：当前节点在第几层 h：完全二叉树的高度(固定值)
	public static int bs(Node node, int l, int h) {
		if (l == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, l + 1) == h) {//node的右子树的左边界到了哪一层
			return (1 << (h - l)) + bs(node.right, l + 1, h);//-1+1
		} else {
			return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
		}
		//返回值为以该节点为头的节点个数
	}

	public static int mostLeftLevel(Node node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		System.out.println(nodeNum(head));

	}

}
