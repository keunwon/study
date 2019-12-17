package com.keunwon.object.chapter14.step02;

import com.keunwon.object.chapter14.DateTimeInterval;

import java.util.Collections;
import java.util.List;

public class FixedFeeCondition implements FeeCondition {

    @Override
    public List<DateTimeInterval> findTimeIntervals(Call call) {
        return Collections.singletonList(call.getInterval());
    }
}
