package com.keunwon.object.chapter02.movie.step02;

import com.keunwon.object.chapter02.money.Money;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
