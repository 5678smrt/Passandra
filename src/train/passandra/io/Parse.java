package train.passandra.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Parse {
	protected String uri;
	
	private String regexTag, regexBraket, regexEng; // 불필요한 내용 제거하는 정규표현식

	public Parse(String uri) {
		this.uri = uri;
		this.regexTag = "<[^>]*>"; // html tag 제거
		this.regexBraket = "[\\[\\d\\]]"; // [%d] 제거
		this.regexEng = "[^\\x00-\\x7F]"; // 영어 이외에 문자 제거
	}
	
	protected String acceptRegex(String target) {
		Pattern pattern = Pattern.compile(regexTag);
		Matcher mat = pattern.matcher("");
		mat.reset(target);
		target = mat.replaceAll("");

		pattern = Pattern.compile(regexBraket);
		mat = pattern.matcher("");
		mat.reset(target);
		target = mat.replaceAll("");

		pattern = Pattern.compile(regexEng);
		mat = pattern.matcher("");
		mat.reset(target);
		target = mat.replaceAll("");
		return target;
	}
	
	/**
	 * 검색어 search의 타입 판별
	 * 
	 * @param search
	 * @return 한글이면 F, 영어면 T
	 */
	protected boolean checkStringType(String search) {
		for (int i = 0; i < search.length(); i++) {
			if (Character.getType(search.charAt(i)) == Character.OTHER_LETTER) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * web에서 search에 대한 내용을 파싱하여 String형태로 반환한다.
	 *
	 * @param 검색할 내용
	 * @return search의 대한 검색 결과
	 */
	public abstract String getContent(String search);
	
	
}
