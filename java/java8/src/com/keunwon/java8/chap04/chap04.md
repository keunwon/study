# 스트림 소개

- 자바 API에서 새로 추가된 기능
- 멀티 스레드 코드를 구현하지 않고 병렬로 처리할 수 있다.

## 스트림 특징
- 선언형: 간결하고 가독성이 좋다.
- 조립할 수 있음: 유연하다.
- 병렬화: 성능이 좋아진다.

```java
 List<String> menuName =  Dish.menu.stream()
                                    .filter(d -> d.getCalories() > 400)
                                    .sorted(comparing(Dish::getCalories))
                                    .map(Dish::getName)
                                    .collect(toList());
System.out.println(menuName);
```
- 출력: [salmon, french fries, pizza, beef, pork]

## 스트림 알아보기
- 컬렉션의 주제는 데이터(ArrayList or LinkedList 사용할 것인지),  
    스트림은 주제는 계산(filter sorted, map) 이다.
- 정렬된 컬렉션으로 스트림을 생성하면 정령이 그대로 유지된다.
- 함수형 프로그래밍 언어에서 일반적으로 지원하는 연산과 데이터베이스와 비슷한 연산을 지원하고, 순차적 또는 병렬로 실행한다.
- 스트림 연산끼리 연결해서 커다란 파이프라인을 구성할 수 있다.
- 내부 반복을 한다.

## 스트림 컬력션 비교
- 데이터를 언제 계산하느냐가 가장 큰 차이이다.
    - 컬렉션: 현재 포함하는 모든 값을 메모리에 저장하는 자료구조이다. (컬렉션에 추가하기 전에 계산되어야 한다.)
    - 스트림: 요청할 때만 요소를 계산하는 고정된 자료구조이다. (스트림의 요소를 추가, 제거할 수 없다.)
- 스트림은 한 번만 탐색할 수 있다.
- 컬렉션은 외부 반복, 스트림은 내부 반복
