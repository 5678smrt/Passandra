package client;

import java.util.ArrayList;

import train.passandra.io.Parse;
import train.passandra.io.ParseByDictDotCom;
import train.passandra.io.ParseByWiki;
import train.passandra.io.ParsingData;
import train.passandra.io.TFIDF;
import train.passandra.model.NaiveBayesian;

public class PassandraTest {
	public static void main(String[] args) {
		Parse wiki = new ParseByWiki();
		Parse dictDotCom = new ParseByDictDotCom();

		ArrayList<Parse> parses = new ArrayList<Parse>();
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
