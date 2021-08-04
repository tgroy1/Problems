package dynamic.programming;

import java.util.HashMap;
import java.util.Map;

/*
 * A traveller is located at the top-left corner of a m x n grid and is trying to reach the bottom-right corner of the grid.
 * The traveller can only move either down or right at any point in time. 
 * How many possible unique paths are there?
 */

public class GridTraveller {

	// Basic recursion approach. Avg time = 9,201,879 microseconds
	private long gridTraveller1(int m, int n) {
		if (m == 0 || n == 0) {
			return 0;
		} else if (m == 1 || n == 1) {
			return 1;
		}

		return gridTraveller1(m - 1, n) + gridTraveller1(m, n - 1);
	}

	// Memoization technique. Avg time = 3120 microseconds
	private long gridTraveller2(int m, int n) {
		Map<String, Long> map = new HashMap<>();
		return memHelper(m, n, map);
	}

	private long memHelper(int m, int n, Map<String, Long> map) {

		String key = m + "," + n;

		if (map.containsKey(key)) {
			return map.get(key);
		}

		if (m == 0 || n == 0) {
			return 0;
		} else if (m == 1 || n == 1) {
			return 1;
		}

		long res = memHelper(m - 1, n, map) + memHelper(m, n - 1, map);
		map.put(key, res);

		return res;
	}

	// Tabulation using primitive array. Avg time = 55.2 microseconds
	private long gridTraveller3(int m, int n) {

		long[][] arr = new long[m + 1][n + 1];
		arr[1][1] = 1;

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				long curr = arr[i][j];
				if (j + 1 <= n) {
					arr[i][j + 1] += curr;
				}
				if (i + 1 <= m) {
					arr[i + 1][j] += curr;
				}
			}
		}

		return arr[m][n];
	}

	// Avg times specified in method docs for 3 runs
	public static void main(String[] args) {

		GridTraveller sol = new GridTraveller();

		long start = System.nanoTime();

		long nWays = sol.gridTraveller3(30, 12); // 2311801440

		long end = System.nanoTime();

		System.out.println("No of ways = " + nWays + ", takes " + (end - start) + " ns");
	}

}
