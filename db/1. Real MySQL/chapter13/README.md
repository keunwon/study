# 13. 프로그램 연동

## 1. 자바
- 자바 프로그램 언어로 MYSQL 데이터베이스 접속해서 SQL을 실행하려면 자바에서 제공하는 표준 데이터베이스 접속 API인 JDBC를 이용
- 각 DBMS 제조사가 자바에서 제정해둔 표준에 맞게 구현하므로 실제 JDBC를 이용하는 개발자는 DBMS 벤더에 신경쓰지 않고 데이터베이스 프로그램을 개발할 수 있음
### 1.1. JDBC 버전
- MYSQL의 JDBC 드라이버 이름은 Connector/J라고 함
### 1.2. MYSQL Connector/J를 이용한 개발
1. MYSQL JDBC 드라이버 클래스는 항상 'com.mysql.jdbc.Driver'
2. MYSQL 서버 접속을 위해 JDBC URL을 설정 해야함
    - 'jdbc:mysql://'는 고정, 접속하고자 하는 MYSQL 서버 정보 (IP, 도메인), 접속 포트 정보, DB 명
3. Class.forName(...)을 이용하여 MYSQL JDBC 드라이버 클래스를 동적으로 JVM으로 로딩
4. DriverManager.getConnection(...)을 이용하여 애플리케이션이 MYSQL 접속
#### SELECT 실행
- Statement 객체
    - JDBC를 사용하는 애플리케이션에서 SELECT뿐 아니라 모든 SQL과 DDL 문장을 실행하는데 필요한 객체
    - PreparedStatement 객체: 프리페어 스테이먼트를 실행 할 때 사용
    - CallableStatement 객체: 스토어드 프로시저를 실행 할 때 사용
    - 결과 셋(Result set) 반환
#### Statement와 PreparedStatement의 차이
- 쿼리 실행 단계: 쿼리 분석 -> 최적화 -> 권한 체크 -> 쿼리 실행
- PreparedStatement를 사용하면 쿼리 분석이나 최적화의 일부 작업을 처음 한번만 수행해 별도로 저장, 다음 요청되는 쿼리는 저장된 분석 결과를 재사용 함
#### 스토어드 프로시저 실행 및 다중 겨로가 셋 조회
- 자바 프로그램에서 스토어드 프로시저를 실행하려면 CallableStatement를 사용해야 함
#### 배치 처리
- JDBC 드라이버가 제공하는 addBatch() or excuteBatch()를 이용해 쿼리을 모아서 한 번에 실행
- 배치 적정 레코드 건수 = (12 * 1024) / (평균 레코드 크기)
#### 트랜젹션
- InnoDB 테이블에 대해서는 쿼리 하나하나에 대해서는 트랜잭션이 보장되지만 연속해서 실행되는 쿼리의 묶음에 대해서는 트랜잭션이 보장되지 않음
- REPEATABLE-READ인 경우 특정 트랜젹션이 유효한 동안에는 해당 트랜잭션이 처음 시작했던 시점의 데이터를 동일하게 보장해줘야 함
    - 다른 트랜잭션이 데이터를 변경했어도 변경하기 전의 데이터를 계속 쌓아둬야 함
- autocommit 상태에서 트랜잭션을 사용하려면 begin or start transaction 명령을 실행해야함 (명시적으로 트랜잭션 시작)
- AutoCommit 성능
    - MYSQL의 InnoDB 스토리지 엔진에서는 COMMIT이 실행될 때마다 테이블의 데이터나 로그파일이 디스크에 동기화되도록 작동할 때가 많음
#### Connector/J 설정 옵션
- userCompression (true / false)
    - 기본: false
    - 애플리케이션과 MYSQL 서버 사이에 전송되는 데이터를 압출할지 선택
    - 압축 시 CPU 작업이 예상외로 크기 때문에 같은 네트워크 대역 내에 있다면 압축 기능을 사용하지 않는 것이 좋음
- allowMultiQueries (true / false)
    - 기본: false
    - 여러 개의 SQL 문장을 ';'로 구분해서 한 번에 실행할 수 있도록 허용하는 기능
- allowLoadLocalInfile
    - 기본: true
    - LOAD DATA LOCAL INFILE .. 명령을 사용할 수 있도록 허용
- allowUrlInLocalInfile
    - 기본: false
    - LOAD DATA LOCAL INFILE .. 명령을 사용할 때 INFILE 옵션에 URL을 사용할 수 있도록 허용 여부
- useCursorFetch (true / false)
    - 기본: false
    - 클라이언트 커서 대신 서버 커서를 사용하도록 설정
- defaultFetchSize
    - 기본: 0
    - 서버 커서를 이용할 때 MYSQL 서버로부터 한 번에 몇 개씩 레코드를 읽어올지를 설정
    - 서버 커서를 사용하지 않고 클라이언트 커서를 사용하도록 설정
- holdResultsOpenOverStatementClose (true / false)
    - 기본: false
    - Statement가 닫혔지만 그 Statement로부터 생성된 ResultSet을 참조해야 할 때 이 옵션을 활성화
- rewriteBatchedStatements (true / false)
    - 기본: false
    - 여러 개의 INSERT 문장을 한꺼번에 실행할 때 PreparedStatement의 addBatch() 함수로 누적된 레코드를 하나의 INSERT 문장으로 변환해서 실행하는 기능을 활성하는 옵션
- useServerPrepStmts (true / false)
    - 기본: false
    - 서버 PreparedStatement를 사용할지 말지를 설정하는 옵션
    - 기본적으로는 PreparedStatement는 클라이언트 PreparedStatement로 작동
- traceProtocl (true / false)
    - 기본: false
    - Connector/J가 MYSQL 서버와 통신하기 위해 주고받는 패킷을 Log4J를 이용하여 로깅할 수 있음
#### 대량 데이터 가져오기
- JDBC 표준에서 제공하는 Statement.setFetchSize() 라는 함수는 MYSQL 서버로부터 SELECT된 레코드를 클라이언트인 애플리케이션으로 가져올 때 한번에 가져올 레코드의건수를 설정하는 역할
#### 복제 MYSQL을 위한 ReplicationDriver
- JDBC Driver는 데이터를 변경하는 쓰기 쿼리는 마스터 MYSQL에서 실행, 읽기 쿼리는 슬레이브 MYSQL에서 실행
- 일반적인 'com.mysql.jdbc.Driver'가 아닌 'com.mysql.jdbc.RelicationDriver' 클래스를 이용
- ReplicationDriver는 단순위 읽기와 쓰기 쿼리를 마스터 슬레이브로 나눠서 전송 및 실행해주는 역할 말고도 여러개의 슬레이브를 지정해서 돌아가면서(Round-robin 방식)쿼리를 실행할 수 있게 해주는 기능도 지원
- 쿼리가 마스터 MYSQL에 실행되기를 원한다면 쿼리를 실행하기 전에 'Connection.setReadOnly(false)'
- 'Connection.setReadOnly(true)'를 실행하면 ReplicationDriver는 읽기 전용 쿼리로 간주하고, 슬레이브 MYSQL로 쿼리를 전송
- ReplicationDriver를 사용하면 ReplicationConnection 객체가 생성되는데, 이는 Connection 객체의 래퍼클래스(Wrapper Class)로서 하나의 ReplicationConnection은 항상 마스터 MYSQL에 대한 커넥션 1개와 슬레이브 MYSQL에 대한 커넥션 1개를 동시에 가짐
    - 둘 중에서 반드시 하나만 사용할 수 있으므로 어느 쪽 하나의 커넥션은 불필요하게 낭비되는 것일 수도 있음
#### MYSQL 클러스트 (NDB)를 위한 ReplicationDriver
- MYSQL 클러스트 환경에서 ReplicationDriver를 사용할 수 있음
- Master/Slave와 NDB 클러스트의 차이점은 커넥션 스트링의 프로토콜 부분이 조금 다름
- MYSQL 클러스트 환경에서 ReplicationDriver를 사용할 때는 읽기 전용 여부를 변경하는 것은 의미가 없음
- MYSQL 클러스트로 접속하면 커넥션이 한꺼번에 여러 개씩 생성되는 것이 아니라 현재 커넥션이 문제가 있어 정상적으로 작동하지 않을 때만 다른 SQL 노드로 새로운 커넥션이 생성
- MYSQL 클러스터에서 ReplicationDriver를 로드 밸런스나 커넥션 페일오버(Fail-over) 용도로 사용