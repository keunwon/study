package com.myshop.order.command.domain.service;

import com.myshop.common.model.Money;
import com.myshop.member.command.application.NoMemberException;
import com.myshop.member.command.domain.MemberRepository;
import com.myshop.member.command.domain.model.Member;
import com.myshop.member.command.domain.model.MemberId;
import com.myshop.order.command.domain.RuleDiscounter;
import com.myshop.order.command.domain.model.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CalculateDiscountService {
    private final RuleDiscounter ruleDiscounter;
    private final MemberRepository memberRepository;

    public Money calculatedDiscount(OrderLine orderLine, String memberId) {
        Member member = findMember(memberId);
        return ruleDiscounter.applyRules(member, List.of(orderLine));
    }

    private Member findMember(String memberId) {
        return memberRepository.findById(new MemberId(memberId))
                .orElseThrow(NoMemberException::new);
    }
}
