package dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fibonacci {

	// Basic recursion approach. Avg time = 7,84,01,415 microseconds!!!
	private long fib1(int n) {

		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		}

		return fib1(n - 1) + fib1(n - 2);
	}

	// Memoization technique. Avg time = 650 microseconds
	private long fib2(int n) {
		Map<Integer, Long> map = new HashMap<>();
		return memHelper(n, map);
	}

	private long memHelper(int n, Map<Integer, Long> map) {
		if (map.containsKey(n)) {
			return map.get(n);
		}
		
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		}

		long res = memHelper(n - 1, map) + memHelper(n - 2, map);
		map.put(n, res);

		return res;
	}

	// Tabulation using primitive array. Avg time = 6.7 microseconds
	private long fib3(int n) {

		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		}

		long[] arr = new long[n + 1];
		arr[0] = 0;
		arr[1] = 1;

		int i = 0;
		for (i = 0; i < n - 1; i++) {
			arr[i + 1] += arr[i];
			arr[i + 2] += arr[i];
		}
		arr[i + 1] += arr[i];

		return arr[n];

	}

	// Tabulation using arrayList. Avg time = 850 microseconds
	private long fib4(int n) {

		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		}

		List<Long> list = new ArrayList<>(Arrays.asList(new Long[n + 1]));
		Collections.fill(list, 0l);
		list.set(1, 1l);

		int i = 0;
		for (i = 0; i < n - 1; i++) {
			list.set(i + 1, list.get(i + 1) + list.get(i));
			list.set(i + 2, list.get(i + 2) + list.get(i));
		}
		list.set(i + 1, list.get(i + 1) + list.get(i));

		return list.get(n);
	}

	// Basic iterative approach. Avg time = 3.8 microseconds
	private long fib5(int n) {

		long first = 0;
		long second = 1;
		long third = first + second;
		for (int i = 2; i <= n; i++) {
			third = first + second;
			first = second;
			second = third;
		}
		return third;
	}

	// Avg times specified in method docs for 3 runs
	public static void main(String[] args) {

		// 0 1 1 2 3 5 8 13 21 where F(0)=0, F(1)=1...

		Fibonacci sol = new Fibonacci();

		long start = System.nanoTime();
		
		long res = sol.fib2(50); //12586269025
		
		long end = System.nanoTime();

		System.out.println("Result is " + res + ", takes " + (end - start) + " ns");
	}

}
