## 2. 설치와 설정

## 3. 서버 설정
### 3.1. 설정 파일의 구성
- [mysqld]: MYSQL 서버만을 위한 설정
- [mysql]: MYSQL 클라이언트
- [client]
    - MYSQL서버를 제외한 대부분의 클라이언트 프로그램이 공유하는 영역
    - mysql 백업 프로그램인 mysqldump 프로그램 등은 모두 클라이언트 프로그램의 분류에 속함
- [mysqldump]
    - 백업
- [mysqld_safe]
    - MYSQL 서버가 비정상적으로 종료됐을 때 재시작하는 일만 하는 프로세스
    - 일반적으로 잘 사용되지 않음
### 3.2. MYSQL 시스템 변수의 특징
- 각 시스템 변수는 'show variables' or 'show global variables' 명령으로 확인 가능
### 3.3. 글로벌 변수와 세션 변수
- MYSQL의 시스템 변수는 적용 범위에 따라 글러벌 변수와 세션 변수로 나뉘며, 때로는 동일한 변수 이름이 존재
- 글로벌 범위의 시스템 변수는 하나의 MYSQL 서버 인스턴스에서 전체적으로 영향을 미치는 시스템 변수를 의미
- 세션 범위의 시스템 변수는 MYSQL 클라이너트가 MYSQL 서버에 접속할 때 기본적으로 부여하는 옵션의 기본값을 제어하는 데 사용
### 3.5. my.cnf 설정 파일
#### [mysqld]
- server-id
    - MYSQL이 내부적으로 자기 자신을 식별하는 아이디 값
    - 그룹 내에서 유일한 값이여야 함
- user
    - MYSQL이 설치된 서버의 운영체제 계정을 입력
- basedir
    - MYSQL 서버의 홈 디렉터리를 명시
- datadir
    - 데이터 파일이 저장되는 디렉토리
- tmpdir
    - 정렬이나 그룹핑과 같은 처리를 위해 내부적으로 임새 테이블을 생성
    - tmpdir은 내부 임시테이블의 데이터 파일이 저장되는 위치
    - 쿼리가 종료되면 자동으로 삭제
- character-set-server, collation-server
    - MYSQL 서버의 기본 문자집합을 설정
- default-storage-engine
    - MYSQL 서버 내에서 기본적으로 사용할 스토리지 엔진을 정의
- skip-name-resolve
    - 클라이언트가 MYSQL 서버에 접속하면 MYSQL 서버는 해당 클라이언트가 접속이 허용된 사용자인지 확인하기 위해  
    클라이언트 IP 주소를 이용해 역으로 DNS명을 가져와야 하는데 이러한 작업을 SKIP
        - 빠른 접속을 위해 이러한 역 DNS 검색 (Reverse lookup)을 하지 않음
- event-scheduler
    - 이벤트 스케줄러 사용 여부
- sysdate-is-now
    - sysdate()를 now()함수와 동일하게 사용
- back_log
    - 수많은 클라이언트가 한꺼번에 MYSQL 서버로 접속을 시도하면 MYSQL 서버의 인증을 거칠 때까지 기다리게 되는데,  
    이때 몇 개까지의 커넥션을 대기 큐에 담아 둘지 결정하는 설정
- max_connections
    - MYSQL 서버가 최대한 허용할 수 있는 클라이언트의 연갤 수를 제한하는 설정
    - max_connections 옵션은 동적으로 변경할 수 있으므로 커넥션이 부족하다면 그때 변경
- thread_cache_size
    - thread_cache_size 설정 변수는 최대 몇 개까지의 스레드를 스레드 풀에 보관할지 결정
    - MYSQL 서버가 스레드를 캐시해 두려면 메모리가 필요하기 때문에 가능하다면 이 값을 크게 늘리기 보다는 클라인트 프로그램이 커넥션 풀을 사용할 수 있게 개발하는 것이 좋음
- wait_timeout
    - 지정된 시간 동안 아무런 요청 없이 대기하는 경우 MYSQL 서버는 해당 커넥션을 강제로 종료해 버림
    - 운영체제의 keep-alive 설정 등도 함께 확인하는 것이 좋음
- max_allowed_packet
    - 모든 클라이언트 패킷이 max_alloowed_packet 설정에 지정된 크기 이하일 것으로 간주함
- max_heap_table_size
    - 메모리 스토리지 엔진을 사용한 메모리 테이블은 힙 테이블이라고 함
    - 빠른 처리를 위해 메모리 테이블을 사용한다면 값을 적절히 변경해야 함
    - 메모리 테이블이라 함은 스토리지 엔진을 메모리로 지정해 CREATE TABLE로 생성한 테이블뿐만 아니라 MYSQL 사용자의 쿼리를 처리하기 위해 내부적으로 생성하는 임시 테이블도 포함
- tmp_table_size
    - 임시 테이블의 최대 크기를 제어하는 설정
- sort_buffer_size
    - 인덱스를 이용하거나 별도의 메모리나 디스크 공간에 결과를 저장해 정렬을 수행 할 수 있음
    - sort_buffer_size는 인덱스를 사용할 수 없는 정렬에 메모리 공간을 얼마나 할당할지 결정하는 설정값
    - sort_buffer_size가 커질수록 정렬이 빨라지지는 않음
- join_buffer_size
    - 적절한 조인 조건이 없어서 드리븐테이블의 검색이 풀 테이블 스캔으로 유도되는 경우에 조인 버퍼가 사용 (Using Buffer)
- read_buffer_size
    - 풀 테이블 스캔이 발생하는 경우 사용하는 버퍼
- read_rnd_buffer_size
    - MYSQL에서 인덱스를 사용해 정렬할 수 없을 경우 정렬 대상 데이터의 크기에 따라 Single-pass or Two-pass 알고리즘 중 하나 사용
    - 정렬 대상 데이터가 큰 경우에는 Two-pass 알고리즘 사용
    - 정렬이 완료되면 다시 한번 데이터를 읽어야함
    - 읽어야 할 데이터 레코드를 버퍼링하는데 필요간이 read_rnd_buffer_size
- query_cache_size, query_cache_limit
    - 쿼리 캐시에 관련된 캐시의 크기를 설정하는 설정 값
- transaction-isolation
    - 트랜잭션 격리 수준을 설정값
- innodb_buffer_pool_size
    - InnoDB 스토리지 엔진에서 가장 중요한 옵션
    - InnoDB 스토리지 엔진의 버퍼 풀은 디스크의 데이터를 메모리에 캐싱함과 동시에 데이터의 변경을 버퍼링하는 역할을 수행
    - 일반적으로 50 ~ 80%까지의 수준에서 Innodb_buffer_pool_size를 설정
- innodb_additional_mem_pool_size
    - InnoDB 스토리지 엔진에서 각 테이블의 메타 정보나 통계 정보를 내부적으로 별도로 가지고 있음
    - 메타 정보나 통계 정보가 저장되는 공간의 크기
    - 테이블 개수가 1000개 미만이라면 16MB, 이상이면 32MB 정도로 설정하면 문제 없이 작동할 것
- innodb_file_per_table
    - InnoDB 스토리지 엔지을 사용하는 테이블은 '.ibd' 확장자로 생성
    - 테이블 스페이스라는 개념 사용
    - 값을 1로 선택
        - 테이블 단위로 각각 1개씩 데이터 파일과 테이블 스페이스를 생성해 데이터를 저장
    - 값을 0으로 선택
        - 하나의 테이블 스페이스에 모든 테이블의 데이터가 저장
        - 테이블이 삭제되어도 테이블 스페이스가 점유하던 공간을 운영체제로 반납하지 않음
- innodb_data_home_dir
    - InnoDB 스토리지 엔진을 사용하는 테이블에 대한 데이터 파일이 저장될 위치를 설정 (일반적으로 MYSQL의 기본 데이터 저장 경로와 동일하게 설정)
- innodb_log_group_home_dir
    - Redo 로그는 사람이 읽을 수 있는 로그가 아니라 MYSQL 서버가 갑자기 종료됐거나 했을 때 이전의 잘못된 내용이나 종료되지 않은 트랜잭션을 InnoDB 스토리지 엔진이 다시 복구하기 위한 용도로 사용
    - 데이터의 변경 이력을 별도의 파일에 순차적으로 기록해 두는데 이를 '트랜잭션 로그' or 'Redo 로그'라고 함
- innodb_log_buffer_size
    - Redo로그를 기록하기 위해서 임시로 메모리 로그를 버퍼링을 하는데, 이때 버퍼의 크기를 지정
- innodb_log_file_size, innodb_log_files_group
    - Innodb_log_file_size: Redo 로그 파일 하나의 크기
    - Innodb_log_files_group: 파일의 개수
    - 데이터가 빈번하게 실행된다면 전체 로그의 크기를 4GB 정도로 늘려서 설정하는 것이 좋음
- innodb_lock_wait_timeout
    - InnoDB에서 잠금 획득을 위해 최대 대기할 수 있는 시간을 설정
    - 테이블 잠금을 기다리는 경우에는 타임 아웃이 적용되지 않음
    - SET으로 대기시간을 늘리거나 줄일 수 있음
- innodb_flush_log_at_trx_commit
    - InnoDB에서 트랜잭션이 커밋될 때마다 Redo 로그를 디스크에 플러시할지 결정하는 옵션
- innodb_flush_method
    - 운영체제는 디스크에 데이터를 쓰는 작업은 크게 '운영체제의 버퍼로 기록' or '버퍼의 내용을 디스크로 복사' 2단계로 나뉨
        - 2단계 작업을 동시에 같이 실행하는 방식을 동기(sync) io라고 함
        - 1단계 2단계 작업을 각각 다른 시점에 실행하는 방식을 비동기(async) io라고 함
        - 데이터가 변경되면 그와 동시에 파일의 변경 일시와 같은 메타 정보도 변경되는데, 데이터의 파일의 메타데이터를 한꺼번에 변경하는 방식을 'fsync'라고 하며, 파일의 메마 정보를 무시하고 순수하게 사용자의 데이터만 변경하는 방식을  'fdatasync'라고 함
        -  운용체제의 버퍼 기록 단계를 생략하고 바로 사용자의 테이블 디스크로 쓰는 경우도 있는데, 이를 다이렉트(direct) io라고 함
    - innodb_flush_method는 innoDB스토리지 엔진이 로그파일과 데이터 파일을 어떤 방식으로 디스크에 기록하고 동기화할지 결정
    - innodb의 버퍼 풀은 운영체제의 캐시보다 더 체계적이고 효울적으로 사용하기 때문에 운영체제의 캐시가 별로 도움이 안됌
    - 보통은 O_DIRECT 설정하면 운영체제의 불피요한 캐시를 막고 캐시를 위한 메모리 낭비도 제거 가능
- innodb_old_blocks_time
    - 
- key_buffer_size
    - MYISAM 스토리지 엔진에서 가장 중요한 설정값
    - MYISAM의 키 버퍼는 인덱스에 대해서만 캐시 역할을 함
    - InnoDB의 버퍼 풀만큼 크게 메모리를 할당해야서는 안됌
    - 전체 메모리에 30 ~ 50% 정도
- general_log, general_log_file
    - MYSQL에는 DBMS 서버에서 실행되는 모든 쿼리를 로그 파일로 기록하는 기능이 있는데, 이 로그를 쿼리 로그 or 제너럴 로그라고 함
    - 글로벌 동적 변수로 개선
- slow-query-log, long_query_time, slow_query_log_file
    - MYSQL에서는 지정된 시간 이상으로 쿼리가 실행되는 경우 해당 쿼리를 별도의 로그 파일로 남김 (슬로우 로그)
    - 반드시 활성화 필요, 글로벌 동적 변수
    - slow-query-log는 슬로우 쿼리 로그를 활성화활지 비활성화할지 결정하는 설정 값
    - long_query_time 초 단위 값을 설정
    - slow_query_log_file 로그 경로
- log_slow_amdin_statements
    - ALTER TABLE ... 등과 같은 DDL 문장의 슬로우 쿼리 로그 기록 여부를 설정
- log-bin, max_binlog_size, expire_logs_days
    - MYSQL 복제를 구축하려면 반드시 바이너리 로그를 사용해야 함
    - 바이너리 로그는 마스터 MYSQL 서버에서만 기록, 슬레이브 MYSQL 서버는 마스터에서 기록된 바이너리 로그를 가져와서 재실항하는 형태로 마스터와 슬레이브 간의 데이터를 동기화
    - log-bin 옵션에는 바이너리 로그의 파일명을 설정
    - max_minlog_size는 최대 바이너리 로그 파일의 크기를 제한
    - expire_log_days는 쓸모없는 오래된 바이너리 로그를 며칠까지 보관할지 결정
- binlog_cache_size
    - 바이너리 로그의 내용도 즉시 디스크에 기록하는 것이 아니라 메모리의 임시 공간에 잠깐 버퍼링했다가 디스크로 기록
    - 버퍼링용 메모리의 크기를 설정하는 변수
    - 커넥션 별로 생성되므로 너무 크게 할당하지 않는 것이 좋음
- log-bin-trust_function-creators
    - 바이너리 로그가 활성화된 MYSQL에서 스토어드 함수를 생성하는 경우 에러 메시지를 내보내고 생성이 실패할 수 잇음
    - 설정 값을 활성화해 두면 위와 같은 경고를 무시하고 스토어드 함수를 생서할 수 있음
- sync_binlog
    - MYSQL에서 성능 문제가 발생하는 요소(특히 디스크 성능)는 InnoDB Redo log의 동기화, 바이너리 로그의 동기화
    - 읽기부하는 주로 인덱스나 데이터 파일에서 발생, 쓰기 부하는 바이너리 로그와 Redo 로그에서 발생
    - '1' 설정
        - 트랜잭션이 커밋 될 때마다 바이너리 로그를 디스크에 플러시(동기화)함
        - 바이너리 로그의 손실은 줄어들지만 MYSQL 서버의 성능은 떨어짐
    - '0' 설정
        - 디스크에 기록을 하지만 MYSQL 서버가 플러시하지 않고 운영체제의 버퍼까지만 기록하고 즉시 처리를 완료
        - 보통 운영체제에서 4 ~ 5초 간격으로 데이터를 플러시
        - MYSQL 서버의 성능은 올라가지만 마스터 MYSQL 서버가 다운되거나 장애가 발생했을 때 바이너리 로그가 손실되어  
        마스터, 슬레이브 간 데이터가 달라질 가능이 커짐
- relay-log, relay_log_purge
    - 마스터 MYSQL에서 바이너리 로그를 생성한다면 슬레이브 MYSQL에서는 마스터의 바이너리 로그를 읽어와서 릴레이 로그 파일을 생성
    - relay-log는 릴레이브 로그의 경로
    - replay_log_purge는 더 이상 필요없는 릴레이 로그를 자동으로 삭제
- log-slave-updates
    - MYSQL 서버 복제 구성에서 하나의 MYSQL 서버가 슬레이브이면 동시에 마스터가 될 수 있음
    - 다른 마스터로부터 바이너리 로그를 가져와서 재실행되는 쿼리가 자기 자신의 바이너리 로그에 기록되게 할지 여부를 결정
    - 복잡한 복제를 구성하는 경우에는 반드시 필요한 설정
- read_only
    - MYSQL 복제가 사용된 경우, 데이터 변경은 마스터 MYSQL 실행, 단순히 조회만 하는 트랜잭션은 슬레이브 장비에서 실행
    - 슬레이브 MYSQL 서버는 읽기 전용으로 만들때 사용하는 설정 옵션
    - 글로벌 동적 변수라서 바로 읽기 전용을 해제 가능
#### [client] 설정 그룹
- MYSQL 서버 접속을 위한 소켓 파일의 경로나 MYSQL 서버의 포트 정보 등을 설정

## 5. MYSQL 복제 구축
### 5.1. 설정 준비
- MYSQL 서버가 중복뒤지 않는 server-id 값을 가지고 있어야 함
- 마스터 MYSQL 서버는 반드시 바이너리 로그가 활성화돼 있어야 함
    - 바이너리 로그 동기화 방법을 지정하려면 sync_binlog 설정
- 슬레이브 MYSQL에서는 릴레이션 로그가 생성
    - replay_log, replay_log_purge 옵션을 추가로 설정
    - 읽기 전용 read-only 옵션도 사용
### 5.2. 복제 계정 준비
- 슬레이브 MYSQL이 마스터로부터 바이너리 로그를 가져오려면 MYSQL 서버에 접속해 로그인을 해야하는데, 이때슬레이브가 사용할 계정을 복제용 계정이라고 함
- 계정에는 REPLICATION SLAVE 권한을 가지고 있어야함
### 5.3. 데이터 복사
- MYSQL Enterpriese backup or mysqldump 등을 이용해 데이터를 슬레이브 MYSQL로 복사
### 5.4. 복제 시작
- 복제를 시작하는 명령은 CHANGE MASTER 명령으로, mysqldupmp로 백업받은 파일의 헤더 부분에서 해당 명령얼 참조할 수 있음
- START SLAVE 명령을하면 동기화 시작

## 6. 권한 관리
### 6.1. 사용자의 식별
- MYSQL의 사용자는 사용자의 계정뿐 아니라 사용자의 접속 지점(호스트 명이나 IP주소)도 계정의 일부
    - 예시 1) 'svc_id'@'192.168.0.10'
    - 예시 2) 'svc_id'@'%'
### 6.2. 권한
#### 권한 그룹
- ALTER: 테이블 스키마 변경
- ALTER ROUTIME: 스토어드 프로시저 변경
- CREATE: 테이블 생성
- CREATE ROUTIME: 스토어드 프로시저 생성
- CREATE TEMPORARY TABLES: 임시 테이블 생성
- CREATE VIEW: 뷰 생성
- DELETE: 레코드 삭제
- DROP: 테이블 삭제
- EVENT: 이벤트 생성 및 변경
- EXCUTE: 프로시저 실행
- INDEX: 인덱스 생성 및 변경
- INSERT: 레코드 INSERT
- LOCK TABLES: 테이블 잠금 (레코드 잠금이 아님)
- SELECT: 레코드 SELECT
- SHOW VIEW: 뷰의 생성 스크립트 조회
- TRIGGER: 트리거 생성과 삭제
- UPDATE: 레코드 UPDATE
- GRANT OPTION: 권한 부여 옵션
- ALL [PRIVILEGES]: GRANT OPTION 권한을 제외한 여기에 명시된 모든 권한
- CREATE USER: 사용자 생성
- FILES: MYSQL 서버에서 파일 접근 (SELECT INTO OUTFILE ..., LOAD DATA IN ...)
- PROCESS: 프로세스의 목록과 각 프로세스의 실행쿼리 조회
- RELOAD: 로그 및 권한 또는 테이블 정보에 대한 FLUSH 명령 사용 권한
- REPLICATION_CLINET: SHOW MASTER [SLAVE] STATUS 명령어 사용
- REPLICATION_SLAVE: 복제를 위한 바이너리 로그를 읽어갈 수 있는 권한 (복제용 사용자 계정이 가져야 하는 권한)
- SHOW_DATABASES: DB의 목록 조회 권한
- SHUTDOWN: MYSQL 서버의 종료 권한
- SUPER: [설명](#super)
- USAGE: 아무런 권한이 없는 사용자 생성
#### SUPER
    - SUPER 권한은 root 사용자와 같은 권한을 의미하지 않지만 특정한 상황에서는 제한을 넘어서서 뭔가 작업을 할 수 있는 권한
    - read_only 설정이지만 SUPER 권한은 데이터를 변경 가능
    - max_connections 제한으로 더는 커넥션을 생성할 수 없어도 SUPER 권한은 커넥션 1개를 더 생성 가능
    - 일반 사용자나 서비스 계정에는 SUPER 권한을 부여하지 않는 것이 좋음
#### 전역 권한과 로컬 권한
    - MYSQL에서 각 권한의 범위는 전역이나 로컬 중 하나이며, 전역 권한은 DB나 테이블 단위의 권한이 아니라 MYSQL의 전역적으로 작동하는 권한을 의미
#### MYSQL의 사용자 계정 초기화
    - root 사용자 계정은 기본적으로 모든 권한을 가지고 있는 것으로 초기화되어 있음
#### 권한 부여
##### 글로벌 권한
```  shell
mysql > grant super on *.* to 'user'@'localhost';
```
##### DB 권한
``` shell
mysql > grant event on *.* to 'user'@'localhost';
mysql > grant event on employees.* to 'user'@'localhost';
```
##### 오브젝트 권한
``` shell
mysql > grant select, insert, update, delete on *.* to 'user'@'localhost';
mysql > grant select, inesrt, update, delete on employees.* to 'user'@'localhost';
mysql > grant select, insert, update, delete on employees.department to 'user'@'localhost';
```
#### MYSQL 백업용 계정
```
create user 'backup'@'127.0.0.1' identified BY 'backuppass' password expire never;

grant lock tables, reload, replication client, select, show databases, show view
on *.* to 'backup'@'127.0.0.1' identified by 'backuppass';

flush privileges ;
```
- 백업 계정은 최소 권한을 가지도록 별도로 준비하는 것이 좋음
#### MYSQL의 서비스용 계정 준비
```
grant file, process, reload, replication client, replication slave, show databases
on *.* to 'svc_user'@'%' identified by 'pass';

grant alter, alter routine, create, create routine, create view, drop, index, show view, create TEMPORARY TABLES, delete, execute, insert, lock tables, select, update
on 'svc_db_name'.* to 'svc_user'@'%';
```
- 서비스 계정은 백업용 계정과 같이 스크립트나 애플리케이션의 설정 파일에 아이디와 비밀번호가 암호화 되지 않고 기록될 때가 많기 때문에 전체 데이터베이스에 대한 권한은 허용하지 않는 것이 좋음
- 서비스용 계정은 최손한으로 권한 부여
