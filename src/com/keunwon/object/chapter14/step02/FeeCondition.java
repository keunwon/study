package com.keunwon.object.chapter14.step02;

import com.keunwon.object.chapter14.DateTimeInterval;

import java.util.List;

public interface FeeCondition {
    List<DateTimeInterval> findTimeIntervals(Call call);
}
