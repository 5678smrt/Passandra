package train.passandra.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import train.passandra.preprocessing.SelectKeyword;

public class ParsingData {
	HashMap<String, Integer> keywordMap;

	public ParsingData(Parser parse, String keyword) {
		keywordMap = new HashMap<String, Integer>();
		processing(parse, keyword);
	}

	public void processing(Parser parse, String keyword) {
		SelectKeyword ws = new SelectKeyword(parse.getContent(keyword),
				"StopWordList.data");

		ArrayList<String> keywordList = ws.getSelectedWord();

		for (int i = 0; i < keywordList.size(); i++) {
			String word = keywordList.get(i);
			int cnt;
			try {
				cnt = keywordMap.get(word);
				keywordMap.put(word, cnt + 1);
			} catch (Exception e) {
				keywordMap.put(word, 1);
			}
			// System.out.println(word);
		}
	}

	public int totalPoint() {
		Iterator<String> it = keywordMap.keySet().iterator();

		int sum = 0;

		while (it.hasNext()) {
			String key = (String) it.next();

			sum += keywordMap.get(key);
		}

		return sum;
	}

	public Boolean isImplication(String keyword) {
		return keywordMap.containsKey(keyword);
	}

	public int getImplicationCnt(String keyword) {
		try {
			return keywordMap.get(keyword);
		} catch (Exception e) {
			return 0;
		}

	}

	public HashMap<String, Integer> getKeywordMap() {
		return keywordMap;
	}
}
