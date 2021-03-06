# 17. 응급 처리

## 1. 서버 과부하
### 1.1. 운영체제의 유틸리티를 이용해 장비의 부하 확인
- 운영체제의 유틸리티를 이용해 하드웨어 상태를 확인하는 것이 좋음
### 1.2. MYSQL 서버의 에러 로그 확인
- 긴급한 상황일 수록 에러 로그의 마지막 부분은 반드시 확인해 보는 것이 좋음
### 1.3. MYSQL 서버의 프로세스 리스트 확인
- MYSQL 서버가 어떤 쿼리를 실행하고 있는지, 어떤 프로세스가 특히 오랜 시간 동안 실행되고 있는지 확인
- show processlist 명령을 이용하여 프로세스의 정보를 볼 수 있음
### 1.4. MYSQL 서버의 최대 커넥션 설정 확인
- MYSQL 서버에서 허용하는 최대 커넥션의 수를 터무니없이 크게 설정해 뒀다면 MYSQL 서버는 동시에 많은 요청을 받아 제대로 처리하지 못하는 현상이 발생 할수 있음
- max_connections 시스템으로 커넥션 수 조정 (동적 변수)
### 1.5. MYSQL 서버의 슬로우 쿼리 분석
``` shell
# 쿼리가 많이 실행된 순서 대로 슬로우 쿼리를 분석해서 결과를 출력
shell > mysqldumpslow -r -s c slow-query.log > parsed_slowquery.log

# 쿼리가 오랜 시간동안 잠금을 가지고 있덨던 순서대로 술로우 쿼리를 분석
shell > mysqldumpslow -r -s l
```
- 슬로우 쿼리 로그의 통계를 확인
### 1.7. 각 원인별 초치
#### 튜닝되지 않은 쿼리
- 높은 부하를 유발하는 쿼리 순서대로 튜닝을 하는 것이 좋음
- 인덱스를 생성하는 형태로 튜닝을 진행하는 것이 좋음
#### 잘못 사용된 쿼리
- 애플리케이션의 버그나 사용자의 실수로 트랜잭션이 종료되지 않고 잠금이 계속 유지되면서 서비용 쿼리에 영향을 미치는 것을 의미
#### 실제 사용자가 많은 경우
- MYSQL 서버를 더 투입해서 부하를 분산하는 것이 좋음
- 지금 당장 바이너리 로그의 동기화 방식이나 InnoDB 트랜잭션 커밋의 동기화 방식을 조금 느슨한 형태로 변경해서 디스크의 병목을 줄여 서비스를 유지하는 것도 한 가지 방법
    - set global sync_binlog = 0;
    - set global innodb_flush_log_at_trx_commit = 2;

## 2. MYSQL 서버 셧다운
- 특별히 명령이 없는데도 MYSQL 서버가 재시작된다면 MYSQL 서버의 에러 로그 파일을 확인하고 'Segmentation fault' 메시지가 있는지 확인해 보는 것이 좋음
- Segmentation fault 일으키고 종료하면 데이터 파일이나 InnoDB 로그 파일이 손상

## 3. MYSQL 복구
### 3.1. MYISAM
- MYISAM 테이블에서는 잘못된 조작으로 인해 인덱스나 데이터 파일이 손상될 수 있음
- REPARI TABLE 명령으로 MYISAM의 데이터 파일이나 인덱스 파일을 다시 복구하는 것이 가능
- 데이터 페이지가 손상됐다면 테이블의 일부를 복구하지 못할 수도 있음
### 3.2. InnoDB
- 하드웨어나 운영체제의 문제가 아니라면 InnoDB 데이터 파일이나 로그 파일이 손상될 가능성은 상당히 낮은 편, 문제 생기면 복구하기가 쉽지 않음
- InnoDB 데이터 파일은 기본적으로 MYSQL 서버가 시작될 때 항상 자동 복구를 수행, 자동으로 복구될 수 없는 손상이 있다면 자동 복구를 멈추고 MYSQL 서버는 종료
- Innodb_force_recovery 옵션을 추가해 MYSQL 서버를 시작해야 함
    - InnoDB의 로그 파일이 손상됐다면 6으로 설정하고 MYSQL 서버 기동
    - InnoDB 테이블의 데이터 파일이 손상되었다면 1로 설정
    - 만약 어떤 부분의 문제인지 알 수 없다면 1부터 6까지 변경하면서 MYSQL을 재시작
#### Innodb_force_recovery 옵션
- 1 (SRV_FORCE_IGNORE_CORRUPT)
    - InnoDB의 테이블 스페이스의 데이터나 인덱스 페이지에서 손상된 부분이 발견되어도 무시하고 MYSQL 서버를 시작
    - 에러 로그에 'Database page corruption on disk or failed' 메시지가 출력될 때 대부분 이 경에 해당
    - 이때는 mysqldump 프로그램이나 SELECT INTO OUTFILE ... 명령을 이용해 덤프해서 데이터베이스를 다시 구축
- 2 (SRV_FORCE_NO_BACKGROUND)
    - 메인 스레드를 시작하지 않고 MYSQL 서버를 시작
    - InnoDB의 메인 스레드가 언두 데이터를 삭제하는 과정에서 장애가 발생하면 이 모드로 복구
- 3 (SRV_FORCE_NO_TRX_UNDO)
    - 커밋되지 않은 트랜잭션의 작업을 롤백하지 않고 그대로 놔둠
    - 커밋되지 않고 종료된 트랜잭션을 계속 그 상태로 남아 있도록 MYSQL 서버를 시작하는 모드
- 4 (SRV_FORCE_NO_IBUF_MERGE)
    - InnoDB 스토리지 엔진이 인서트 버퍼의 내용을 무시하고 강제로 MYSQL 시작
- 5 (SRV_FORCE_NO_UNDO_LOG_SCAN)
    - InnoDB 엔진이 언두 로그를 모두 무시하고 MYSQL을 시작 함
    - MYSQL 종료되던 시점에 커밋되지 않았던 작업도 모두 커밋된 것처럼 처리되므로 실제로는 잘못된 데이터가 데이터베이스에 남는 것이라고 볼 수 있음
- 6 (SRV_FORCE_NO_LOG_REDO)
    - InnoDB 스토리지 엔진의 리두 로그가 손상되면 MYSQL 서버가 시작되지 못함
    - 이 복구 모드로 시작하면 InnoDB엔진은 리두 로그를 모두 무시하고 MYSQL 시작
    - 이때는 기존의 InnoDB의 리두 로그를 삭제하고 MYSQL 서버를 시작하는 것이 좋음

## 4. 테이블 메타 정보의 파일
- alter or rename 명령으로 테이블의 구조를 변경하는 작업은 가끔 트랜잭션을 보장하지 못할 때가 있음
- InnoDB 테이블은 MYSQL 구조상 테이블의 정보를 파일로도 관리하지만 InnoDB 스토리지 엔진에서 자체적으로 가지고 있는 딕셔너리 정보에서도 이중으로 관리

## 5. 복제가 멈추었을 때
- 복제가 구축된 MYSQL 서버에서 쿼리나 시트템적인 오류로 인해 슬레이브의 복제가 진행되지 않고 멈추는 문제가 자주 발생 할 수 있음
- 슬레이브 MYSQL은 마스터 데이터와 동기화를 위해서 'SQL스레드', 'IO 스레드' 2개의 스레드를 사용
    - 두 스레드가 상태가 정사이여야 복제가 정상적으로 진행
    - 'show slave status' 명령어를 통해서 서버의 복제 상태를 확인
#### IO 스레드가 멈췄을 때
- MYSQL로부터 바이너리 로그를 가져오는 일을 담당하는 스레드
- 슬레이브 MYSQL이 마스터 MYSQL 서버에 접속하지 못하거나 로인을 못하고 있음을 의미
#### SQL 스레드가 멈추었을 때
- SQL 스레드는 IO 스레드가 가져온 바이너리 로그를 슬레이브 MYSQL 서버에서 재실행한느 역할을 수행
- 쿼리 중에 오류가 발생하면 SQL 스레드는 더는 진행하지 않고 멈춤
- show slave status의 last_error 컬럼에 표시되는 일반적으로 문제의 쿼리도 같이 표시되어 쉽게 원인을 찾을 수 있음

## 6. 경고 메시지로 에러 로그 파일이 커질 때
- 만약 에러 로그나 슬로우 쿼리 로그가 너무 커서 삭제하거나 새로운 파일로 대체할 때 로그파일을 삭제만 하면 로그 파일이 생성되지 않거나 다시 기록되지 않을 수 있음
    - 에러 로그파일이나 슬로우 로그 파일을 삭제 후, 반드시 'FLUSH LOGS'를 해야함

## 7. 바이너리 로그로 디스크가 꽉 찬 경우
- 바이너리 로그 파일이 너무 많아서 삭제할 때는 그냥 운영체제의 명령으로 파일을 삭제해서는 안됌
- 바이너리 로그 파일의 목록은 MYSQL 서버에 의해 별도로 관리되므로 MYSQL 서버에 로그인해서 다음 명령으로 삭제하는 것이 좋음
``` shell
mysql > PURGE BINARY LOGS BEFORE '2011-07-23 10:00:00'; 
```

## 8. 마스터 MYSQL 서버에서 함수 생성 오류
- 바이너리 로그가 활성화된 마스터 MYSQL 서버에서 스토어드 함수나 이벤트는 'DETERMINISTIC', 'no sql', 'reads sql data' 옵션 가운데 하나로 정의했을 때만 문제없이 생성

## 9. MYSQL의 DB명 변경
- rename database라는 명령어는 잠재된 문제점이 많아서 이제는 지원되지 않음
- 데이터베이스 명을 변경해야 한다면 rename table 명령을 이용해 기존 DB의 테이블을 모두 새로운 DB로 옮기는 것이 유일한 방법

## 10. DB의 테이블 생성 DDL만 덤프
- mysqldump의 --no-data 옵션을 이용하면 데이터를 제외하고 나머지 모두를 덤프 받을 수 있음
- create table 문장에서 'AUTO_INCREMENT=xxx' 옵션ㅇ르 제거하고 사용하는 것이 좋음
```
shell > mysqldup -u<계정명> -p --routines --triggers --database db1 db2 > schema.sql
```

## 12. 테이블이나 레코드의 잠금 해결

## 13. InnoDB의 잠금 대기 시간 초과
- MYSQL 서버의 'innodb_lock_wait_timeout' 시스템 설정에 정의 된 시간만큼 기다림
- 시간이 초과되면 에러 메시지를 출력하고 쿼리를 강제 종료, 트랜잭션 자체가 롤백된 것이 아님
- 만약 테이블 잠금고 있을때는 계속 대기

## 14. MYSQL 서버의 호스트 잠금
- 각 클라이언트는 호스트별 에러 카운터의 값이 설정 (max_connect_errors <- 독적 변수임)
- 에러 카운터 값이 초과하면 접속을 차단
- 에러 로그 카운터를 초기화
``` shell
shell > mysqladmin -u root -p flush-hosts
mysql > flush hosts;
```
