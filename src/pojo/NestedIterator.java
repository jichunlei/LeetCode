package pojo;

import java.util.Iterator;
import java.util.List;

/**
 *  扁平化嵌套列表迭代器
 *
 * @author : xianzilei
 * @date : 2020/6/1 16:13
 */
public class NestedIterator implements Iterator<Integer> {

    public NestedIterator(List<NestedInteger> nestedList) {

    }

    @Override
    public Integer next() {
        return 1;
    }

    @Override
    public boolean hasNext() {
        return false;
    }
}
