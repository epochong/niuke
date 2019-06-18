package www.niuke.class_04;
//5_6
/*
 * 完全二叉树
 * 1.一个节点有右孩子没有左孩子一定不是完全二叉树
 * 2.不是左右双全
 *   a.左有 右没有
 *   b.左右都没有
 *   以后遇到的必须是叶子节点
 * */
//初级5-6
/*
* 搜索二叉树: 中序遍历二叉树为升序的，以每个节点为根的左子树都比他小，右子树都比他大
* */
import java.util.LinkedList;
import java.util.Queue;

public class Code_07_IsBSTAndCBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		boolean res = true;
		Node pre = null;
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
				}
			}
			if (pre != null && pre.value > cur1.value) {
				res = false;
			}
			pre = cur1;
			cur1 = cur1.right;
		}
		return res;
	}

	public static boolean isCBT(Node head) {
		if (head == null) {
			return true;
		}
		/*
		* DQ：两端既可以进也可以出
		* Q ：尾进头出
		* 都可以用双端链表实现
		* */
		Queue<Node> queue = new LinkedList<Node>();//Java后台的双端链表可以实现队列结构
		boolean leaf = false;//是否开启了叶子节点
		Node l = null;
		Node r = null;
		queue.offer(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			//开启了叶子节点阶段如果左右孩子有一个不为空返回false 或满足第一种情况左空右不空返回false
			if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
				return false;
			}
           /*//和下面一样
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            }
            if(l == null || r == null) {
                leaf = true;
            }
            */
			if (l != null) {//如果不是叶子节点进队列
				queue.offer(l);
			}
			if (r != null) {
				queue.offer(r);
			} else {//右孩子不为空在上面已经有左空右不空的情况那时候已经返回，所以不用判断
				leaf = true;//当第一次右为空的时候(潜台词：左右都为空)叶子节点阶段开启
			}
		}
		return true;
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);

		printTree(head);
		System.out.println(isBST(head));
		System.out.println(isCBT(head));

	}
}