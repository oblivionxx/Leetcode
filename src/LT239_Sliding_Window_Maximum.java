
/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Hint:

How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window’s size.
Remove redundant elements and the queue should store only elements that need to be considered.

Heap.
 */
import java.util.*;

public class LT239_Sliding_Window_Maximum {
    // 1. deque. O(n)  !!!
    // 初始化第一个窗口（0-第k－1个元素），一次向deque中加入数组中的元素。每次加入新元素时，若deque中最后一个元素比新元素小，则删去，直到deque中最后一个元素比新元素大时停止（或deque为空时停止），然后加入新元素。
    // 添加元素：从第k的元素开始，每次加入新元素时，若deque中最后一个元素比新元素小，则删去，直到deque中最后一个元素比新元素大时停止（或deque为空时停止），然后加入新元素。此时deque中第一个元素即为当前窗口的最大值，加入答案中。
    // 删除元素：应该删去（当前位置－k）位置的元素，看deque第一个元素是否和要删除元素相等，若不相等则说明在之前的元素加入过程中该元素已经删去，则不用做任何操作，否则删去deque中第一个元素即可。
    public int[] maxSlidingWindow(int[] nums, int k) {
	// Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
	if (k == 0)
	    return new int[0];

	LinkedList<Integer> q = new LinkedList<Integer>();
	int[] res = new int[nums.length - k + 1];

	for (int i = 0; i < nums.length; i++) {
	    while (!q.isEmpty() && nums[i] >= nums[q.getLast()]) {
		q.removeLast();
	    } // remove al the numbers in the queue less than nums[i]
	    q.addLast(i); // append nums[i]'s index to the end of list. so the list is decreasing order.

	    if (i - q.getFirst() + 1 > k) {
		q.removeFirst();
	    }
	    if (i + 1 >= k)
		res[i - k + 1] = nums[q.getFirst()];
	}

	return res;
    }

    // 2. heap. O(nlgk)
    public int[] maxSlidingWindow2(int[] nums, int k) {
	int len = nums.length;
	int[] result = new int[len - k + 1];
	if (nums.length == 0)
	    return new int[0];

	// max heap
	PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k, Collections.reverseOrder());

	for (int i = 0; i < k; i++) {
	    queue.add(nums[i]);
	}

	// keep the heap with window size k. remove left element.
	result[0] = queue.peek();
	for (int i = k; i < len; i++) {
	    queue.remove(nums[i - k]);
	    queue.add(nums[i]);
	    result[i - k + 1] = queue.peek();
	}

	return result;
    }
}
