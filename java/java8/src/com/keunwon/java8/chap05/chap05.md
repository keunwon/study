# 스트림 활용

## 프레디케이트로 필터링: filter
```java
List<Dish> vegetarianMenu = Dish.menu.stream()
                                     .filter(Dish::isVegetarian)
                                     .collect(toList());
```

## 고유 요소 필터링(중복 제거): distinct
```java
List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
numbers.stream()
       .filter(i -> i % 2 == 0)
       .distinct()
       .forEach(System.out::println);
```

## 스트림 축소: limit
```java
List<Dish> dishesLimit3 = Dish.menu.stream()
                                    .filter(d -> d.getCalories() > 300)
                                    .limit(3)
                                    .collect(toList());
dishesLimit3.forEach(System.out::println);
```

## 요소 건너뛰기: skip
```java
List<Dish> dishesSkip2 = Dish.menu.stream()
                                  .filter(d -> d.getCalories() > 3000)
                                  .skip(2)
                                  .collect(toList());
dishesSkip2.forEach(System.out::println);
```

## 매핑: map
```java
List<String> dishNames = Dish.menu.stream()
                                   .map(Dish::getName)
                                   .collect(toList());
System.out.println(dishNames);
```

## 하나의 평면화된 스트림반환: flatMap
```java
List<String> uniqueCharacters = words.stream()
                                     .map(w -> w.split(""))
                                     .flatMap(Arrays::stream)
                                     .distinct()
                                     .collect(toList());
System.out.println(uniqueCharacters);
```

## 프레디케이트가 적어도 한 요소와 일치하는지 확인: anyMatch
```java
if (menu.stream().anyMatch(Dish::isVegetarian)) {

}
```

## 프레디케이트가 모든 요소와 일치하는지 검사(noneMatch): allMatch
```java
boolean isHealthy = menu.stream()
                        .allMatch(d -> d.getCalories() < 1000);
```

## 프레디케이타가 모든 요소와 일치하지 않는지 검사(allMatch 반대): noneMatch
```java
boolean isHealthy = menu.stream()
                        .noneMatch(d -> d.getCalories() < 1000);
```

## 쇼트서킷
- 표현식에서 하나라도 거짓이라는 결과가 나오면 나머지 표현식의 결과와 상관없이 전체 결과도 거짓이 된다.
- allMatch, noneMatch, findFirst, findAny 등의 연산은 모든 스트림 요소를 처리하지 않고 결과를 반환한다.
- 원하는 요소를 찾으면 즉시 반환할 수 있다.

## 요소 검색(현재 스트림에서 임의의 요소를 반환): findAny
```java
Optional<Dish> dish = menu.stream()
                          .filter(Dish::isVegetarian)
                          .findAny();
```

## 첫 번째 요소 찾기: findFirst
```java
List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                                                           .map(x -> x * x)
                                                           .filter(x -> x % 3 == 0)
                                                           .findFirst();
```

## 리듀싱 연산
- 결과가 나올 때까지 스트림의 모든 요소를 반복적으로 처리해야 한다. 이런 질의를 리듀싱 연산이라고 한다.

## 숫자형 스트림
```java
int calories = menu.stream()                     // Stream<Dish>
                   .mapToInt(Dish::getCalories)  // IntStream
                   .sum();
```

## 객체 스트림으로 복원
```java
IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
Stream<Integer> stream = intStream.boxed();
```

## 기본값: OptionalInt
```java
OptionalInt maxCalories = Dish.menu.stream()
                                    .mapToInt(Dish::getCalories)
                                    .max();
```

## 숫자 범위: rangeClosed, range
```java
IntStream eventNumbers = IntStream.rangeClosed(1, 100) // 1 ~ 100
                                  .filter(n -> n % 2 == 0);
System.out.println(eventNumbers.count()); // 50

IntStream eventNumbers2 = IntStream.range(1, 100) // 2 ~ 99
                                   .filter(n -> n % 2 == 0);
System.out.println(eventNumbers2.count()); // 49
```

## 무한 스트림 만들기

- iterate: 연속적으로 계산
```java
Stream.iterate(0, n -> n + 2)
      .limit(10)
      .forEach(System.out::println);
```

- generate: 매번 새로운 값 생성
```java
Stream.generate(Math::random)
      .limit(5)
      .forEach(System.out::println);
```













