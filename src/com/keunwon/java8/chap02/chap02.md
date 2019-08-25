# 동작 파라미터화 코드 전달하기

## 동작 파라미터화
- 자주 바뀌는 요구 사항에 효과적으로 대응할 수 있다.
- 아직은 어떻게 실행할 것인지 결정하지 않은 코드 블록을 의미한다. (코드 블록의 실행은 나중으로 미뤄진다.)

## 전략 디자인패턴
```java
public interface ApplePredicate {
    boolean test(Apple apple);
}

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}

public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}

public static void main(String ... args) {
    List<Apple> greenApples1 = filter(inventory, new AppleGreenColorPredicate());
    System.out.println(greenApples1);

    List<Apple> heavyApples1 = filter(inventory, new AppleHeavyWeightPredicate());
    System.out.println(heavyApples1);

    ...
```
- 각 알고리즘을 캡슐화하여 런타임에 알고리즘을 선택하는 기법이다.

## 익명 클래스
```
위의 전략 디자인 패턴을 이용하여 동작 파라미터화하였지만 새로운 동작을 전달하려면 ApplePredicate를 구현한 클래스를 정의해야 한다. 
이러한 작업은 상당히 번거로운 작업이다. 하지만 익명 클래스를 통해서 어느 정도 해결할 수 있다.
```
- 지역 클래스와 비슷한 개념이다.
- 이름이 없는 클래스이다.
- 익명 클래스를 사용하면 클래스 선언과 인스턴스화를 동시에 할 수 있다.
- 즉석에서 구현을 만들어 사용할 수 있다.
```java
List<Apple> greenApples2 = filter(inventory, new ApplePredicate() {
    @Override
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor());
    }
});

List<Apple> heavyApples2 = filter(inventory, new ApplePredicate() {
    @Override
    public boolean test(Apple apple) {
        return 150 > apple.getWeight();
    }
});
```

## 익명 클래스 단점
- 많은 공간을 차지한다.
- 많은 프로그래머가 익명 클래스의 사용에 익숙하지 않다.
- 코드가 장황하다.

```
동작 파라미터를 이용하여 다양한 동작을 쉽게 수행할 수 있지만 코드를 지저분하게 구현해야 했다.(전략 패턴, 익명 클래스)
하지만 자바 8 이후부터는 람다라는 개념을 통해서 깔끔하게 코드를 구현할 수 있다.

람다는 다음 장에서 ...
```