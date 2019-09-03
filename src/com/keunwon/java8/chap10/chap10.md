# null 대신 Optional

## null 때문에 발생하는 문제
- 에러의 근원이다(NPE 자바에서 흔히 발생하는 에러이다.)
- 중첩된 null 확인 코드를 추가해야 하므로 코드 가독성이 떨어진다.
- 아무런 의미가 없다.
- 자바 철학에 위배된다. (자바는 모든 포인트를 숨긴다. 하지만 null 포인터가 있다)
- 형식 시스템에 구멍을 만든다. (모든 레퍼런스에 null을 할당할 수 있다. 이런 식으로 null이 할당되기 시작하면 null이 어떤 의미로 사용되었는지 알 수 없다.)

## Optional 클래스
- 값이 있으면 Optional 클래스는 값을 감싼다.
- 값이 없으면 Optional.empty 메소드로 Optional을 반환한다.
- 모든 null 레퍼런스를 Optional로 대치하는 것은 바람직하지 않다.

## 빈 Optional
```java
Optional<Car> optCar = Optional.empty();
```

## null이 아닌 값으로 Optional 만들기
```java
Optional<Car> optCar = Optional.of(car); // car가 null이면 NPE 발생한다.
```

## null값으로 Optional 만들기
```java
Optional<Car> optCar = Optional.ofNullable(car); // car가 null이면 빈 Optional 객체를 반환한다.

```
