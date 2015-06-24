# Passandra

### Objective

텍스트에서 사용자의 프로필(관심사, 전공 분야 등)에 따라 관심사, 전공 분야 등에서 중요한 단어를 자동으로 추천해주는 라이브러리다.  
OCR 라이브러리와 함께 쓰면 자동으로 문서의 단어를 인식하고 사용자에게 중요한 단어를 미리 추천해줄수 있다.  

### How to?

* Naive Bayesian Classification 을 이용하여 각 문서를 대표할 수 있는 단어를 찾아낸다.  
* TFxIDF 알고리즘을 이용하여 각 문서를 대표하는 각각의 단어들이 문서 전체집합에서 어느정도 연관성을 갖고 있는지를 효율적으로 계산하여 정확도를 높혔다.  

출처: 신진섭,이창훈<단어의 연관성을 이용한 문서의 자동분류>,<한국정보처리학회 논문지>,<제6권 제9호>: TFxIDF 알고리즘  

### Language

Java (JDK 1.5 이상)

### Participants

* [Saehyun Kim](https://github.com/saehyun/)  
* [Sungjin Lee](https://github.com/qchonjae)  
* [Myeongjun Kim](https://github.com/kimmyeongjun)  
* [Byeongcheol Park](https://github.com/gkr2410)  
