package com.myshop.order.infra;

import com.myshop.common.model.Money;
import com.myshop.member.command.domain.model.Member;
import com.myshop.order.command.domain.RuleDiscounter;
import com.myshop.order.command.domain.model.OrderLine;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DroolsRuleDiscounter implements RuleDiscounter {

    @Override
    public Money applyRules(Member member, List<OrderLine> orderLines) {
        return Money.ZERO;
    }
}
