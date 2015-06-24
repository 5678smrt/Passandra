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
		Parser wiki = new ParserByWiki();
		Parser dictDotCom = new ParserByDictDotCom();

		ArrayList<Parser> parses = new ArrayList<Parser>();
		parses.add(wiki);
		parses.add(dictDotCom);

		ArrayList<ParsingData> data = new ArrayList<ParsingData>();
		data.add(new ParsingData(wiki, "man"));
		data.add(new ParsingData(dictDotCom, "man"));

		NaiveBayesian test = new NaiveBayesian(data, parses);

		System.out.println("Correlation : " + test.getCorrelation("man")+"%");
		System.out.println("Correlation : " + test.getCorrelation("woman")+"%");
		System.out.println("Correlation : " + test.getCorrelation("cup")+"%");
		System.out.println("Correlation : " + test.getCorrelation("wolf")+"%");
		System.out.println("Correlation : " + test.getCorrelation("rain")+"%");
		
		TFIDF tfidf = new TFIDF(data);
		
		System.out.println("Correlation : " + tfidf.getCorrelation("woman")+"%");
		System.out.println("Correlation : " + tfidf.getCorrelation("cup")+"%");
		System.out.println("Correlation : " + tfidf.getCorrelation("sex")+"%");
		System.out.println("Correlation : " + tfidf.getCorrelation("rain")+"%");

	}
}
