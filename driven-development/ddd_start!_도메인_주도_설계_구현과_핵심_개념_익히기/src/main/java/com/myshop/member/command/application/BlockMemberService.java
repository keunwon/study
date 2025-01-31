package com.myshop.member.command.application;

import com.myshop.member.command.domain.MemberRepository;
import com.myshop.member.command.domain.model.Member;
import com.myshop.member.command.domain.model.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BlockMemberService {
    private final MemberRepository memberRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void block(String memberId) {
        Member member = memberRepository.findById(new MemberId(memberId))
                .orElseThrow(NoMemberException::new);

        member.block();
    }
}
