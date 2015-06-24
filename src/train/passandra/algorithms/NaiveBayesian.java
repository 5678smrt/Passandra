package train.passandra.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import train.passandra.parser.Parser;
import train.passandra.parser.ParsingData;

public class NaiveBayesian {
	private NaiveBayesianModel model;
	private ArrayList<Parser> prases;

	
	/**
	 * 비교 기준 keyword의 ParsingData들과 사용된 Parser를 생성자로 사용  
	 * @param data
	 * @param prases
	 */
	public NaiveBayesian(ArrayList<ParsingData> data, ArrayList<Parser> prases) {
		model = creatModel(data);
		this.prases = prases;
	}

	/**
	 * 비교 기준이 되는 keyword의 ParsingData를 이용하여 해당 kewyord의 model 생성
	 * @param data 
	 * @return NaiveBayesianModel
	 */
	private NaiveBayesianModel creatModel(ArrayList<ParsingData> data) {
		HashMap<String, Integer> map = data.get(0).getKeywordMap();
		Iterator<String> it = map.keySet().iterator();

		HashMap<String, Integer> keywordMap = new HashMap<String, Integer>();

		while (it.hasNext()) {
			String key = (String) it.next();
			int cnt = 1;
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i).getKeywordMap().get(key) != null) {
					cnt++;
				}
			}

			if (cnt >= data.size()) {
				keywordMap.put(key, map.get(key));
			}
		}

		return new NaiveBayesianModel(keywordMap);

	}

	/**
	 * model과 비교할 keyword를 매개변수로 받아 연관도 반환
	 * @param keyword
	 * @return correlation
	 */
	public int getCorrelation(String keyword) {

		int searchPoint = compareModel(keyword);
		int modelPoint = model.getTotalWordSize();

		System.out.println("searchPoint : " + searchPoint + ", modelPoint : " + modelPoint);
		return searchPoint * 100 / modelPoint;
	}

	
	/**
	 * 비교할 keyword를 Parser를 통해 ParsingData를 생성하여 model 생성 후
	 * 기준 keyword의 model과 비교분석하여 공통되는 부분의 수치를 반환 
	 * @param keyword
	 * @return 비교 후 두 keyword의 공통되는 단어의 갯수
	 */
	private int compareModel(String keyword) {
		HashMap<String, Integer> modelMap = model.getWordMap();
		HashMap<String, Integer> searchMap;
		// = creatModel(keyword).getWordMap();

		ArrayList<ParsingData> data = new ArrayList<ParsingData>();

		for (int i = 0; i < prases.size(); i++)
			data.add(new ParsingData(prases.get(i), keyword));

		searchMap = creatModel(data).getWordMap();

		int searchMapPoint = 0;

		Iterator<String> it = searchMap.keySet().iterator();

		while (it.hasNext()) {
			String key = (String) it.next();
			if (modelMap.get(key) != null)
				searchMapPoint += modelMap.get(key);
		}

		return searchMapPoint;
	}

	/**
	 * 비교할 단어들을 ArrayList 형태로 입력받아 분석 후 랭킹하여 반환 
	 * @param keywords
	 * @return rankingData
	 */
	public ArrayList<String> getRecommendWords(ArrayList<String> keywords) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < keywords.size(); i++)
			map.put(keywords.get(i), getCorrelation(keywords.get(i)));

		return sortByValue(map);
	}

	
	/**
	 * HashMap의 value값으로 key를 sorting하여 ArrayList 형태로 반환
	 * @param map
	 * @return 정렬된 ArrayList
	 */
	public ArrayList<String> sortByValue(final HashMap<String, Integer> map) {
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(map.keySet());

		Collections.sort(list, new Comparator<Object>() {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);

				return ((Comparable) v1).compareTo(v2);
			}
		});
		Collections.reverse(list);
		return list;
	}
}
