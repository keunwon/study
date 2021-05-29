# IO에서 발생하는 병목 현상

## Stream
- ByteArrayInputStream: 바이트로 구성된 배열을 읽어서 입력 스트림으로 만듬
- FileInputStream: 이미지와 같은 바이터리 파일의 스트림을 만듬
- FilterInputStream: 다른 입력 스트림들이 상속받아 사용 (생성자가 protected)
- ObjectInputStream: ObjectOutputStream을 통해서 저장해 놓은 객체를 읽기위한 스트림을 만듬
- PipedInputStream: PipeOutputStream을 통해서 출련된 스트림을 읽어서 처리하기 위한 스트림을 만듬
- SequenceInputStream: 별개인 두개의 스트림을 하나의 스트림으로 만듬

## Reader
- BufferReader: 문자열을 입력 스트림을 버퍼에 담아 처리 (보통 문자열 기반의 파일)
- CharArrayReader: char의 배열로 문자 배열 처리
- FilterReader: 문자열 기반의 스트림을 처리하기 위한 추상 클래스
- FileReader: 문자열 기반의 파일을 읽기 위한 클래스
- InputStreamReader: 바이트 기반의 스트림을 문자열 기반의 스트림으로 연결
- PipeReader: 파이프 스트림을 읽음
- StringReader: 문자열 기반의 소스를 읽는다

## 파일 변경 모니터링
- Watch 관련 클래스들을 사용하여 파일을 쉽게 모니터링 할 수 있다
    - ex) WatchService
