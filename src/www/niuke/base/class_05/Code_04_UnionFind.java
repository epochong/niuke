package www.niuke.class_05;
/*
* 6_1
* 并查集
* 功能
* 1.非常快的查两个元素是否属于一个集合 isSameSet
* 2.两个元素各自所在的集合合并为一个集合 union
* 临时的数据不行
* 开始要给好数据样本
*
* 假设给你五个数
* 1    2    3    4    5
* 每一个数是自己的集合，自己是这个集合的代表节点
* 1 2合并 2 -> 1 -> 1  1是这个集合的代表节点
* 3也合并
* 3 -> 1 -> 1
* 2 -> 1 -> 1
*
* 如果是一个集合，代表节点相同
*
* 4,5合并
* 5 -> 4 -> 4
*
* 3,5合并
* 少元素的挂在多元素的底下
* 3 -> 1 -> 1
* 2 -> 1 -> 1
* 5 -> 4 -> 1 -> 1
* 样本数量为N
* 如果查询次数 + 合并次数 为O(N)及以上，则单次查询或合并为O(1)
* */
import java.util.HashMap;
import java.util.List;
//import java.util.Stack;

public class Code_04_UnionFind {

	public static class Node {
		// whatever you like String,Int,Char,Float
	}

	public static class UnionFindSet {
		public HashMap<Node, Node> fatherMap;//某一个节点， 父节点  key：child  value：father一层一层找上去

		public HashMap<Node, Integer> sizeMap;//Node所在集合的节点个数,只保存代表节点的节点数就行了，
		// 记代表街点所在的集合节点个数才是有意义的，因为每次都是先找到代表节点

		public UnionFindSet() {//用户一次给你很多list，所有样本，这是必须的
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
		}

		public void makeSets(List<Node> nodes) {//初始化
			fatherMap.clear();
			sizeMap.clear();
			for (Node node : nodes) {
				fatherMap.put(node, node);//每一个node形成自己的集合，是自己的父
				sizeMap.put(node, 1);
			}
		}
		/*
		* 4 -> 3 -> 2 -> 1
		* node 是 4
		* father 是 3
		* */
		public Node findHead(Node node) {//找代表节点，递归行为
			Node father = fatherMap.get(node);
			if (father != node) {
				father = findHead(father);
			}
			fatherMap.put(node, father);//变扁平
			return father;
		}
		//非递归
/*		public Node findHead(Node node) {//找代表节点，递归行为
			Stack<Node> stack = new Stack<Node>();
			Node cur = node;
			Node parent = fatherMap.get(cur);
			while(cur != parent) {
				stack.push(cur);
				cur = parent;
				parent = fatherMap.get(cur);
			}
			while(!stack.isEmpty()) {
				fatherMap.put(stack.pop(),parent);
			}
			return parent;
		}*/

		public boolean isSameSet(Node a, Node b) {
			return findHead(a) == findHead(b);
		}

		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aHead = findHead(a);
			Node bHead = findHead(b);
			if (aHead != bHead) {
				int aSetSize= sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				if (aSetSize <= bSetSize) {
						fatherMap.put(aHead, bHead);
					sizeMap.put(bHead, aSetSize + bSetSize);
				} else {
					fatherMap.put(bHead, aHead);
					sizeMap.put(aHead, aSetSize + bSetSize);
			}				}

	}

	}

	public static void main(String[] args) {

	}

}
