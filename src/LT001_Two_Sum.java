/*
 Given an array of integers, find two numbers such that they add up to a specific target number.
 The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 You may assume that each input would have exactly one solution.
	Input: numbers={2, 7, 11, 15}, target=9
	Output: index1=1, index2=2
 */

/*
 * Array. HashMap
 * HashMap store (number, index).
 */
import java.util.*;

public class LT001_Two_Sum {
    public int[] twoSum(int[] nums, int target) {
	// output is 0 based or 1 based.
	// nums sorted ? duplicate
	int[] res = new int[2];

	HashMap<Integer, Integer> map = new HashMap<>();
	for (int i = 0; i < nums.length; i++) {
	    if (map.containsKey(target - nums[i])) {
		res[0] = map.get(target - nums[i]) + 1;
		res[1] = i + 1;
		return res;
	    }
	    map.put(nums[i], i);
	}

	return res;
    }
}
