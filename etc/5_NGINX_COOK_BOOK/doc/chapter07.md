# 7. 보안 제어

## 7.1. IP 주소 기반 접근 제어
- http, stream 모듈을 이용
- 'allow', 'deny' 지시자는 http, server, location, TCP/UDP stream,server 사용할 수 있음
- deny: 사용자 요청 차단
- allow: 차단 대상인 요청의 일부에 대해 요청을 허용
- all: 모든 요청 대상으로 정책을 적용할 수 있으며 유닉스 소켓도 매개변수로 사용할 수 있음
## 7.2. 크로스 오리진 리소스 공유(CORS)
- 본래 도메인이 아닌 다른 도메인을 통해 리소스를 제공할 때 브라우저가 원할히 접근하도록 크로스 오리진 리소 공유(CORS) 정책일 필요
- CORS 접근을 허용하려면 사용자 요청의 메서드에 따라 응답 헤더를 변경해야 함
- OPTIONS 메서드는 prelight 요청으로 사용자에게 서버가 가진 CORS 정책을 응답함
- 'Access-Control-Allow-Origin' 헤더를 통해 여러 하위 도메인에서 서버 리소스 접근 가능함을 알려줌
-  prelight 요청을 매번 보내지 않도록 사용자 브라우저에 캐시하기 위해 'Access-Control-Max-Age' 헤더를 설정
## 7.3. 클라이언트 측 암호화
- ssl 모듈을 사용해 트래픽을 암호화
- 전송 중인 정보를 암호화하는 가장 일반적인 방법은 보안 전송 계층을 사용하는 방식
- TLS 프로토콜은 SSL 프로토콜보다 우선수위가 높습니다
- TLS 보안 소켓 계층을 만들며 엔직엑스는 TLS 활용해 서버와 클라이언트 사이에 주고받는 정보를 보호
## 7.4. 고급 클라이언트 측 암호화
- 'ssl_ciphers' 지시자는 TLS 표준이 제시하는 높은 수준의 암호화 알고리즘을 사용하도록 
'HIGH' 지정하면 'aNull'과 'MD5'는 사용하지 않도록 명시적으로 느낌표를 붙여 지정
- SSL 세션 캐시와 타임아웃 지시자는 엔진엑스 워커 프로세스가 지정된 시간 동안 세션 매개변수를 캐시하고 저장하도록 함
- 여러 시험 결과에 따르면 ECC 인증서는 암호화 강도가 비슷한 RSA 인증서보다 빠름
## 7.5. 업스트림 암호화
- 엔진엑스와 업스트림 서비스 간 트래픽을 암호화
- 'proxy_ssl_verify' 활성화 필요
- 'proxy_ssl_certificate', 'proxy_ssl_certificate_key' 추가 해야함
## 7.6. location 블록 보호
- 'secure link_sercert' 지시자를 사용해 secure linke를 가진 사용자에게만 리소스에 대한 접근을 허용
## 7.10. HTTPS 리다이렉션
- HTTP 트래픽을 HTTPS로 보내려면 URL을 재작성해야함
## 7.11. HTTPS 리다이렉션 - SSL 오프로딩 계층이 있는 경우
- 'X-Forwarded-Proto' 헤더를 통해 사용자의 프로토콜을 확인할 수 있으며 이 값을 활용해 리다이렉트를 함
## 7.12. HSTS
- Strict-Transport-Security 헤더를 설정해 HSTS 확장을 사용함
