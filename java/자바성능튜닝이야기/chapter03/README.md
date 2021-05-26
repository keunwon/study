# 3. 왜 자꾸 String을 쓰지 말라는 거야

## StringBuffer, StringBuilder
|클래스|특징|
|:---:|:---:|
|StringBuffer|다중 스레드에도 안전|
|StringBuilder|단일 스레드만 안전|;

### StringBuffer
|생성자|설명|
|:---:|:---:|
|StringBuffer()|기본 생성자 시, char[16] 크기|
|StringBuffer(CharSequence seq)|char[seq.length + 16] 크기|
|StringBuffer(int capacity)|char[capacity] 크기|
|StringBuffer(String str)|char[str.length + 16]|  


> JDK 5.0 이상을 사용하면 컴파일러 시점에서 문자열 추가 시  Stirng을 자동으로 StringBuilder로 변경한다.  
단, 반복 루프에서는 String 그대로 사용되므로 주의해야 한다.
