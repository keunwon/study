package com.myshop.order.infra;

import com.myshop.member.command.domain.model.MemberId;
import com.myshop.member.query.MemberData;
import com.myshop.member.query.MemberQueryService;
import com.myshop.order.command.domain.OrdererService;
import com.myshop.order.command.domain.model.Orderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrdererServiceImpl implements OrdererService {
    private final MemberQueryService memberQueryService;

    @Override
    public Orderer createOrderer(MemberId orderMemberId) {
        MemberData memberData = memberQueryService.getMemberData(orderMemberId.getId());
        return new Orderer(new MemberId(memberData.getId()), memberData.getName());
    }
}
