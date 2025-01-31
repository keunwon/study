# 결과를 전달하는 HTTP 상태코드

## HTTP 상태코드
||클래스|설명|
|:---:|:---:|:---:|
|1XX|Informational|리퀘스트를 받아들여 처리중|
|2XX|Success|리퀘스트를 정상적으로 처리했음|
|3XX|Redirect|리퀘스트를 완료하기 위해 추가 동작이 필요|
|4XX|Client Error|서버는 리퀘스트 이해 불가능|
|5XX|Server Error|서버는 리퀘스트 처리 실패|

## HTTP 대표적인 상태코드
- 2XX 성공 (Success): 리퀘스트 정상
    - 200 [OK]:  리퀘스트를 서버가 정상으로 처리 
    - 204 (No Content): 리스폰스의 엔티티 바디를 포함하지 않음
    - 206 (Partial Content): Range에 의해 범위내에 엔티티가 반환
- 3XX 리다이렉트 (Redirect): 리퀘스트를 처리하기 위해서는 브라우저에서 적절한 조치를 취해야 하는 경우
    - 301 (Moved Permanently): 요청한 리소스가 영구적으로 이동 (도메인 변경, url 변경 등)
    - 302 (Found): 요청한 리소스가 임시적으로 이동
    - 303 (See Other): 요청한 리소스를 다른 URI의 GET 메소드를 통해 얻어야 할 때
    - 304 (Not Modified): 리퀘스트 시 리소스의 엑세스는 허락하지만 조건이 충족되지 않았을 때 (리스폰스 바디 내용은 없음)
    - 307 (Temporary Redirect): 302 (Found)와 동일한 의미를 가지고 있지만 첫 리퀘스트의 메소드와 두번째 리퀘스트의 메소드와 동일해야 함
- 4XX 클라이언트 에러 (Client Error)
    - 400 (Bad Request): 리퀘스트 요청이 잘못되었을 때 (파라미터 오류 등 ..)
    - 401 (Unauthorized): 리퀘스에 사용자 인증에 실패한 경우
    - 403 (Forbidden): 리퀘스한 리소스의 엑세스가 거부된 경우
    - 404 (Not Found): 리퀘스한 리소스가 서버에 없는 경우, 서버 측에서 리퀘스트를 거부한 경우
- 5XX 서버 에러 (Server Error)
    - 500 (Internet Server Error): 리퀘스트를 처리하는 중 에러 발생
    - 503 (Service Unavaliable): 일시적으로 서버가 과부하 상태이거나 점검 중