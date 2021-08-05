package dynamic.programming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * The function should return a list of numbers if it is possible to generate the targetSum
 * using numbers from the array. Return null if it is not possible to do the same. 
 * You may use an element of the array as many times as needed. You may assume that all numbers are non-negative.
 */

public class HowSum {
	
	// Basic recursion approach. Avg time = 5,962,090 microseconds
	private List<Integer> howSum1(int targetSum, int[] nums) {
		
		if (targetSum == 0) {
			return new ArrayList<>();
		} else if (targetSum < 0) {
			return null;
		}
		
		for (int num: nums) {
			int remainder = targetSum - num;
			List<Integer> list = howSum1(remainder, nums);
			if (list != null) {
				list.add(num);
				return list;
			}
		}
		
		return null;
		
	}
	
	// Memoization technique. Avg time = 182 microseconds
	private List<Integer> howSum2(int targetSum, int[] nums) {
		
		Map<Integer, List<Integer>> map = new HashMap<>();
		
		return memHelper(targetSum, nums, map);
	}
	
	private List<Integer> memHelper(int targetSum, int[] nums, Map<Integer, List<Integer>> map) {
		
		if (map.containsKey(targetSum)) {
			return map.get(targetSum);
		}
		
		if (targetSum == 0) {
			return new ArrayList<>();
		} else if (targetSum < 0) {
			return null;
		}
		
		for (int num : nums) {
			int remainder = targetSum - num;
			List<Integer> list = memHelper(remainder, nums, map);
			map.put(targetSum, list);
			if (list != null) {
				list.add(num);
				return list;
			}
		}
		
		return null;
	}

	// Avg times specified in method docs for 3 runs
	public static void main(String[] args) {

		HowSum sol = new HowSum();

		long start = System.nanoTime();

		int[] arr = {7, 14};
		List<Integer> res = sol.howSum1(300, arr); // false

		long end = System.nanoTime();

		System.out.println("Result = " + res + ", and it took " + (end - start) + " ns");

	}

}
