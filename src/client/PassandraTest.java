package client;

import java.util.ArrayList;

import train.passandra.algorithms.NaiveBayesian;
import train.passandra.algorithms.TFIDF;
import train.passandra.parser.Parser;
import train.passandra.parser.ParserByDictDotCom;
import train.passandra.parser.ParserByWiki;
import train.passandra.parser.ParsingData;

public class PassandraTest {
	public static void main(String[] args) {
		// Parser 객체 생성
		// 갯수의 제한은 1~ , Parser 클래스를 상속받은 기본 제공되는 2개의 Parser 사용
		// 따로 원하는 사이트가 있는 경우, Parser 클래스를 상속받아 class 정의 
		Parser wiki = new ParserByWiki();
		Parser dictDotCom = new ParserByDictDotCom();

		// 생성한 Parser 객체를 사용하기 위해선 Parser의 ArrayList 형태로 변환하여 사용
		ArrayList<Parser> parses = new ArrayList<Parser>();
		parses.add(wiki);
		parses.add(dictDotCom);

		// 위에서 생성한 Parser와 기준이 될 keyword를 생성자로 ParsingData 생성.
		// 생성과 동시에 Parsing 작업 실행 후 내부에서 데이터 전처리 시행
		// ParsingData 또한 ArrayList 형태로 사용
		ArrayList<ParsingData> data = new ArrayList<ParsingData>();
		data.add(new ParsingData(wiki, "man"));
		data.add(new ParsingData(dictDotCom, "man"));

		// 기준이 되는 ParsingData의 ArrayList와
		// 기준 모델에 대하여 비교하는 데이터 또한 Parsing 작업을 하기 때문에 사용할 Parse들의 ArrayList를 전달.
		NaiveBayesian test = new NaiveBayesian(data, parses);
		
		System.out.println("keyword : man\n");
		
		System.out.println("NaiveBayesian");
		System.out.println("Correlation (woman): " + test.getCorrelation("woman") + "%");
		System.out.println("Correlation (cup): " + test.getCorrelation("cup") + "%");
		System.out.println("Correlation (rain): " + test.getCorrelation("rain") + "%");
		System.out.println("Correlation (wolf): " + test.getCorrelation("wolf") + "%\n");

		TFIDF tfidf = new TFIDF(data);
		System.out.println("TFIDF");
		System.out.println("Correlation (woman): " + tfidf.getCorrelation("woman"));
		System.out.println("Correlation (cup): " + tfidf.getCorrelation("cup"));
		System.out.println("Correlation (rain): " + tfidf.getCorrelation("rain"));
		System.out.println("Correlation (wolf): " + tfidf.getCorrelation("wolf"));

	}
}
