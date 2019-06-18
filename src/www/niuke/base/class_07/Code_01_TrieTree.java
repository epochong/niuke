package www.niuke.class_07;
/*
* 7_5
* 前缀树
* 每次加字符串的时候从头结点开始加，每次及字符加在路径上
* bef  和  be 路径一致
* 查be是否加过
* 每个节点加一个数据，以这个结点结尾的路径的个数end
* 时候加过be，e的结尾一定大于0
*
* 有多少个字符串前缀为bc
* 再加一个数据项，这个结点在加的过程中经过几次
*
* */
public class Code_01_TrieTree {

	public static class TrieNode {
		public int path;
		public int end;
		public TrieNode[] nexts;

		public TrieNode() {
			path = 0;
			end = 0;
			nexts = new TrieNode[26];//每个结点有26条路向下走a-z 可以根据题意
		}
	}

	public static class Trie {
		public TrieNode root;//头

		public Trie() {
			root = new TrieNode();
		}

		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';//字符距离a的大小
				if (node.nexts[index] == null) {//如果没路建出来
					node.nexts[index] = new TrieNode();
				}
				node = node.nexts[index];
				node.path++;
			}
			node.end++;//最后一个节点 path++  end++  整个路上的结点end没变
		}

		public void delete(String word) {
			if (search(word) != 0) {//存在才考虑删除问题
				char[] chs = word.toCharArray();
				TrieNode node = root;
				int index = 0;
				for (int i = 0; i < chs.length; i++) {
					index = chs[i] - 'a';
					if (--node.nexts[index].path == 0) {//path为0 底下的结点直接不用管直接释放掉，jvm中不用管 C++ 要依次经过每个节点，依次析构掉
						node.nexts[index] = null;
						return;
					}
					node = node.nexts[index];
				}
				node.end--;
			}
		}

		public int search(String word) {//word 一共插入多少次
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.end;//曾经有多少结点以它结尾
		}

		public int prefixNumber(String pre) {//查每个字符串前缀有多少
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.path;
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		trie.insert("zuo");
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuoa");
		trie.insert("zuoac");
		trie.insert("zuoab");
		trie.insert("zuoad");
		trie.delete("zuoa");
		System.out.println(trie.search("zuoa"));
		System.out.println(trie.prefixNumber("zuo"));

	}

}
