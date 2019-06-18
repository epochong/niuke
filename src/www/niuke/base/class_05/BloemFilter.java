package www.niuke.class_05;
/*
* 6_3
* 布隆过滤器
* 100亿个url加入黑名单
* bit类型数组(因为要存100亿个)0 ~ m-1
* 每一个url经过k个hash函数算到的对应值在数组中抹黑、就进入布隆过滤器
* 给你一个url
* 如果经过k个hash函数算到的值%m对应的数组中的点都被抹黑了，说明这个url是黑名单的
* 如果有一个没有抹黑，说明就不是
* m要开多大(按照以下公式)
* m = - (n*lnp)/(ln2)^2)(bit)
* n: 样本量 (100亿)
* p：预期失误率 (0.000001)
* m = 16G
* k:hash函数的个数
* k = ln2 * m/n
* 如果k和m都向上取整(这样可以降低失误率)，真正的失误率是(1-e^((n*k)/m))^k
* 公式怎么推的自己查帖子
* */
public class BloemFilter {
    public static void main(String[] args) {
        int[] arr = new int[1000];//32000
        int index = 30000;//30000这个位置抹黑
        int intIndex = index / 32;//30000定位到哪个桶
        int bitIndex = index % 32;//该桶的第几个位置
        arr[intIndex] = (arr[intIndex] | (1 << bitIndex));
    }
}
