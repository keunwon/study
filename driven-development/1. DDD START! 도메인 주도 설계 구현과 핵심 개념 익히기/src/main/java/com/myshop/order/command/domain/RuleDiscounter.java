package com.myshop.order.command.domain;

import com.myshop.common.model.Money;
import com.myshop.member.command.domain.model.Member;
import com.myshop.order.command.domain.model.OrderLine;

import java.util.List;

public interface RuleDiscounter {
    public Money applyRules(Member member, List<OrderLine> orderLines);
}
