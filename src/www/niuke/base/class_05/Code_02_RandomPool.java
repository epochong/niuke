package www.niuke.class_05;
/*
* 6_2
* 设计RandomPool结构
* 设计一种结构，在该结构中有如下三个功能：
* insert(key)：将某个key加入到该结构，做到不重复加入。
* delete(key)：将原本在结构中的某个key移除。 getRandom()：
* 等概率随机返回结构中的任何一个key。
* 要求: Insert、delete和getRandom方法的时间复杂度都是
*
* 准备两张hash表，一张做不到严格的等概率返回(离散的方式只能保证几乎是均匀的)
*               size
*    map1                   map2
* key    value          key         value
* A      0              0           A
* B      1              1           B
* ...
* z      25             25          z
* 因为有size(大小)，可以利用Math.Random()得到随机的返回0-25在map2中找对应的value
*
* remove操作
* 不进行别的操作，如果只删除在index上就会产生很多洞，在getRandom的时候就会很慢不会是O(1)
* 产生洞的时候用最后一个数据填补这个洞size--，有序只保证getRandom的时候有数
O(1)
* */
import java.util.HashMap;

public class Code_02_RandomPool {

	public static class Pool<K> {
		public HashMap<K, Integer> keyIndexMap;
		public HashMap<Integer, K> indexKeyMap;
		public int size;

		public Pool() {
			this.keyIndexMap = new HashMap<K, Integer>();
			this.indexKeyMap = new HashMap<Integer, K>();
			this.size = 0;
		}

		public void insert(K key) {
			if (!this.keyIndexMap.containsKey(key)) {
				this.keyIndexMap.put(key, this.size);
				this.indexKeyMap.put(this.size++, key);
			}
		}

		public void delete(K key) {
			if (this.keyIndexMap.containsKey(key)) {
				int deleteIndex = this.keyIndexMap.get(key);
				int lastIndex = --this.size;
				K lastKey = this.indexKeyMap.get(lastIndex);
				this.keyIndexMap.put(lastKey, deleteIndex);
				this.indexKeyMap.put(deleteIndex, lastKey);
				this.keyIndexMap.remove(key);
				this.indexKeyMap.remove(lastIndex);
			}
		}

		public K getRandom() {
			if (this.size == 0) {
				return null;
			}
			int randomIndex = (int) (Math.random() * this.size); // 0 ~ size -1
			return this.indexKeyMap.get(randomIndex);
		}

	}

	public static void main(String[] args) {
		Pool<String> pool = new Pool<String>();
		pool.insert("zuo");
		pool.insert("cheng");
		pool.insert("yun");
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());

	}

}
