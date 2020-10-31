package pojo;

import java.util.*;

/**
 * 随机集合
 *
 * @author : xianzilei
 * @date : 2020/10/31 11:15
 */
public class RandomizedCollection {
    /**
     * 元素集合
     */
    private List<Integer> elements;
    /**
     * 每个元素对应的索引集合
     */
    private HashMap<Integer, Set<Integer>> elementIndexList;

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        elements = new ArrayList<>();
        elementIndexList = new HashMap<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain
     * the specified element.
     */
    public boolean insert(int val) {
        //添加元素到元素集合中
        elements.add(val);
        //更新元素索引集合
        if (elementIndexList.containsKey(val)) {
            Set<Integer> indexSet = elementIndexList.get(val);
            indexSet.add(elements.size() - 1);
            return false;
        } else {
            Set<Integer> indexSet = new HashSet<>();
            indexSet.add(elements.size() - 1);
            elementIndexList.put(val, indexSet);
            return true;
        }
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the
     * specified element.
     */
    public boolean remove(int val) {
        //删除时采用交换的方式删除元素，即每次就要删除的元素与最后一位替换，从而保证删除的时候时间复杂度降低为O(1)
        //如果当前元素存在
        if (elementIndexList.containsKey(val)) {
            //获取当前元素的索引集合
            Set<Integer> indexSet = elementIndexList.get(val);
            //获取其中一个索引进行删除
            Iterator<Integer> iterator = indexSet.iterator();
            Integer index = iterator.next();
            indexSet.remove(index);
            //如果当前值删除完了，则从索引集合中移除
            if (indexSet.isEmpty()) {
                elementIndexList.remove(val);
            }
            //更新索引集合
            if (index < elements.size() - 1) {
                Integer lastNum = elements.get(elements.size() - 1);
                Set<Integer> lastNumIndexList = elementIndexList.get(lastNum);
                lastNumIndexList.remove(elements.size() - 1);
                lastNumIndexList.add(index);
            }
            //更新元素集合
            elements.set(index, elements.get(elements.size() - 1));
            elements.remove(elements.size() - 1);
            return true;
        }
        //如果当前元素不存在直接返回false
        else {
            return false;
        }
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        //返回随机位置的数
        Random random = new Random();
        int randomIndex = random.nextInt(elements.size());
        return elements.get(randomIndex);
    }
}
