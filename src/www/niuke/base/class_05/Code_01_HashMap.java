package www.niuke.class_05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
/*
* 6_1哈希函数(散列函数)
* 1.输入域无穷大
* 2.输出域是又穷的(S域)
* 3.输入一样输出肯定一样
* 4.输入不一样，也可能输出一样
* 5.很多输入不一样的输入，输出是均匀分布的(哈希函数的离散性)
* 可以用来打乱输入规律，目的就是输入量很大时使输出均匀
* 推论：结果也是均匀分布
* 面试是不会让实现hash函数，code挺麻烦的很多的异或运算
*
* 通过一个hash变换不同hash函数
* 假如一个hash函数的结果是2^64范围每一位1-9，a-f共16位
* 高八位作为h1和低八位作为h2相互独立把一个hash函数做成两个，
* 如果考虑到S域会变小就使用多个hash函数生成器
* 查一下md5的code
*
* hash函数得到的码每一个位置和其他位置都是独立的
* h1 + 1 * h2 = h3
* h1 + 2 * h2 = h4
* 通过改变系数可以得到不同hash函数h3，h4与h1是不相关的
*
* hash表
* 经典结构：桶下面是链表
* JVM中：是红黑树，平行搜索二叉树，有多少个桶就有多少红黑树
* put(key1,value)
* get(key1)
* remove(key1)
* key1->code1(hash一下) % 17 = 0~16 ,假如算出来10 在10位置存key和value
* 如果得到重复的余数就在后面链一个点
* 如果key相同，就看value是否相等不相等修改新的
* 如果不同就在后面链
*
* hash表的扩容默认O(1)，数学上不是O(1)很做到
* 当每一个链达到一定长度(在向下加可能效率不行了)时开始扩容(重新计算)
* 可以离线扩容系统另外开辟扩容，用户用直接返回。
*
* 大数据题目用到hash函数做分流(大任务分成小任务)
* 有1T的大文件每一行有一个字符串，要求打印出重复的字符串
* 问面试官有几台机器，面试官说有1000台
* 问是否有从文件中方便取字符串的工具，面试官说有分布式的取字符串方式，你就说怎么处理数据
* 你说：可以将取出的字符串通过hash函数得到结果%1000得到0~999的数
* 将机器编好号0~999，得到什么值就向对应的机器发送，相同的字符串一
* 定在同一台机器上，同一台机器上可能有多种相同的字符串，每台机器可
* 以并行处理多个任务，以同样的方式获得相同的字符串
* */

public class Code_01_HashMap {

	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<>();
		map.put("zuo", "31");

		System.out.println(map.containsKey("zuo"));
		System.out.println(map.containsKey("chengyun"));
		System.out.println("=========================");

		System.out.println(map.get("zuo"));
		System.out.println(map.get("chengyun"));
		System.out.println("=========================");

		System.out.println(map.isEmpty());
		System.out.println(map.size());
		System.out.println("=========================");

		System.out.println(map.remove("zuo"));
		System.out.println(map.containsKey("zuo"));
		System.out.println(map.get("zuo"));
		System.out.println(map.isEmpty());
		System.out.println(map.size());
		System.out.println("=========================");

		map.put("zuo", "31");
		System.out.println(map.get("zuo"));
		map.put("zuo", "32");
		System.out.println(map.get("zuo"));
		System.out.println("=========================");

		map.put("zuo", "31");
		map.put("cheng", "32");
		map.put("yun", "33");

		for (String key : map.keySet()) {
			System.out.println(key);
		}
		System.out.println("=========================");

		for (String values : map.values()) {
			System.out.println(values);
		}
		System.out.println("=========================");

		map.clear();
		map.put("A", "1");
		map.put("B", "2");
		map.put("C", "3");
		map.put("D", "1");
		map.put("E", "2");
		map.put("F", "3");
		map.put("G", "1");
		map.put("H", "2");
		map.put("I", "3");
		for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + "," + value);
		}
		System.out.println("=========================");

		// you can not remove item in map when you use the iterator of map
//		 for(Entry<String,String> entry : map.entrySet()){
//			 if(!entry.getValue().equals("1")){
//				 map.remove(entry.getKey());
//			 }
//		 }

		// if you want to remove items, collect them first, then remove them by
		// this way.
		List<String> removeKeys = new ArrayList<String>();
		for (Entry<String, String> entry : map.entrySet()) {
			if (!entry.getValue().equals("1")) {
				removeKeys.add(entry.getKey());
			}
		}
		for (String removeKey : removeKeys) {
			map.remove(removeKey);
		}
		for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + "," + value);
		}
		System.out.println("=========================");

	}

}
