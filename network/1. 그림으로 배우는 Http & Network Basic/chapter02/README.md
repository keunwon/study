# 간단한 프로토클 HTTP

## HTTP
- 리소스가 요청을 하는 쪽이(Request) 클라이언트, 리소를 제공해주는(Response) 쪽이 서버
- Stateless 하다 (상태관리를 하지 않음)
    - 상태관리가 필요할 때는 쿠키, session, token 등을 사용 한다.
- HTTP 메소드
    - GET: 리소스 획득
    - POST: 엔티티 전송
    - PUT: 파일 전송
    - HEAD: 메시지 헤더 취득
    - DELETE: 파일 삭제
    - OPTIONS: 제공 메소드 문의
    - TRACE: 경로 조사
    - CONNECT: 프록시 터널링 요구

## HTTP 지속 연결 (Keep-Alive)
지속 연결은 어느 한 쪽이 커넥션 종료를 하지 않는 이상 TCP연결을 계속 유지를 한다.  
TCP 커넥션의 연결(3Way-Handshaking)과 종료(4Way-Handshaking)에 대한 오버헤드를 줄여준다.  
HTTP1.0에서는 정식 스펙이 아니였지만 HTTP1.1에서부터는 기본 스펙으로 사용을 하고있다.  

하지만 장점만 존재하지는 않는다. HTTP의 지속 연결은 많은 리소스 요청 시 TCP 커넥션이 계속 연결되어 있어 문제가 발생 할수 있다. (서버 자원 문제)  
각 상황에 맞게 적적히 Keep-Alive의 타임아웃 시간을 설정해야한다.

## HTTP 파이프라인화
HTTP 지속 연결을 통해서 다중 request를 보낼 수 있도록 한다.  
HTTP 지속 연결 전에는 하나의 request의 response가 수신되어야지 다음 request를 요청을 할 수 있었다.

