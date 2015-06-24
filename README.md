# Passandra

Objective
---------

 + 학습 데이터가 없는 환경에서 사용자의 입력 데이터와 대상 데이터와의 연관도를 수치화 해주는 라이브러리이다.  
Web parsing을 기반으로 단어들의 연관도를 분석해준다.  

How to?
-------

* Naive Bayesian Classification 을 이용하여 각 문서를 대표할 수 있는 단어를 찾아낸다.  
* TFxIDF 알고리즘을 이용하여 각 문서를 대표하는 각각의 단어들이 문서 전체집합에서 어느정도 연관성을 갖고 있는지를 효율적으로 계산하여 정확도를 높혔다.  

출처: 신진섭,이창훈<단어의 연관성을 이용한 문서의 자동분류>,<한국정보처리학회 논문지>,<제6권 제9호>: TFxIDF 알고리즘  

Usage
-----

  1. Properties for "Your Project" - Add JARs.. - lib/jsoup library 추가  
  2. Parser 클래스를 상속 받아 파싱할 웹사이트에 맞는 객체를 생성한다.  

  ```Java
  Parser wiki = new ParserByWiki();
  Parser dictDotCom = new ParserByDictDotCom();
  ```

  3. 생성 한 Parser 객체들을 List에 추가한다.  

  ```Java
  ArrayList<Parser> parses = new ArrayList<Parser>();
  parses.add(wiki);
  parses.add(dictDotCom);
  ```

  4. 사용자의 입력 데이터의 대해 학습 데이터를 생성한다.  

  ```Java
  ArrayList<ParsingData> data = new ArrayList<ParsingData>();
  // man: User input data
  data.add(new ParsingData(wiki, "man"));
  data.add(new ParsingData(dictDotCom, "man"));
  ```

  5. Bayesian, TFxIDF 알고리즘으로 입력 데이터와 대상 데이터의 연관도를 수치로 확인할 수 있다.  

  ```Java
  NaiveBayesian test = new NaiveBayesian(data, parses);
  System.out.println("Correlation : " + test.getCorrelation("woman") + "%");
  System.out.println("Correlation : " + test.getCorrelation("cup") + "%");
  System.out.println("Correlation : " + test.getCorrelation("rain") + "%");
    
  TFIDF tfidf = new TFIDF(data);
  System.out.println("Correlation : " + tfidf.getCorrelation("woman") + "%");
  System.out.println("Correlation : " + tfidf.getCorrelation("cup") + "%");
  System.out.println("Correlation : " + tfidf.getCorrelation("rain") + "%");
  ```

Language
--------

 + Java (JDK 1.5 이상)

Participants
------------

* [Saehyun Kim](https://github.com/saehyun/): Web Parsing, Document  
* [Sungjin Lee](https://github.com/qchonjae): Parsing result preprocessing  
* [Myeongjun Kim](https://github.com/kimmyeongjun): TF IDF algorithms  
* [Byeongcheol Park](https://github.com/gkr2410): Naive Bayesian algorithms, Test code  

License
-------

 + GNU Public License(GPL)
