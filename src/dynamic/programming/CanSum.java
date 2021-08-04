package dynamic.programming;

import java.util.HashMap;
import java.util.Map;

/*
 * The function should return a boolean indicating whether or not it is possible to generate the targetSum
 * using numbers from the array. You may use an element of the array as many times as needed.
 * You may assume that all numbers are non-negative.
 */

public class CanSum {

	// Basic recursion approach. Avg time = 6,256,935 microseconds
	private boolean canSum1(int targetSum, int[] nums) {

		if (targetSum == 0) {
			return true;
		} else if (targetSum < 0) {
			return false;
		}

		for (int i = 0; i < nums.length; i++) {
			int remSum = targetSum - nums[i];
			if (canSum1(remSum, nums)) {
				return true;
			}
		}

		return false;
	}

	// Memoization technique. Avg time = 185 microseconds
	private boolean canSum2(int targetSum, int[] nums) {

		Map<Integer, Boolean> map = new HashMap<>();

		return memHelper(targetSum, nums, map);
	}

	private boolean memHelper(int targetSum, int[] nums, Map<Integer, Boolean> map) {

		if (map.containsKey(targetSum)) {
			return map.get(targetSum);
		}

		if (targetSum == 0) {
			return true;
		} else if (targetSum < 0) {
			return false;
		}

		for (int i = 0; i < nums.length; i++) {
			if (memHelper(targetSum - nums[i], nums, map)) {
				map.put(targetSum, true);
				return true;
			}
		}

		map.put(targetSum, false);
		return false;

	}

	// Avg times specified in method docs for 3 runs
	public static void main(String[] args) {

		CanSum sol = new CanSum();

		long start = System.nanoTime();

		int[] arr = { 7, 14};
		boolean res = sol.canSum2(300, arr); // false

		long end = System.nanoTime();

		System.out.println("Result = " + res + ", and it took " + (end - start) + " ns");

	}

}
