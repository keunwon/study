package com.myshop.order.command.application.service;

import com.myshop.member.command.domain.MemberRepository;
import com.myshop.member.command.domain.model.Member;
import com.myshop.order.command.domain.exception.OrderNotFoundException;
import com.myshop.order.command.domain.model.Order;
import com.myshop.order.command.domain.model.OrderNo;
import com.myshop.order.command.domain.model.ShippingInfo;
import com.myshop.order.command.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChangeOrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void changeShippingInfo(OrderNo id, ShippingInfo newShippingInfo, boolean useNewShippingAddressAsMemberAddress) {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        order.changeShippingInfo(newShippingInfo);

        if (useNewShippingAddressAsMemberAddress) {
            Member member = memberRepository.findById(order.getOrderer().getMemberId()).get();

        }
    }
}
