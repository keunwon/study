# 7. 쿼리 작성 및 최적화

## 3. MYSQL 연산자와 내장 함수
### 문자열
```sql
# 1
select * from departments where dept_no = 'd''001';
# 2
select * from departments where dept_no = 'd"001';
# 3
select * from departments where dept_no = "d'001";
# 4
select * from departments where dept_no = "d""001";
```
- 1, 2번은 SQL 표준
- 3, 4번은 MYSQL에서만 지원
### 숫자
- 숫자형 컬럼에 문자열 값을 넣으면 자동으로 숫자 형으로 변경해줌
- 문자열 컬럼에 숫자 값을 넣으면 문자열 컬럼을 숫자 컬럼으로 변경하여 비교해줌
    - 만약 문자열 컬럼에 인덱스가 걸렸있으면 이를 사용하지 못함
    - 문자열 컬럼에 알파벳과 같은 문자가 포함된 경우 숫자 값으로 변환할 수 없어 쿼리가 실해함
### 날짜
- 문자열을 자동으로 DATE, DATETIME 값으로 자동으로 변환함
### 불리언
- BOOLEAN이라는 타입이 있지만 사실은 TINYINT 타입에 대한 동의어 (쿼리 결과는 0, 1 값이 조회)
### 동등(Equal) 비교 (=, <=>)
- '=' 기호를 사용해 비교를 수행
- '<=>' 기호는 '=' 연산자와 같으며, 부가적으로 NULL 값에 대한 비교까지 수행
### REGEXP 연산자
- 문자열 값이 어떤 패턴을 만족하는지 확인하는 연산자 (정규식 표현식)
- 인덱스를 사용할 수 없음
### LIKE 연산자
- 인덱스를 사용 가능
### BETWEEN 연산자
- 크거나 같아, 작거나 같다 두 개의 연산자를 하나로 합침
### IN 연산자
- 여러개의 값에 대해 동등 비교 연산을 수행
### MYSQL 내장 함수
#### NULL 값 비교 및 대체
- IFNULL: null 이면 다른 값으로 대체하는 용도
- ISNULL: null인지 아닌지 비교
#### 현재 시간 조회(NOW, SYSDATE)
- NOW: 하나의 SQL에서 동일한 값을 가짐
- SYSDATE: 하나의 SQL에서 사용한 시점마다 현재 시간을 출력함
#### 날짜와 시간의 포멧
|지정 문자|내용|
|:---:|:---|
|%Y|4자리 년도|
|%m|2자리 숫자 표시의 월|
|%d|2자리 숫자 표시의 일자|
|%H|2자리 숫자 표시의 시|
|%i|2자리 숫자 표시의 분|
|%s|2자리 숫자 표시의 초|
#### 날짜와 시간의 연산(DATE_ADD, DATE_SUB)
- 특정 날짜에서 년도나 월일 또는 시간을 등을 더하거나 뺄 때는 DATE_ADD(), ATE_SUB() 사용
#### 문자열 처리 (RPAD, LPAD / RTRIM, LTRIM, TRIM)
- RPAD, LPAD 함수는 문자열 좌, 우에 문자를 덧붙임
- RTRIM, LTRIM 함수는 문자열의 좌, 우에 연속된 공백 문자를 제거
- TRIM 함수는 RTRIM, LTRIM 동시에 수행
#### 문자열 결합 (CONCAT)
- 여러 개의 문자열을 연결해서 하나의 문자열로 반환하는 함수 (인자의 개수는 제한이 없음)
- CONCAT_WS(): 구분자를 지정하여 문자열을 연결할 때 구분자를 추가함
#### GROUP BY 문자열을 결합 (GROUP_CONCAT)
- COUNT(), MAX(), MIN(), AVG() 등과 같은 그룹함수 중 하나이다
- GROUP BY가 없는 SQL에서 사용하면 고유한 결과들을 가지고 정렬 한 후 연결하거나 값의 구분자 설정을 함  
(= 여러 값중에서 중복을 제거하고 연결함)
- 메모리 버퍼 공간을 사용함 (기본 1KB)
- GROUP_CONCAT() 함수를 자주 사용하면 버퍼의 크기를 적절히 늘려야 함
#### 값의 비교와 대체 (CASE WHEN .. THEN .. END)
- 프로그래밍 언어에서 제공하는 SWITCH 구문과 같은 역할
#### 타입 변환 (CAST, CONVERT)
- CAST: 명시적인 타입의 변환
- CONVERT: CAST와 비슷하지만 함수의 인자 사용 규칙이 다름
#### 암호화 및 해시 함수 (MD5, SHA)
- 비대칭 알고리즘
- MD5: 128비트 해시 값 반환 (char(32) 타입 필요 = binary(16) = unhex(md5('abc')))
- SHA: 160비트 해시값 반환 (char(40) 타입 필요 = binary(20) = unhex(sha('abc')) )
- 중복될 가능성이 매우 작기때문에 binary로 크기를 줄여서 인덱로 사용할 수도 있음
#### 밴치마크 (BENCHMARK)
- SLEEP 함수와 같이 디버깅이나 간단한 함수의 성능 테스용으로 아주 유용
- 지정한 횟수만큼 반복 실행하는데 얼마나 시간이 소요됐는지가 출력
#### IP 주소 변환(INET_ATON, INET_NTOA)
- IP 주소를 일반 문자열로 저장시 VARCHAR(15) 가 필요함
- INET_ATON: 문자열로 구성된 IP 주소를 정수형으로 변환하는 함수  
- INET_NTOA: 정수형의 IP 주소를 사람이 읽을 수 있는 형태의 '.'으로 구분된 문자열로 변환하는 함수
#### COUNT
- 컬럼의 값이 null인 경우 레코드 건수에 포함되지 않음
- count 쿼리에 order by, left join에서는 제거하는게 좋음

## 4. SELECT
### 4.1. SELECT 각 절의 처리 순서
- 기본: GROUP BY -> DISTINCT 적용 -> HAVING 조건 필터링 -> ORDER BY 정렬 -> LIMIT 적용
- ORDER BY, GROUP BY 절이 있다 하더라도 인덱스를 이용해 처리할 때는 그 단계 자체가 불필요함
### 4.2. WHERE 절과 GROUP BY 절, 그리고 ORDER BY 절의 인덱스 사용
#### 인덱스를 사용하기 위한 기본 규칙
- where, order by, group by가 인덱스를 사용하려면 인덱스 컬럼의 값 자체를 변환하지 않고 그대로 사용해야함
- 인덱스 컬럼의 값을 아무런 변환 없이 B-tree에 정렬해 정함
#### WHERE 절의 인덱스 사용
- 각 조건이 명시된 순서는 중요치 않고, 그 컬럼에 대한 조건이 있는지 없는지가 중요함  
(인덱스를 구성하는 컬럼과 얼마나 좌측부터 일치하는가에 따라 달라짐)
#### GROUP BY 절의 인덱스 사용
- GROUP BY 절에 명시된 컬럼이 인덱스 컬럼의 순서와 위치가 같아야 함
- 인덱스 앞쪽에 있는 컬럼이 GROUP BY 절에 명시되지 않으면 인덱스를 사용할 수 없음
- WHERE 조건절과 달리, GROUP BY 절에 명시된 컬럼이 하나라도 인덱스가 없으면 GROUP BY 절은 전혀 인덱스를 이용하지 못함
#### ORDER BY 절의 인덱스 사용
- MySQL의 인덱스는 모든 컬럼이 오름차순으로만 정렬돼 있기 때문에 ORDER BY 절의 모든 컬럼이 오른차순이거나 내림차순일 때만 인덱스를 사용할 수 있음
#### WHERE 조건과 ORDER BY(또는 GROUP BY)절의 인덱스 내용
- WHERE 절과 ORDER BY 절이 동시에 같은 인덱스를 이용
- WHERE 절만 인덱스를 이용
- ORDER BY 절은 인덱스를 이용한 정렬이 불가능하여, 인덱스를 통해 검색된 결과 레코드를 별도의 정렬 처리 과정을 거쳐서 정렬을 수행 (Filesort)
    - WHERE 절의 조건에 일치하는 레코드의 건수가 많지 않을 때 효율적인 방식
- ORDER BY 절만 인덱스를 이용
    - ORDER BY 절의 순서대로 인덱스를 읽으면서, 레코드 한건을 WHERE 절의 조건에 일치하는지 비교해 일치하지 않을 때는 버리는 형태로 처리 (주로 많은 레코드를 조회해서 정렬해야 할 때)
#### WHERE 조건과 ORDER BY 절, 그리고 GROUP BY 절의 인덱스 사용
1. WHERE 절이 인덱스를 사용할 수 있는가?
2. GROUP BY 절이 인덱스를 사용할 수 있는가?
3. GROUP BY 절과 ORDER BY 절이 동시에 인덱스를 사용할 수 있는가?
### 4.3. WHERE 절의 비교 조건 사용 시 주의사항
#### NULL 비교
- MYSQL에서는 NULL 값이 포함된 레코드로 인덱스로 관리 (인덱스에서는 NULL을 하나의 값으로 인정해서 관리함)
#### 문자열이나 숫자 비교
- 문자열, 숫자 컬럼을 비교할 때는 반드시 그 타입을 맞춰서 상수를 사용할 것을 권장
#### DATE나 DATETIME과 문자열 비교
- DATE, DATETIME 타입의 값과 문자열을 비교할 때는 문자열을 자동으로 DATETIME 타입의 값으로 변환해서 비교를 수행
#### DATE와 DATETIME의 비교
- DATETIME 컬럼을 DATE와 비교시 DATE를 DATETIME으로 변경 (시분초는 00:00:00 으로 세팅)
- 인덱스의 사용 여부에 영향을 미치지 않음 (성능보다는 결과에 주의해야함)
### 4.4. DISTINCT
- DISTINCT 처리가 인덱스를 사용되지 못할 때는 항상 임시 테이블이 있어야 함
#### SELECT DISTINCT ...
- 인덱스를 사용하는 SELECT DISTINCT, GROUP BY에서는 같은 방식으로 처리
### 4.5. LIMIT n
- MYSQL에 존재하는 키워드
- LIMIT은 WHERE 조건이 아니기 때문에 항상 쿼리의 마지막에 실행
- GROUP BY와 함계 사용되는 경우에는 LIMIT절이 있더라도 실질적인 서버의 작업 내용을 크게 줄여주지 못함 (GROUP BY 완료 후 처리)
### 4.6. JOIN
#### JOIN 순서와 인덱스
- 인덱스 레인지 스캔
    1. 인덱스 탐색(index seek): 인덱스 조건을 만족하는 값이 저장된 위치를 찾음
    2. 인덱스 스캔(index scan): 1번에서 탐색된 위치부터 필요한 만큼 인덱스를 읽음
    3. 2번에서 읽어들인 인덱스 키와 레코드 주소를 이용해 레코드가 저장된 페이지를 가져오고, 최종 레코드를 읽어옴
- 인덱스 풀 스캔, 테이블 풀 스캔 작업은 인덱스 탐색(index seek) 과정이 거의 없지만 실제 인덱스나 테이블의 모든 레코드를 읽기 때문에 부하가 높음
- 조인 작업에서 드라이빙 테이블을 읽을 때는 인덱스 탐색 작업을 단 한번만 수행하고, 그 이후부터는 스캔만 실행함
- 조인이 수행될 때 조인되는 양쪽 테이블의 컬럼에 모두 인덱스가 없을 때만 드리븐 테이블을 풀 스캔함
- 드라이빙 테이블은 풀 테이블 스캔을 사용할 수 있어도 드리븐 테이블을 풀 테이블 스캔으로 접근하는 실행 계획은 옵티마이저가 만들어내지 않음
#### OUTER JOIN의 주의사항
- OUTER 테이블의 컬럼이 WHERE 절에 명시하면 옵티마이저가 INNTER JOIN과 같은 방법으로 처리함
#### INNER JOIN과 OUTER JOIN의 선택
- OUTER JOIN, INNER JOIN은 실제 가져와야 하는 레코드가 같다면 쿼리의 성능 차이는 거의 없음
#### FULL OUTER JOIN 구현
- MYSQL에서는 FULL OUTER JOIN 기능을 제공하지 않음
- UNION, UNION ALL을 사용함
    - UNION 사용시 내부적인 임시 테이블을 사용하므로 쿼리가 느리게 처리됌
#### JOIN과 FOREIGN KEY
- FOREIGN KEY는 조인과 아무런 연관이 없음 (FOREIGN KEY는 무결성 보장용)
#### 지연된 조인(Delayed Join)
- 지연된 조인: 조인이 하기 전에 GROUP BY, ORDER BY를 처리하는 방식
- 조인의 개수를 줄여 임시 테이블의 크기를 작게 유지함
### 4.7. GROUP BY
#### GROUP BY ... ORDER BY NULL
- MYSQL에서 GROUP BY가 불피요한 정렬 작업을 하지 않게 하려면 GROUP BY를 수행할 때 'ORDER BY  NULL' 사용해야 함
#### GROUP BY .. WITH ROLLUP
- 해당 그룹 마지막에 총 레코드 계수를 출력
- ORDER BY와 함께 사용할 수 없음
- LIMIT과 함께 사용되는 경우 결과가 조금 혼라스러움
### 4.8. ORDER BY
#### ORDER BY RAND()
- 임의값으로 정렬을 수행
#### 함수나 표현식을 이용한 정렬
- 컬럼의 연산 결과를 이용해 정렬하는 것도 가능 (연산 결과에 의한 정렬은 인덱스를 사용할 수 없음)
### 4.9. 서브 쿼리
FROM 절에 사용되는 서브 쿼리나 WHERE 절의 IN (subquery) 구문도 그다시 효율적이지 않음
#### 서브 쿼리의 제약 사항
- 서브 쿼리는 대부분의 쿼리 무장에서 사용할 수 있지만 LIMIT, LOAD DATA INFILE의 파일명에는 사용할 수 없음
- 서브 쿼리는 IN 연산자와 함께 사용할 때에는 효율적으로 처리되지 못함
- IN 연산자 안에서 사용하는 서브 쿼리는 ORDER BY와 LIMIT를 동시에 사용할 수 없음
- FROM 절에 사용하는 서브 쿼리는 상관 서브 쿼리 형태로 사용할 수 없음
### 4.10. 집합 연산
#### UNION
- UNION은 중복을 제거하는 (DISTINCT)가 포함되어있어 임시 테이블의 모은 컬럼을 UNIQUE 인덱스를 생성함 (UNION ALL, UNION의 차이는 UNIQUE 인덱스를 생성했냐 안했냐에 차이만 있음. 임시 테이블은 둘다 만듬)
#### LOCK IN SHARE MODE와 FOR UPDATE
- LOCK IN SHARE: SELECT 레코드에 대해 읽기 잠금(Shared lock), 다른 세션에서 해당 레코드를 변경하지 못하게 (읽기는 가능)
- FOR UPDATE: 쓰기 잠금(배타잠금 = Exclusive lock)을 설정, 다른 트랜잭션에서는 그 레코드를 읽기, 쓰기를 못함

## 5. INSERT
### AUTO_INCREMENT 제약 및 특성
- AUTO_INCREMENT: 하나의 테이블에서 순차적으로 자동 증가
### AUTO_INCREMENT 잠금
- 동시에 AUTO_INCREMENT를 사용할 때 AutoIncrement 잠금이라는 테이블 단위의 잠금을 사용
- AUTO_INCREMENT 값을 가져올때만 잠금이 걸렸다가 즉시 해제 (성능상의 문자가 될 때는 거의 없음)
- 롤백시 AUTO_INCREMENT 값을 되돌리지 않음
### AUTO_INCREMENT 증가 값 가져오기
- SELECT LAST_INSERT_ID() 사용
### REPLACE
- 문법은 INSERT와 크게 다르지 않음
- 저장하려는 데이터가 중복된 데이터이면 UPDATE 실행(기존에 있는 데이터 DELETE하고 수로운 레코드 INSERT), 중복되지 않으면 INSERT 수행
- 이미 존재하는 중복된 레코드의 컬럼 값을 참조할 수 없음
### INSERT INTO ... ON DUPLICATE KEY UPDATE ...
- 중복된 레코드를 DELETE하지 않고 UPDATE 함
### INSERT ... SELECT ...
- 특정 테이블로부터 레코드를 읽어 그 결과를 INSERT함

## 6. UPDATE
### 6.1. UPDATE ... ORDER BY ... LIMIT n
- UPDATE 문장에 ORDER BY절과 LIMIT 절을 동시에 사용해 특정 값으로 정렬해서 그 중에서 상위 몇 건만 업데이트하는 것도 가능함
- 마스터 슬레이어 구조에서 ORDER BY가 포함된 UPDATE 문장을 사용할 때는 주의가 필요
- 마스터 슬레이어 구조에서 마스터 역할을 하는 MYSQL 서버에서는 LIMIT 절은 있지만 ORDER BY 절이 없는 UPDATE문은 사용하지 않는게 좋음
### 6.2. JOIN UPDATE
- 두 개 이상의 테이블을 조인해 조인된 결과 레코드를 업데이트하는 쿼리를 JOIN UPDATE라고 함
- JOIN UPDATE는 조인되는 모든 테이블에 대해 읽기 참조만 되는 테이블은 읽기 잠금, 컬럼이 변경되는 테이블은 쓰기 잠금이 걸림
(실시간 웹 서비스와 같은 환경에서는 데드락을 유발할 가능성이 높아 많이 사용하지 않는 것이 좋음, 배치 or 통계용으로는 유용하게 사용할 수 있음)
- JOIN UPDATE 시 GROUP BY, ORDER BY 절을 사용할 수 없음

## 7. DELETE
### 7.1. DELETE ... ORDER BY ... LIMIT n
- 마스터 슬레이브 MYSQL에서 다른 레코드를 삭제할 가능성에 대해서 주의가 필요함
### 7.2. JOIN DELETE
- 여러 테입블을 조인해 레코드를 삭제

## 8. 스키마 조작(DDL)
#### 테이블 구조 조회
- SHOW CREATE TABLE
    - MYSQL 서버가 테이블의 메타 정보를 읽어서 이를 CREATE TABLE 명령으로 재작성해서 보여줌
#### 테이블 구조 변경
- 테이블 구조 변경시 ALTER TABLE 사용
#### 테이블 이름 변경
- RENAME TABLE 사용
- RENAME TABLE 사용 시 네임 락(Name lock)을 이용하여 RENAME TABLE 명령에 있는 모든 테이블에 대해 잠금을 걸고 진행
- InnoDB에서 *.FRM 파일 이름 변경 작업과 InnoDB 스토리지 엔진 내에서 관리되는 딕셔너리 정보의 변경이 필요
    - 많이 데이터 변경 후 RENAME TABLE 명령을 실행하면 문제가 발생할 수 있음
- 다른 데이터 베이스로 옮길때도 유용하게 사용 가능
#### 테이블의 상태 조회
- SHOW TABLE status like '<테이블 명>'\G
#### 테이블 구조 복사
- CREATE TABLE <신규 테이블 명> like <복사 대상 테이블 명>
#### 테이블 삭제
- 레코드가 많은 테이블을 삭제하는 작업은 부하가 큰 작업에 속함 (서비스 도중에 삭제 삭업은 수행하지 않는 것이 좋음)
- 테이블 삭제시 LOCK_open 이라는 잠금을 획득해야함 
### 8.3. 컬럼 변경
#### 컬럼 추가
- 컬럼 추가하는 작업은 테이블의 데이터를 새로운 테이블로 복사하는 형태로 처리 (레코드가 많으면 느려짐)
#### 컬럼 삭제
- 컬럼을 삭제하는 작업도 테이블의 데이터를 새로운 테이블로 복사하면서 컬럼을 제거하는 형태로 처리 (레코드가 많으면 느려짐)
#### 컬럼명을 변경하는 경우
- CHANGE COLUMN 사용
- InnoD에서는 임세 테이블로 데이터를 복사하는 작업이 실행되기 때문에 레코드 건수에 따라 상당히 느리게 처리
- ALTER TABLE <테이블 명> CHANGE COLUMN <변경 대상 컬럼 명> <변경 컬럼 명> <타입> <그 외 옵션>
#### 컬럼명 이외의 타입이나 NULL 여부를 변경하는 경우
- 컬럼의 타입이나 NULL 여부 등을 변경 할때 사용
- MODIFY COLUMN 사용
- 타입 변환이나 NULL 여부 변경은 테이블의 데이터를 복사하면서 구조를 변경하는 형태로 처리하기 때문에 레코드 건수에 따라 상당히 시간이 걸림
### 8.4. 인덱스 변경
#### 인덱스 조회
- show index from <테이블 명>
#### 인덱스 삭제
- DROP INDEX 사용
#### 컬럼 및 인덱스 변경을 모아서 실행
- 하나로 모아서 실행하는 방법은 각 명령으로 나눠서 실행할 때보다 빠르게 실행 (가능하다면 스키마 변경은 테이블 단위로 모아서 실행)
### 8.6. 프로세스 강제 종료
- KILL 사용
### 8.7. 시스템 변수 조회
- SHOW GLOBAL VARIABLES
- SHOW GLOBAL VARIABLES LIKE '%connections%'
- SHOW SESSION VARIABLES LIKE '%timeout%'
- SHOW VARIABLES LIKE '%timeout%'
### 8.8. 경고나 에러 조회
- SHOW WARNINGS
- 에러 메시지가 표시되지 않는 경우 -> SHOW ERRORS
### 8.9. 권한 조회
- SHOW PRIVILEGES
- 특정 사용자 권한 조회: SHOW GRANT FOR 'root'@'localhost'

## 9. SQL 힌트
MYSQL 옵티마이저에게 어떻게 데이터를 읽는 것이 최적인지 알려줌 (옵티마이저가 최적으로 데이터를 읽으려고 하지만 한계점을 가짐)
### 9.2. STRAIGHT_JOIN
- SELECT, UPDATE, DELETE 쿼리에서 여러 개의 테이블이 조인될 때 조인의 순서를 고정하는 역할을 함
- STRAIGHT_JOIN 사용해야 할 때
    - 임시 테이블(인라인 뷰 또는 파생된 테이블)과 일반 테이블의 조인
        - 임시 테이블을 드라이빙 테이블로 선정하는 것이 좋음
        - 인덱스 없는 경우에는 레코드 건수가 적은 쪽을 드라이빙으로 선택해서 먼저 읽게 하는 것이 좋음
    - 임시 테이블끼리의 조인
        - 임시 테이블은 인덱스가 없으므로 크기가 작은 테이블을 드라이빙으로 선택
    - 일반 테이블끼리의 조인
        - 양쪽에 조인 컬럼에 인덱스가 있거나 없으면 건수가 적은 테이블을 드라이빙으로 선택하는 것이 좋음
        - 조인 컬럼에 인덱스가 없는 테이블을 드라이빙으로 선택
### 9.3. USE INDEX / FORCE INDEX / IGNORE INDEX
- 복잡한 인덱스에 대해 MYSQL 옵티마이저가 적절한 인덱스를 선택하지 못할 때는 USE INDEX or FORCE INDEX 힌트로 인덱스를 사용하도록 함
### 9.4. SQL_CACHE / SQL_NO_CACHE
- MYSQL은 SELECT 쿼리에 의해 만들어진 결과를 재사용하기 위해 쿼리 캐시에 선택적으로 저장
- SQL_CACHE / SQL_NO_CACHE를 이용하여 캐시 사용 여부를 선택

## 10. 쿼리 성능 테스트
### 10.1. 쿼리의 성능에 영향을 미치는 요소
#### 운영체제의 캐시
InnoDB 스토리 엔진은 일반적으로 파일 시스템의 캐시나 버퍼를 거치지 않는 Direct I/O를 사용하므로 운영체제의 캐시가 큰 영향을 미치지 않음
#### MYSQL 서버의 버퍼 풀
InnoDB의 버퍼 풀은 인덱스 페이지, 데이터 페이지까지 캐시, 쓰기 작업을 위한 버퍼링 작업까지 처리
### 10.2. 쿼리의 성능 테스트

### 10.3. 쿼리 프로파일링
- 프로파일링을 통해서 쿼리가 처리되는 각 단계별 작업에 시간이 얼마나 걸렸는지 확인
- 명령어: SET PROFILEING = 1
