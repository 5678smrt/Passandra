package train.passandra.algorithms;

import java.util.ArrayList;

import train.passandra.parser.ParsingData;

public class TFIDF {
	ArrayList<ParsingData> data;
	Double totalWordPoint = 0.0;

	public TFIDF(ArrayList<ParsingData> data) {
		this.data = data;
		for (int i = 0; i < data.size(); i++) {
			totalWordPoint += data.get(i).totalPoint();
		}
	}

	/**
	 * 전체 문서에서 keyword가 차지하는 갯수 / 전체 문서의 총 단어 갯수 * Log(전체 문서의 갯수/keyword가 포함 되는 문서 갯수)
	 * >> ParsingData => 문서
	 * 
	 * 문서에서 keyword가 의미있는 단어인지 판별
	 * 
	 * @param keyword
	 * @return
	 */
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
		try {

			Double dataImplicationRatio = (double) (dataSize / dataImplicationCnt);
			
			Double correlation = (wordImplicationCnt / totalWordPoint)
					* (Math.log10(dataImplicationRatio));

			return correlation;
		} catch (Exception e) {
			return 0.0;
		}
	}
}
