package www.niuke.class_07;
/*
* 7_6
* 最低字典序
* abc b 比较 等效为 abc  baa 比较 baa 大
* 贪心算法
* 定义一个一个指标，在这个指标下把每一个样本分出一个优先来。按照优先大的先执行，优先小的后执行
* 贪心就是一个简洁的标准，在这个标准下给所有东西分个1 2 3 出来，然后排的顺序，或者我定的优先级决定的顺序
*
* str1+str2 <= str2+str1 ,str1放在前面，否则str2放在前面  这个策略才是行之有效的
* 如 ba  和  b  的连接
* 定义一个比较器Mycompartor，按照这个规则排序之后连接
*
* 有没有排序策略不成立的
* 在比较器中规定 要证明你的比较器要有传递性
* 传递性： a+b <= b+a
* 		  b+c <= c+b   能推出a+c <= c+a  这叫具有传递性
* 证明
*         a*m(b)+b<=b*m(a)+a  ->  a*m(b)*c<=(b*m(a)+a-b)*c
*         b*m(c)+c<=c*m(b)+b  ->  (b*m(c)+c-b)*a<=c*m(b)*a
*    化简  m(c)*a-a<=m(a)*c
*    	   m(c)*a+c<=m(a)*c+a
*要证明这个顺序不能变，期间如果任意两条字符串交换位置次序就变了
* a m1 m2 ... mk b
* m1 a m2 ... mk b
* ...
* m1 m2 ... mk a b
* m1 m2 ... mk b a
* m1 m2 ... b mk a
* ...
* b m1 m2 ... mk a
* 证明一直增大，这就是贪心策略的证明，巨麻烦
* 有计数器解决这个问题，不要在意细节，用一个绝对正确的的策略和它比对就行了
*
* */
import java.util.Arrays;
import java.util.Comparator;

public class Code_05_LowestLexicography {

	public static class MyComparator implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
			return (a + b).compareTo(b + a);
		}
	}

	public static String lowestString(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		// �����µıȽϷ�ʽ����
		Arrays.sort(strs, new MyComparator());
		String res = "";
		for (int i = 0; i < strs.length; i++) {
			res += strs[i];
		}
		return res;
	}

	public static void main(String[] args) {
		String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
		System.out.println(lowestString(strs1));

		String[] strs2 = { "ba", "b" };
		System.out.println(lowestString(strs2));

	}

}
