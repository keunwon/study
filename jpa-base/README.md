# JPA 기초

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

## 영속성 컨텍스트 (entityManger)
- Entity를 영구 저장하는 환경
- Entity 생명주기
    - 비영속
    - 영속
    - 준영속
    - 삭제
- 1차 캐시
    - db 조회 시 먼저 영속성 컨텍스트의 1차 캐시에서 존재 확인
    - 존재하면 반환, 존재하지 않으면 db 조회
    - transaction 종료 시 제거
- 영속 Entity 동일성 보장 (== 비교)
- 쓰기 지연
    - 영속성 컨텍스트에는 **쓰기지연 SQL 저장소** 존재
- 더티 체킹 (변경 감지)
    - 엔티티와 스냅샷을 비교하여 변경사항이 존재하면 **쓰기지연 SQL 저장소**에 update 추가
    - 스냅샷: 영속성 컨텍스에서 처음 등록된 Entity 정보
- 플러시
    - 영속성 컨텍스트의 변경내용을 db에 반영
    - 영속성 컨텍스트를 비우지는 않음