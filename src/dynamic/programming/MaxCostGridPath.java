package dynamic.programming;

import java.util.HashMap;
import java.util.Map;

//Move from bottom-left to top-right and find the cost of path with max cost

public class MaxCostGridPath {
	
	public static int findMaxCost(int[][] grid) {
		
		int m = grid.length - 1;
		int n = 0;
		
		int maxCost = findMaxCost(grid, m, n);
		
		return maxCost;
	}
	
	private static int findMaxCost(int[][] grid, int m, int n) {
		
		int maxColIndex = grid[0].length-1;
		
		if (m<0 || n>maxColIndex) {
			return 0;
		} else if (m==0 && n==maxColIndex) {
			return grid[m][n];
		} else {
			return grid[m][n] + Math.max(findMaxCost(grid, m-1, n), findMaxCost(grid, m, n+1));
		}
	}
	
	//Move from top-left to bottom-right and find the cost of path with max cost
	
	public static int findMaxCost3(int[][] grid) {
		
		int m = 0;
		int n = 0;
		
		int maxCost = findMaxCost3(grid, m, n);
		
		return maxCost;
	}
	
	private static int findMaxCost3(int[][] grid, int m, int n) {
		
		int maxColIndex = grid[0].length-1;
		int maxRowIndex = grid.length-1;
		
		if (m>maxRowIndex || n>maxColIndex) {
			return 0;
		} else if (m==maxRowIndex && n==maxColIndex) {
			return grid[m][n];
		} else {
			return grid[m][n] + Math.max(findMaxCost3(grid, m+1, n), findMaxCost3(grid, m, n+1));
		}
	}
	
	//Move from top-left to bottom-right and find the cost of path with max cost

	//Memoization approach
	public static int findMaxCost2(int[][] grid) {
		
		int m = grid.length - 1;
		int n = 0;
		Map<String, Integer> map = new HashMap<>();
		
		return memHelper(grid, m, n, map);
	}
	
	private static int memHelper(int[][] grid, int m, int n, Map<String, Integer> map) {
		
		String key = m + "," + n;
		
		if (map.containsKey(key)) {
			return map.get(key);
		}
		
		int maxColIndex = grid[0].length-1;
		
		if (m<0 || n>maxColIndex) {
			map.put(key, 0);
			return 0;
		} else if (m==0 && n==maxColIndex) {
			int val = grid[m][n];
			map.put(key, val);
			return val;
		}
		
		int cost = grid[m][n] + Math.max(findMaxCost(grid, m-1, n), findMaxCost(grid, m, n+1));
		map.put(key, cost);
		return cost;
	}

	public static void main(String[] args) {
		
		int[][] grid = new int[][] {
			{0,0,0,0,5},
			{0,1,1,1,0},
			{2,0,0,0,10}
		};
		
		int maxCost = findMaxCost3(grid);
		System.out.println(maxCost);
	}
}
