package dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//DP solution not done
public class AllConstruct {
	
	private ArrayList<ArrayList<String>> allConstruct(String target, String[] words) {
		
		if ("".equals(target)) {
			return new ArrayList<ArrayList<String>>();
		}
		
		ArrayList<ArrayList<String>> allList = null;
		for (String word : words) {
			if (target.startsWith(word)) {
				String remWord = target.substring(word.length());
				List<ArrayList<String>> list = allConstruct(remWord, words);
				
				if (list != null && !list.isEmpty()) {
					if (allList == null) {
						allList = new ArrayList<>();
					}
					for (List<String> item : list) {
						item.add(0, word);
					}
				} else if (list != null && list.isEmpty()) {
					if (allList == null) {
						allList = new ArrayList<>();
					}
					list.add(new ArrayList<>(Arrays.asList(word)));
				}

				if (allList != null && list != null) {
					allList.addAll(list);
				}
			}
		}
		
		return allList;
	}

	public static void main(String[] args) {
		
		AllConstruct sol = new AllConstruct();

		String target = "purple";
		String[] words = {"purp", "p", "ur", "le", "purpl"};

		List<ArrayList<String>> list = sol.allConstruct(target, words);
		System.out.println(list);
	}
}
