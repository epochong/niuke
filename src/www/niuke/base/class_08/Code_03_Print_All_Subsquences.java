package www.niuke.class_08;
/*
* 8_5 试的过程
* 打印字符串所有子序列(不是子串)，包括空
* a  b  c
* 0  1  2
* 0  连个决策  要 a 不要 a
* ...
* 穷尽所有情况
*
* */

import java.util.ArrayList;
import java.util.List;

public class Code_03_Print_All_Subsquences {

	public static void printAllSubsquence(String str) {
		char[] chs = str.toCharArray();
		process(chs, 0);
	}

	public static void process(char[] chs, int i) {
		if (i == chs.length) {
			System.out.println(String.valueOf(chs));
			return;
		}
		process(chs, i + 1);
		char tmp = chs[i];
		chs[i] = 0;
		process(chs, i + 1);
		chs[i] = tmp;
	}
	
	public static void function(String str) {
		char[] chs = str.toCharArray();
		process(chs, 0, new ArrayList<Character>());
	}
	
	public static void process(char[] chs, int i, List<Character> res) {
		if(i == chs.length) {
			printList(res);
		}
		List<Character> resKeep = copyList(res);
		resKeep.add(chs[i]);
		process(chs, i+1, resKeep);
		List<Character> resNoInclude = copyList(res);
		process(chs, i+1, resNoInclude);
	}
	
	public static void printList(List<Character> res) {
		// ...;
	}
	
	public static List<Character> copyList(List<Character> list){
		return null;
	}
	//                                               上级扔给我的，'',a,b,c几种
	public static void printAllSub(char[] str,int i, String res) {
	    if(i == str.length) {//base case  终止位置
            System.out.println(res);
            return;
        }
        printAllSub(str, i + 1,res);//不要字符 ''
	    printAllSub(str, i + 1,res + String.valueOf(str[i]));//要字符 a b c
    }

	public static void main(String[] args) {
		String test = "abc";
		//printAllSubsquence(test);
        printAllSub(test.toCharArray(),0,"");
	}

}
