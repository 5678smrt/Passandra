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

	/**
	 * keyword의 데이터를 Parsing 후 전처리 작업을 통해
	 * 멤버변수 keywordMap에 저장 
	 * @param parser 사용할 웹페이지의 정보와 방법을 담은 Parser
	 * @param keyword Parsing할 keyword
	 */
	public void processing(Parser parser, String keyword) {
		SelectKeyword ws = new SelectKeyword(parser.getContent(keyword),
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
		}
	}

	/**
	 * @return ParsingData에 포함된 단어의 총 갯수
	 */
	public int totalPoint() {
		Iterator<String> it = keywordMap.keySet().iterator();

		int sum = 0;

		while (it.hasNext()) {
			String key = (String) it.next();
			sum += keywordMap.get(key);
		}

		return sum;
	}

	/**
	 * keyword가 본 데이터에 포함되어있는지 확인
	 * @param keyword 검사할 keyword
	 * @return 포함되면 T, 포함하지 않으면 F
	 */
	public Boolean isImplication(String keyword) {
		return keywordMap.containsKey(keyword);
	}

	/**
	 * @param keyword
	 * @return keyword의 포함 개수 반환
	 */
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
