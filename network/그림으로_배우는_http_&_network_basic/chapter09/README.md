# HTTP에 기능을 추가한 프로토콜

## HTTP의 병목 해소
- Ajax
- Comet
- SPDY

## WebSocket
웹 서버와 클라이언트와 세션을 맺으면 서버와 클라이언트 어느 쪽에서도 레퀘스트를 할 수 있다.  

- 서버 푸시 기능
    - 서버에서 클라이언트 쪽으로 데이터를 푸시 (서버 -> 클라이언트)
- 통신량 절약
    - HTTP에 비해 접속 횟수, 헤더 사이즈가 적어진다.
- 핸드쉐이크/리퀘스트
    - WebSocket 통신 시 첫 1회는 HTTP로 통신을 해야한다.
    - HTTP의 Upgrade 헤더 필드를 사용하여 프로토콜을 변경하는 핸드쉐이크를 실사한다. (HTTP -> WebSocket)
    - Sec-WebSocket-Key에는 핸드쉐이크에 필요한 키가 저장
    - Set-WebSocket-Protocol에는 사용하는 서브 프로토콜이 저장 (서브 프로토콜은 WebSocket을 여러 커넥션으로 구분하고 싶을 때 이름을 붙여 사용)
- 핸드쉐이크/리스폰스
    - 핸드쉐이크/리퀘스트를 하면 응답으로 101 (Switching Protols)로 상태코드가 반환
    - Sec-WebScoekt-Accept는 Sec-WebSocket-Key의 값이 저장