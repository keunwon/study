# 2. 고성능 부하분산

## 2.1. HTTP 부하분산
- upstream 블록과 http 모듈을 이용해 HTTP 서버 간에 부하를 분산 함
- 'server' 지시자 뒤에 지정한 'weight' 매개 변수값은 가중치를 설정
## 2.2. TCP 부하분산
- upstream 블록과 stream 모듈을 이용해 TCP 서버 간에 부하를 분산
- stream 모듈을 이용한 설정은 'stream.conf.d' 라는 별도의 폴더를 생성해 저장하는 편이 좋음
- http vs stream 차이점
    - http는 OSI의 7계층 애플리케이션에서 동작
    - stream은 4계층인 전송계층에서 동작
    - stream 모듈은 패킷의 전달 경로 결정과 부하분산에 더 중점을 둠
## 2.3. UDP 부하분산
- upstream 블록을 stream 모듈에서 사용해 UDP 서버 간에 부하를 분산 함
- 부하분산이 적용된 서비스에서 클라이언트와 서버가 패킷을 여러 번 주고받아야 한다면 'reuseport' 매개변수를 사용
    - 오픈 VPN, 음성 인터넷 프로토콜(VoIP), 가상 데스크톱 환경, DTLS 서비스가 대표적
## 2.4. 부하분산 알고리즘
- 라운드 로빈
    - 기본값으로 설정된 부하분산 방법
    - 업스트림 풀에 저장된 서버의 순서에 따라 요청을 분산
    - 가중치로 더 높은 정숫값이 지정된 서버는 더 많은 요청을 받음 (가중치는 단순히 가중치 평균의 통계적 확률에 따라 계산)
- 리스트 커넥션
    - 엔진엑스와의 연결 수가 가장 적은 업스트림 서버로 요청을 전달해 부하를 분산
    - 리스트 커넥션 알고리즘 지시자는 'least_conn'
- 리스트 타임
    - 엔진엑스 플러스에서만 사용할 수 있는 방법
    - 리스트 커넥션과 마찬가지로 연결 수가 가장 적은 업스트림 서버로 요청을 전달, 이중 가장 응답 시간이 빠른 서버를 우선시 함
    - 가장 복잡한 부하분산 알고리즘으로 높은 성능이 필요한 애플리케이션에 적합
- 제네릭 해시
    - 주어진 텍스트 문자열 혹은 요청이나 런타임 변수를 사용해 해시를 정의
    - 수신한 요청의 해시를 생성하고 업스트림 서버 선택에 활용해 부하를 분산
    - 제네릭 해시는 요청을 처리할 서버를 선택하는데 깊이 개입해야 할 때나 캐시가 있을 확률이 높은 서버로 요청을 전달하고 싶을 때 무척 유용 (서버가 업스트림 풀에서 추가되거나 제거되면 해시 처리된 요청이 재분배된다는 점을 주의, consistent 옵션 매개변수를 사용하면 재분배의 영향을 최소화할 수 있음)
- 랜덤
    - 엔진엑스가 업스트림 풀에 지정된 서버를 임의로 선택해 요청을 전달하며 이때 업스트림 서버에 지정된 가중치를 고려
    - 기본 알고리즘은 'least_conn' 지정
- IP 해시
    - HTTP에 대해서만 동작하는 방법으로, IP 주소를 이용해 해시를 생성
    - 세션 상태가 관리되지 않는 환경이나 세션 상태가 애플리케이션의 공유 메모리를 통해 공유되지 않는 경우에 유용
## 2.5. 스티키 쿠기(엔진엑스 플러스)
- sticky cookie 지시자를 사용해 엔진엑스 플러스가 쿠키를 생성하고 추적
## 2.6. 스티키 런(엔진엑스 플러스)
- sticky learn 지시자로 업스트림 애플리케이션 생성한 쿠기를 찾아내어 추적
- 업스트림 서버의 응답 헤더 중 Set-cookie 헤더에서 쿠키를 찾아 추적함
## 2.7. 시티키 라우팅(엔진엑스 플러스)
- sticky 지시자를 route 매개변수와 함께 사용 (사용자 요청을 특정 업스트림 서버로 보내려면 경로에 대한 매핑 정보를 포함하는 변수를 사용)
## 2.8. 커넥션 드레이닝(엔진엑스 플러스)
- 엔진엑스 플러스 API로 drain 매개변수를 보내 엔진엑스가 추적 중이 아닌 새로운 연결을 더는 업스트림 서버로 보내지 않도록 설정
- 엔진엑스 server 지시자에 drain 매개변수를 넣은 후 엔진엑스 플러스 설정을 리로드해서 제어 가능
## 2.9. 수동적인 헬스 체크
- 동작에 문제가 없는 업스트림 서버만 사용하려면 엔진엑스 부하분산 설정에 헬스 체크 매개변수를 추가 함
    - max_fails: 헬스 체크의 최대 실패 횟수 (기본값: 1회)
    - fail_timeout: 실패에 대한 타임아웃 값 (기본 값: 10초)
- HTTP, TCP, UDP 부하분산 구성의 server 지시자에 설정
## 2.10. 능동적인 헬스 체크(엔진엑스 플러스)
- http 모듈을 사용하는 경우 location 블록에 'health_check' 지시자를 사용해 능동적으로 상태를 확인
## 2.11. 슬로 스타트(엔진엑스 플러스)
- server 지시자에 'slow_start' 매개변수를 사용해 점진적으로 사용자 연결을 늘려나갈 시간 범위를 지정하고 업스트림 서버 부하분산 풀에 각 서버가 투입되도록 함
- hash, hash_ip, random 부하분산 방식에서는 사용할 수 없음