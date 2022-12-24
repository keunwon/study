# 15. 성능 튜닝

## 15.1. 로드 드라이버를 이용한 시험 자동화
- 아파치의 로드 드라이버인 제이미터, 로커스트, 개틀링, 그 외 HTTP 부하 도구를 사용
- 천천히 동시 접속자를 늘려가면서 실제 서비스 환경의 사용 패턴과 흡사한 부하를 애뮬레이션하고 개선이 필요한 부분을 확인
## 15.2. 클라이언트의 연결 유지
- 'keepalive_requests', 'keepalive_timeout' 지시자를 사용해 단일 연결이 허용하는 요청 수를 늘리고 유휴 연결이 유지되는 시간을 제어
## 15.3. 업스트림 서버와의 연결유지
- 업스트림 블록에 'keepalive' 지시자를 사용해 업스트림 서버와 이미 맺어진 연결이 재사용되도록 함
- 업스트림 서버와 맺은 연결을 유지하면 연결 과정에 소요되는 시간을 아낄 수 있으며 워커 프로세스가 유휴 연결을 활용할 수 있음
## 15.4. 응답 버퍼링
- 프록시 버퍼 설정을 변경하여 엔진엑스가 메모리에 업스트림 서버의 응답을 저장하도록 함
- 'proxy_buffer_size': 업스트림 서버 응답의 앞부분인 헤더 정보를 저장할 버퍼의 크기를 지정
- 'proxy_buffers': 매개변수로 버퍼 개수와 버퍼 크기를 지정
- 'proxy_busy_buffers_size'
    - 엔진엑스는 클라이언트의 요청에 빠르게 반응하기 위해 업스트림 서버의 응답을 완전히 수신하지 않더라도 클라이언트로 응답을 시작하는데, 이때 사용하는 버퍼의 크기를 지정
    - 기본값은 proxy_buffer_size or proxy_buffers에 지정된 값의 두배
- 불필요하게 큰 버퍼 용량을 할당하면 엔진엑스가 동작할 때 필요한 서버의 메모리 자원을 많이 점유함
- 버퍼 관련된 설정은 업스트림 서버의 응답 특성이 파악된 특정 'location 블록 단위'로 사용해 최적의 성능을 발휘하도록 해야 함
## 15.5. 접근 로그 버퍼링
- 접근 로그를 저장할 버퍼 크기와 버퍼 플러시 시간을 설정
- 'buffer' 매개 변수: 디스크에 기록하지 전에 쌓아둘 수 있는 메모리 버퍼의 크기를 나타냄
- 'flush' 매개 변수: 메모리 버퍼에 쌓은 로그가 디스크에 기록되기 전까지 기다릴 수 있는 최대 시간을 나타냄
- 'gzip' 매개 변수: 로그를 압축해 저장할 수 있으며 압축률에 따라 레벨 1 ~ 9까지 지정 가능 (레벨이 높을 수록 속도는 느리지만 압축률이 좋아짐)
## 15.6. 운영체제 튜닝
- 커널 설정 중 net.core.somaxconn 값을 확인
    - 엔진엑스가 요청을 처리하도록 커널이 '큐잉' 할 수 있는 연결의 최대 개수를 나타냄
    - 512보다 큰 값으로 지정하면 엔진엑스 설정의 listen 지시자의 blocking 매개변수가 같은 값을 갖도록 설정해야 함
    - net.core.somaxconn 값을 설정해야 하는 시점은 커널이 커널 로그에 명시적으로 값을 설정하라고 메시지를 남길 때
    - 보통은 값을 조정할 필요는 없음
- 시스템 성능 튜닝 시 파일 디스크립터의 수를 늘리는 방법을 널리 사용
    - 대량의 연결을 처리하기 위해 커널의 sys.fs.file_max 옵션을 조정해 시스템의 파일 디스크립터 수를 늘리거나, 
    시스템 사용자를 통해 실행되는 경우 /etc/security/limits.conf 파일의 내용을 조정할 수 있음
    - 시스템 설정을 변경하면 엔진엑스의 설정인 'worker_connections', 'worker-rlimit_nofile' 지시자의 매개 변수도 커널 설정 변경에 맞춰 늘려야 함
- 임시 포트를 활성화
    - 엔진엑스가 리버스 프록시나 로드 밸런서로 동작하면 모든 업스트림 연결이 응답 트래픽을 받을 임시 포트를 염, 
    이때 필요한 임시 포트의 최대 개수는 시스템 설정에 따라 달라짐
    - 'net.ip4.ip_local_port_range' 값 확인 필요
- 커널 튜닝을 시작하는 시점은 커널 로그 메시지에서 커널 설정값 조정이 필요하다는 메시지를 발견하거나 엔진엑스 오류 로그에서 명시적으로 관련 내용이 언급될 때임을 기억해야 함