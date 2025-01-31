# JPA 기본편
김영한님의 **자바 ORM 표준 JPA 프로그래밍 - 기본편**을 참고하여 작성하였습니다.  
이해한 내용을 바탕으로 작성하여 주관적인 의견이 포함될 수 있습니다.

## ORM (Object-relational-mapping)
- 객체 관계 매핑
- 패러다임이 다른 객체와 관계형 데이터베이스를 매핑해주는 역할은 한다.
    - 관계형 데이터베이스에 종속성이 줄어든다.
    - 개발자는 핵심 비지니스에 집중할 수 있다.
    - 좀 더 객체지향적인 코드를 작성할 수 있다. (= 유지보수성, 생산성 증가)

## JPA 구동 방식
1. Persistence가 설정 정보 조회 (META-INF/persistence.xml)
2. Persistence가 -> EntityMangerFactory 생성
3. EntityMangerFactory -> EntityManger생성
4. EntityManger를 통해서 커넥션 사용

## JPA 주의 사항
- JPA에서 데이터 변경을 하려면 transaction 안에서 작업해야한다.
- EntityMangerFactory는 하나만 생성해서 전체 공유
- EntityManger는 스레드 공유X

## JPQL 
- SQL을 추상화한 객체 지향 쿼리 언어 제공 (SQL에 의존 X)
- JPQL은 엔티티 객체를 대상으로 쿼리, SQL은 데이터베이스 테이블을 대상으로 쿼리

## 영속성 컨텍스트
- Entity를 영구 저장하는 환경
- Entity 생명주기
    - 비영속
    - 영속
    - 준영속
    - 삭제
- 1차 캐시
    - db 조회 시 먼저 영속성 컨텍스트의 1차 캐시에서 존재 확인
    - 존재하면 반환, 존재하지 않으면 db 조회
    - 동일한 transaction 사용
- 영속 Entity 동일성 보장 (== 비교)
- 쓰기 지연
    - 영속성 컨텍스트에는 **쓰기지연 SQL 저장소** 존재
- 더티 체킹 (변경 감지)
    - 엔티티와 스냅샷을 비교하여 변경사항이 존재하면 **쓰기지연 SQL 저장소**에 update 추가
    - 스냅샷: 영속성 컨텍스에서 처음 등록된 Entity 정보
- 플러시
    - 영속성 컨텍스트의 변경내용을 db에 반영
    - 영속성 컨텍스트를 비우지는 않음

## Entity 매핑
- @Entity
    - JPA 관리하는 클래스
    - 기본 생성자 필수 (public or protected)
- @Column
    - name: 테이블 컬럼과 매핑
    - insertable, updateable: 등록, 변경 가능 여부
    - nullable(DDL): null 허용 여부 (DDL 생성 시 not null 제약 조건 추가)
    - unique(DDL): 한 컬럼에 간단히 유니크 제약 조건 사용 (@Table의 uniqueConstraints 사용하는게 좋음)
    - columnDefinition(DDL): 컬럼 정보를 직접 세팅 ex) varchar(100) not null
    - length(DDL): 문자열 길이 제한, String 타입에만 사용
- @Enumerated
    - ORDINAL: 순서 ex) 0, 1, 2 .. 
    - STRING: 이름

## 기본키 매핑
- @Id (직접 생성)
- @GeneratedValue (자동 생성)
    - AUTO: DB방언에 따라 자동
    - IDENTITY: 데이터 베이스에 위임 ex) Mysql의 auto_increment
    - SEQUENCE: 시퀀스 사용

## 양방향 매핑 규칙 / 연관관계의 주인 (Owner)
- 두 객체 중 하나의 객체만 연관관계의 주인을 정해야함
- 연곽관계의 주인만이 외래 키 관리 (등록, 수정)
- 주인이 아닌쪽은 읽기만 가능
- 주인은 mappedBy 사용 X
- 주인이 아니면 mappedBy 속성으로 주인 지정
- 외래키가 있는 곳을 주인으로 정해라
- N : 1 관계에서 N 테이블이 연관관계의 주인
- 양방향 매핑 시 양쪽 객체에 값을 세팅해야함
- 연관관계 편의 메소드를 생성하자
