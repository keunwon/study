# 람다 표현식

## 람다란?
- 익명 함수를 단순화한 것이라고 할 수 있다.
- 이름은 없지만 파라미터 리스트, 바디, 반환 형식, 예외 리트스를 가진다.
- 특징
    - 익명: 메소드의 이름이 없다.
    - 함수: 특정 클래스의 종속되지 않는다.
    - 전달: 메소드의 인수로 전달하거나 변수로 저장할 수 있다.
    - 간결성: 익명 클래스처럼 지저분한 코드를 구현할 필요가 없다.
- 동작 파라미터를 쉽게 구현할 수 있다. (코드가 간결하고 유연해진다.)

## 함수형 인터페이스
**오직 하나의 추상메소드만 있는 인터페이스 (많은 디폴드 메소드가 있어도 추상메소드가 하나면 함수형 인터페이스이다.)**

## 박싱 & 언박싱
 - 박싱: 기본형을 참조형으로 변환
 - 언박싱: 참조형을 기본형으로 변환

## 자바 8의 대표적인 함수형 인터페이스
- Predicate<T> : T -> boolean
- Consumer<T> : T -> void
- Function<T, R> : T -> R
- Supplier<T> : () -> T
- ...


## 메소드 레퍼런스
- 기존의 메소드 정의를 재활용해서 람다처럼 전달할 수 있다.
- 람다 표현식보다 가독성이 좋다.

## 메소드 레퍼런스 예제
```java
// 메소드 레퍼런스 적용 전
Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);
BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);

// 메소드 레퍼런스 적용 후
Function<String, Integer> stringToInteger = Integer::parseInt;
BiPredicate<List<String>, String> contains = List::contains;
```

## 생성자 레퍼런스
```java
Supplier<Apple> c1 = Apple::new;
Apple a1 = c1.get();

Function<Integer, Apple> c2 = Apple::new;
Apple a2 = c2.apply(110);

BiFunction<Integer, String, Apple> c3 = Apple::new;
Apple a3 = c3.apply(10, "green");

static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
static {
    map.put("apple", Apple::new);
    map.put("orange", Orange::new);
}

public static Fruit gitveMeFruit(String fruit, Integer weight) {
    return map.get(fruit)
              .apply(weight);
}
```

## 인수가 3개인 생성자 래퍼런스 만들기
```java
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

public class Color {
    private Integer a;
    private Integer b;
    private Integer c;

    public Color(Integer a, Integer b, Integer c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Color{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}

public class TriFunctionMain {

    public static void main(String ... args) {
        TriFunction<Integer, Integer, Integer, Color> c = Color::new;
        Color color = c.apply(1,2, 3);
        System.out.println(color.toString());
    }
}
```
- 출력: Color{a=1, b=2, c=3}

## Predicate 조합
- negate(): 기존 Predicate 객체결과를 반전시킨다.
- and(): &&
- or(): ||
```java
Predicate<Apple> redAndHeavyAppleOfGreen = 
                                redApple.and(a -> a.getWeight() > 150)
                                        .or(a -> "green".equals(a.getColor()));
```

## Function 조합
- andThne(): g(f(x));
- compose(): f(g(x));

```java
Function<Integer, Integer> f = x -> x + 1;
Function<Integer, Integer> g = x -> x * 2;
Function<Integer, Integer> h1 = f.andThen(g);
Function<Integer, Integer> h2 = f.compose(g);

System.out.println(h1.apply(1));
System.out.println(h2.apply(1));
```
출력
```
4
3
```
