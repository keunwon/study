# 11. 스토어드 프로그램
MYSQL에서 절차적인 처리를 위해 스토어드 프로그램을 이용 (스토어드 프로지서, 스토어드 함수, 트리거, 이벤트 등을 모두 아우르는 명칭)

## 1. 스토어드 프로그램의 장단점
### 1.1. 스토어드 프로그램의 장점
- 데이터 베이스의 보안 향상
    - 스토어드 프로그램 단위로 실행 권한을 부여할 수 있음
    - 입력 값의 유효성을 체크한 후 동적인 SQL 문장을 생성하므로 SQL 문법적인 취약점을 이용한 해킹은 어려움
- 기능의 추상화
- 네트워크 소요 시간 절감
- 절차적 기능 구현
- 개발 업무의 구분
### 1.2. 스토어드 프로그램의 단점
- 낮은 처리 성능
- 애플리케이션 코드의 조각화

## 2. 스토어드 프로그램의 문법
- 헤더 부분과 본문 부분으로 나눌 수 있음
### 2.2. 스토어드 프로시저
#### 스토어드 프로시저 생성 및 삭제
- CREATE PROCEDURE 명령으로 생성
- 스토어드 프로시저는 기본 반환값이 없음 (프로시저 내부에서는 RETURN 명령을 사용할 수 없음)
- 스토어드 프로시저의 파라미터는 3가지 특성 중 하나를 지님
    - IN: 입력 전용 파라미터
    - OUT: 출력 전용 파라미터 
    - INOUT: 입/출력 용도로 모두 사용
#### 스토어드 프로시저 실행
- 프로시저는 SELECT 쿼리에 사용될 수 없음
- CALL 명령어로 실행
### 2.3. 스토어드 함수
- 하나의 SQL 문장으로 작성이 불가능한 기능을 하나의 SQL 문장으로 구현해야 할 때 사용
- SQL 문장의 일부로 사용할 수 있음
#### 스토어드 함수 생성 및 삭제
- 함수 정의 부에 RETURN로 반환되는 값의 타입을 명시해야 함
- 함수 본문 마지막에 정의부에 지정된 타입과 동일한 타입의 값을 RETURN 명령으로 반환해야 함
- PREPARE, EXCUTE 명령을 이용한 프리페어 스테이트먼트를 사용할 수 없음
- ROLLBACK/COMMIT을 유발하는 SQL 문장을 사용할 수 없음
- 재귀 호출 사용할 수 없음
- 스토어드 함수 내에 프로시저를 호출할 수 없음
- 결과 셋을 반환하는 SQL 문장을 사용할 수 없음
### 스토어드 함수 실행
- SELECT 쿼리를 이용해 실행
### 2.4. 트리거
- 트리거는 테이블의 레코드가 저장되거나 변경될 때 미리 정의해둔 작업을 자동으로 실행해주는 스토어드 프로그램  
(= 데이터의 변화가 생길 때 다른 작업을 실행)
- INSERT, UPDATE, DELETE 될 때 시작되도록 설정
- 컬럼의 유효성 체크나 다른 테이블로의 복사나 백업을 위해 트리거를 자주 사용
- 트리거는 마스터 슬레이브의 데이터를 다르게 만들 가능성이 높아 특별히 주의해야 함
#### 트리거 생성
- CREATE TRIGGER 명령으로 생성
### 2.5. 이벤트
- 특정 시간에 스토어드 프로그램을 실행할 수 있는 스케줄러 기능
### 2.6. 스토어드 프로그램 본문(Body) 작성
#### BEGIN ... END 블록과 트랜잭션
- 트랜잭션을 시작할 때는 START TRANSACTION 명령을 사용해야 함
- 트랜잭션 종료할 때는 COMMIT 또는 ROLLBACK 명령을 사용
- 프로시저 or 이벤트 본문에서만 트랜잭션을 사용
#### 프로시저 내부에서 트랜잭션 완료
- 프로시저 내ㅐ부에서 COMMIT or ROLLBACK 명령으로 트랜잭션을 완료하면 외부에서 COMMIT 이나 ROLLBACK을 실행해도 의미가 없음
#### 변수
- BEGIN ... END 블록 내에서만 사용할 수 있음
- DECLARE: 스토어드 프로그램의 로컬 변수를 정의할 때 사용, 초기 기본 값을 설정 (기본 값을 설정하지 않으면 NULL로 초기화)
- SET: DECLARE로  정의한  변수에 값을 저장(할당) 