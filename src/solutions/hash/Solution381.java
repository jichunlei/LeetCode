package solutions.hash;

import pojo.RandomizedCollection;

/**
 * O(1) 时间插入、删除和获取随机元素 - 允许重复
 *
 * @author : xianzilei
 * @date : 2020/10/31 11:42
 */
public class Solution381 {
    public static void main(String[] args) {
        RandomizedCollection randomizedCollection = new RandomizedCollection();
        randomizedCollection.insert(4);
        randomizedCollection.insert(3);
        randomizedCollection.insert(4);
        randomizedCollection.insert(2);
        randomizedCollection.insert(4);
        randomizedCollection.remove(4);
        randomizedCollection.remove(3);
        randomizedCollection.remove(4);
        randomizedCollection.remove(4);
        System.out.println(randomizedCollection.getRandom());
    }
}
