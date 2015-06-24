package train.passandra.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import train.passandra.io.Parse;
import train.passandra.io.ParsingData;

public class NaiveBayesian {
	private NaiveBayesianModel model;
	private ArrayList<Parse> prases;

	public NaiveBayesian(ArrayList<ParsingData> data, ArrayList<Parse> prases) {
		model = creatModel(data);
		this.prases = prases;
	}

	/**
	 * @param data
	 * @return NaiveBayesianModel 데이터의 분석을 위한 키워드의 모델
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
	 * @param keyword
	 * @return correlation
	 */
	public int getCorrelation(String keyword) {

		int searchPoint = compareModel(keyword);
		int modelPoint = model.getTotalWordSize();

		System.out.println("searchPoint : " + searchPoint + ", modelPoint : "
				+ modelPoint);
		return searchPoint * 100 / modelPoint;
	}

	private int compareModel(String keyword) {
		HashMap<String, Integer> modelMap = model.getWordMap();
		HashMap<String, Integer> searchMap;
		// = creatModel(keyword).getWordMap();

		ArrayList<ParsingData> data = new ArrayList<ParsingData>();

		for (int i = 0; i < prases.size(); i++)
			data.add(new ParsingData(prases.get(i),keyword));

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

	public ArrayList<String> getRecommendWords(ArrayList<String> content) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < content.size(); i++)
			map.put(content.get(i), getCorrelation(content.get(i)));

		return sortByValue(map);
	}

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
