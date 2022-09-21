package dynamic.programming;

public class UniquePathCount {
	
	public int uniquePaths(int m, int n) {
		
		if (m==0 || n==0) {
			return 0;
		} else if (m==1 || n==1) {
			return 1;
		}
		
		return uniquePaths(m-1,n) + uniquePaths(m,n-1);
	}
	
	public int uniquePaths2(int m, int n) {
		
		int[][] grid = new int[m][n];
		
		for (int i=0; i<m; i++) {
			grid[i][0] = 1;
		}
		
		for (int j=0; j<n; j++) {
			grid[0][j] = 1;
		}
		
		for (int i=1; i<m; i++) {
			for (int j=1; j<n; j++) {
				grid[i][j] = grid[i-1][j] + grid[i][j-1];
			}
		}
		
		return grid[m-1][n-1];
		
	}

	public static void main(String[] args) {
		
		UniquePathCount u = new UniquePathCount();
		
		int m = 3;
		int n = 5;
		
		int nPaths = u.uniquePaths2(m,n);
		System.out.println(nPaths);

	}

}
