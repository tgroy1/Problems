package dynamic.programming;

import java.util.HashMap;
import java.util.Map;

public class CountConstruct {

	private int countConstruct(String target, String[] words) {

		if ("".equals(target)) {
			return 1;
		}

		int count = 0;

		for (String word : words) {
			if (target.startsWith(word)) {
				String remWord = target.substring(word.length());
				count += countConstruct(remWord, words);
			}
		}

		return count;
	}

	private int countConstruct2(String target, String[] words) {
		Map<String, Integer> map = new HashMap<>();
		return memHelper(target, words, map);
	}

	private int memHelper(String target, String[] words, Map<String, Integer> map) {

		if (map.containsKey(target)) {
			return map.get(target);
		}

		if ("".equals(target)) {
			return 1;
		}

		int count = 0;
		for (String word : words) {
			if (target.startsWith(word)) {
				String remWord = target.substring(word.length());
				count += memHelper(remWord, words, map);
			}
		}

		map.put(target, count);
		return count;

	}

	public static void main(String[] args) {

		CountConstruct sol = new CountConstruct();

		String target = "eeeeeeeeeeeeeeeeeeeeeeeeeeef";
		String[] words = { "e", "ee", "eee", "eeee", "eeeee", "eeeeee" };

		long start = System.nanoTime();

		int res = sol.countConstruct2(target, words);

		long end = System.nanoTime();

		System.out.println("Result = " + res + ", and it took " + (end - start) + " ns");

	}
}
