package dynamic.programming;

import java.util.HashMap;
import java.util.Map;

public class CanConstruct {

	private boolean canConstruct(String target, String[] words) {

		if ("".equals(target)) {
			return true;
		}
		
		for (String word: words) {
			if (target.startsWith(word)) {
				String remWord = target.substring(word.length());
				if (canConstruct(remWord, words)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean canConstruct2(String target, String[] words) {
		Map<String, Boolean> map = new HashMap<>();
		return memHelper(target, words, map);
	}

	private boolean memHelper(String target, String[] words, Map<String, Boolean> map) {

		if (map.containsKey(target)) {
			return map.get(target);
		}
		
		if ("".equals(target)) {
			return true;
		}
		
		for (String word: words) {
			if (target.startsWith(word)) {
				String remWord = target.substring(word.length());
				if (memHelper(remWord, words, map)) {
					map.put(target, true);
					return true;
				}
			}
		}
		
		map.put(target, false);
		return false;	
	}

	public static void main(String[] args) {
		
		CanConstruct sol = new CanConstruct();
		
		String target = "eeeeeeeeeeeeeeeeeeeeeeeeeeef";
		String[] words = {"e", "ee", "eee", "eeee", "eeeee", "eeeeee"};
		
		long start = System.nanoTime();
		
		boolean res = sol.canConstruct2(target, words);

		long end = System.nanoTime();

		System.out.println("Result = " + res + ", and it took " + (end - start) + " ns");
	}
}
