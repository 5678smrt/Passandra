package train.passandra.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ParserByWiki extends Parser {

	public ParserByWiki() {
		super("https://en.wikipedia.org/?title=");
	}

	/**
	 * wiki에서 search에 대한 내용을 파싱하여 String형태로 반환한다.
	 * @param 검색할 내용
	 * @return search의 대한 검색 결과
	 */

	@Override
	public String getContent(String search) {
		try {
			if (!checkStringType(search)) {
				return "";
			}
			Document doc = Jsoup.connect(uri + search).userAgent("Mozilla/5.0").get();
			Elements selectByTag = doc.select("li"); // li 태그 내용 선택

			String content = selectByTag.html(); // 해당 태그 제거
			content = acceptRegex(content); // 정규식 적용

			return content;
		} catch (IOException e) {
			return "";
		}
	}

}