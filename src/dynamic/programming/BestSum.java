package dynamic.programming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * The function should return a list of numbers containing the shortest combination of numbers that adds up to
 * exactly the targetSum. If there is a tie, you may return any of the shortest combinations.
 * Return null if it is not possible to do the same. 
 * You may use an element of the array as many times as needed. 
 * You may assume that all numbers are non-negative.
 */

public class BestSum {

	// Basic recursion approach. Avg time = 6,085,104 microseconds (6 seconds)
	private List<Integer> bestSum1(int targetSum, int[] nums) {

		if (targetSum == 0) {
			return new ArrayList<>();
		} else if (targetSum < 0) {
			return null;
		}

		List<Integer> shortestSum = null;

		for (int num : nums) {
			int remainder = targetSum - num;
			List<Integer> list = bestSum1(remainder, nums);
			if (list != null) {
				list.add(num);
				if (shortestSum == null || list.size() < shortestSum.size()) {
					shortestSum = list;
				}
			}
		}
		return shortestSum;
	}

	// Memoization technique. Avg time = 1208 microseconds
	private List<Integer> bestSum2(int targetSum, int[] nums) {
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

		List<Integer> shortestSum = null;

		for (int num : nums) {
			int remainder = targetSum - num;
			List<Integer> list = memHelper(remainder, nums, map);
			if (list != null) {
				// very important...do not add num to list....that will modify memo map entries as well
				List<Integer> newList = new ArrayList<>(list);
				newList.add(num);
				if (shortestSum == null || newList.size() < shortestSum.size()) {
					shortestSum = newList;
				}
			}
		}

		map.put(targetSum, shortestSum);
		return shortestSum;
	}

	// Avg times specified in method docs for 3 runs
	public static void main(String[] args) {

		BestSum sol = new BestSum();

		long start = System.nanoTime();

		int[] arr = {2, 5, 25};
		List<Integer> res = sol.bestSum2(90, arr);

		long end = System.nanoTime();

		System.out.println("Result = " + res + ", and it took " + (end - start) + " ns");
	}

}
