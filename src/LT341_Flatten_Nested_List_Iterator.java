import java.util.*;

import common.NestedInteger;

/*
 * Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

Stack, Design
 */
public class LT341_Flatten_Nested_List_Iterator {
    /**
     * // This is the interface that allows for creating nested lists. // You should not implement it, or speculate about its implementation public interface NestedInteger {
     *
     * // @return true if this NestedInteger holds a single integer, rather than a nested list. public boolean isInteger();
     *
     * // @return the single integer that this NestedInteger holds, if it holds a single integer // Return null if this NestedInteger holds a nested list public Integer getInteger();
     *
     * // @return the nested list that this NestedInteger holds, if it holds a nested list // Return null if this NestedInteger holds a single integer public List<NestedInteger> getList(); }
     */
    public class NestedIterator implements Iterator<Integer> {
	Stack<NestedInteger> stk = null;

	public NestedIterator(List<NestedInteger> nestedList) {
	    stk = new Stack<>();
	    for (int i = nestedList.size() - 1; i >= 0; i--) {
		stk.push(nestedList.get(i));
	    }
	}

	@Override
	public Integer next() {
	    return hasNext() ? stk.pop().getInteger() : null;
	}

	@Override
	public boolean hasNext() {
	    while (!stk.isEmpty()) {
		NestedInteger cur = stk.peek();
		if (cur.isInteger()) {
		    return true;
		} else {
		    stk.pop(); // further flatten
		    for (int i = cur.getList().size() - 1; i >= 0; i--) {
			stk.push(cur.getList().get(i));
		    }
		}
	    }

	    return false;
	}
    }

    /**
     * Your NestedIterator object will be instantiated and called as such: NestedIterator i = new NestedIterator(nestedList); while (i.hasNext()) v[f()] = i.next();
     */
}
