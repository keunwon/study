# JPA 기초

## JPA 구동 방식
1. Persistence가 설정 정보 조회 (META-INF/persistence.xml)
2. Persistence가 -> EntityMangerFactory 생성
3. EntityMangerFactory -> EntityManger생성

## JPA 주의 사항
- JPA에서 데이터 변경을 하려면 transaction 안에서 작업해야한다.
- EntityMangerFactory는 하나만 생성해서 전체 공유
- EntityManger는 스레드 공유X

## JPQL 
- SQL을 추상화한 객체 지향 쿼리 언어 제공 (SQL에 의존 X)
- JPQL은 엔티티 객체를 대상으로 쿼리, SQL은 데이터베이스 테이블을 대상으로 쿼리
