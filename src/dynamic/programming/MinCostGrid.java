package dynamic.programming;

import java.util.HashMap;
import java.util.Map;

public class MinCostGrid {
	
	public int minPathSum(int[][] grid) {
        
        int m = 0;
        int n = 0;
        
        return minSum(grid, m, n);
    }
    
    private int minSum(int[][] grid, int m, int n) {
        
        int maxRowIndex = grid.length-1;
        int maxColIndex = grid[0].length-1;
        
        if (m>maxRowIndex || n>maxColIndex) {
            return 1000;
        } else if (m==maxRowIndex && n==maxColIndex) {
            return grid[m][n];
        }
        
        return grid[m][n] + Math.min(minSum(grid,m+1,n), minSum(grid,m,n+1));

    }
    
    public static int minPathSum2(int[][] grid) {
		
    	int m = 0;
        int n = 0;
		Map<String, Integer> map = new HashMap<>();
		
		return memHelper(grid, m, n, map);
	}
    
    private static int memHelper(int[][] grid, int m, int n, Map<String, Integer> map) {
		
		String key = m + "," + n;
		
		if (map.containsKey(key)) {
			return map.get(key);
		}
		
		int maxRowIndex = grid.length-1;
        int maxColIndex = grid[0].length-1;
        
        if (m>maxRowIndex || n>maxColIndex) {
        	map.put(key, 1000); //very imp to take a high value instead of 0 so that min function can work correctly
			return 1000;
        } else if (m==maxRowIndex && n==maxColIndex) {
        	int val = grid[m][n];
			map.put(key, val);
			return val;
        }
		
		int cost = grid[m][n] + Math.min(memHelper(grid,m+1,n,map), memHelper(grid,m,n+1,map));
		map.put(key, cost);
		return cost;
	}
    
    

	public static void main(String[] args) {
		
		int[][] grid = new int[][] {
			{1,3,1},
			{1,5,1},
			{4,2,1}
		};
		
		MinCostGrid m = new MinCostGrid();
		
		int minCost = m.minPathSum2(grid);
		System.out.println(minCost);

	}

}
