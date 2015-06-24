package train.passandra.algorithms;

import java.util.ArrayList;

import train.passandra.parse.ParsingData;

public class TFIDF {
	ArrayList<ParsingData> data;
	Double totalWordPoint = 0.0;

	public TFIDF(ArrayList<ParsingData> data) {
		this.data = data;
		for (int i = 0; i < data.size(); i++) {
			totalWordPoint += data.get(i).totalPoint();
		}
	}

	public Double getCorrelation(String keyword) {
		int dataSize = data.size();
		int dataImplicationCnt = 0;

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).isImplication(keyword))
				dataImplicationCnt++;
		}

		Double wordImplicationCnt = 0.0;

		for (int i = 0; i < data.size(); i++) {
			wordImplicationCnt += data.get(i).getImplicationCnt(keyword);
		}

		//		System.out.println(wordImplicationCnt);
		//		System.out.println(totalWordPoint);
		//		System.out.println(dataSize);
		//		System.out.println(dataImplicationCnt);
		//		System.out.println(Math.log10(dataSize / dataImplicationCnt));
		//		System.out.println(wordImplicationCnt / totalWordPoint);

		try {
			Double correlation = (wordImplicationCnt / totalWordPoint) * (Math.log10(dataSize / dataImplicationCnt));
			return correlation;
		} catch (Exception e) {
			return 0.0;
		}
	}
}
