package com.myshop.order.command.domain.service;

import com.myshop.member.command.application.NoMemberException;
import com.myshop.member.command.domain.MemberRepository;
import com.myshop.member.command.domain.model.MemberId;
import com.myshop.order.command.domain.RuleDiscounter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateDiscountServiceTest {

    @Test
    public void member_thenExceptionShouldBeThrown() {
        // given
        MemberRepository stubRepo = mock(MemberRepository.class);
        RuleDiscounter ruleDiscounter = (member, orderLines) -> null;

        when(stubRepo.findById(new MemberId("noId"))).thenReturn(Optional.empty());
        CalculateDiscountService calculateDiscountService = new CalculateDiscountService(ruleDiscounter, stubRepo);

        // when, then
        assertThrows(NoMemberException.class, () ->
                calculateDiscountService.calculatedDiscount(null, "noId"));
    }
}
