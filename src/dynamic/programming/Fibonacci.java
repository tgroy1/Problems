package dynamic.programming;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fibonacci {
	
	//Memoization using a HashMap. For n=50, avg time = 650 microseconds
	public long fib1(int n) {
        
        if (n==0) {
            return 0;
        } else if (n==1) {
            return 1;
        }
        
        Map<Integer, Long> map = new HashMap<>();
        map.put(0,0l);
        map.put(1,1l);

        return fibMem(n, map);
    }
    
    private long fibMem(int n, Map<Integer, Long> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        
        long res = fibMem(n-1, map) + fibMem(n-2, map);
        map.put(n, res);
        
        return res;
    }
	
    //Tabulation using primitive array. Avg time = 6.7 microseconds
	public long fib2(int n) {
        
        if (n==0) {
            return 0;
        } else if (n==1) {
            return 1;
        }
        
        long[] arr = new long[n+1];
        arr[0] = 0;
        arr[1] = 1;
        
        int i = 0;
        for (i=0; i<n-1; i++) {
            arr[i+1] += arr[i];
            arr[i+2] += arr[i];
        }
        arr[i+1] += arr[i];
        
        return arr[n];
        
    }
	
	//Tabulation using arrayList. Avg time = 850 microseconds
	public long fib3(int n) {
        
        if (n==0) {
            return 0;
        } else if (n==1) {
            return 1;
        }
        
        List<Long> list = new ArrayList<>(Arrays.asList(new Long[n+1]));
        Collections.fill(list,0l);
        list.set(1,1l);
        
        int i = 0;
        for (i=0; i<n-1; i++) {
            list.set(i+1, list.get(i+1) + list.get(i));
            list.set(i+2, list.get(i+2) + list.get(i));
        }
        list.set(i+1, list.get(i+1) + list.get(i));
        
        return list.get(n);
    }
	
	public static void main(String[] args) {
		
		Fibonacci sol = new Fibonacci();
		
		long start = System.nanoTime();
		
		long res = sol.fib3(50);
		
		long end = System.nanoTime();
		
		System.out.println("Result is " + res + " and it took " + (end - start) + " ns");
	}

}
