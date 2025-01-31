package com.keunwon.java8.chap05;

import com.keunwon.java8.common.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class PuttingIntoPractice {

    public static void main(String ... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
                                                        new Transaction(raoul, 2012, 1000),
                                                        new Transaction(raoul, 2011, 400),
                                                        new Transaction(mario, 2012, 710),
                                                        new Transaction(mario, 2012, 700),
                                                        new Transaction(alan, 2012, 950));

        // Q1. 2011년에 일어난 모든 트렌잭션을 찾아 값을 오름차순 정렬
        List<Transaction> year2011 = transactions.stream()
                                              .filter(s -> s.getYear() == 2011)
                                              .sorted(comparing(Transaction::getValue))
                                              .collect(toList());
        System.out.println(year2011);

        // Q2. 거래자가 근무하는 모든 동시를 중복 없이 나열
        List<String> cityList = transactions.stream()
                                          .map(Transaction::getTrader)
                                          .map(Trader::getCity)
                                          .distinct()
                                          .collect(toList());
        System.out.println(cityList);

        Set<String> citySet = transactions.stream()
                                          .map(transaction -> transaction.getTrader().getCity())
                                          .collect(toSet());
        System.out.println(citySet);

        // Q3. 케임브리지에서 근무하는 모든 거래자를 찾아서 오름차순 정렬
        List<Trader> cambridge = transactions.stream()
                                             .map(Transaction::getTrader)
                                             .filter(trader -> trader.getCity().equals("Cambridge"))
                                             .collect(toList());
        System.out.println(cambridge);

        // Q4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환
        String traderStr = transactions.stream()
                                       .map(transaction -> transaction.getTrader().getName())
                                       .distinct()
                                       .sorted()
                                       .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);

        String traderStr2 = transactions.stream()
                                        .map(transaction -> transaction.getTrader().getName())
                                        .distinct()
                                        .sorted()
                                        .collect(joining());
        System.out.println(traderStr2);

        // Q5. 밀라노에 거래자가 있는가?
        boolean isMilan = transactions.stream()
                                      .map(Transaction::getTrader)
                                      .anyMatch(s -> s.getCity().equals("Milan"));
        System.out.println(isMilan);

        // Q6. 케임브리지에 거주하는 거래자의 모든 트랜젹션값을 출력
        transactions.stream()
                    .filter(s -> "Cambridge".equals(s.getTrader().getCity()))
                    .map(Transaction::getValue)
                    .forEach(System.out::println);

        // Q7. 전체 트랜잭션 중 최대값
        Optional<Integer> max = transactions.stream()
                                            .map(Transaction::getValue)
                                            .reduce(Integer::max);
        System.out.println(max);

        Optional<Integer> max2 = transactions.stream()
                                             .map(Transaction::getValue)
                                             .max(Integer::compareTo);
        System.out.println(max2);

        // Q8. 전체 트랜잭션 중 최솟값
        Optional<Integer> min = transactions.stream()
                                            .map(Transaction::getValue)
                                            .reduce(Integer::min);
        System.out.println(min);

        Optional<Integer> min2 = transactions.stream()
                                             .map(Transaction::getValue)
                                             .min(Integer::compareTo);
        System.out.println(min2);
    }
}
