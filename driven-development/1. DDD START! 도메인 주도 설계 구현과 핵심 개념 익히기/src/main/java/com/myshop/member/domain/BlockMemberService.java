package com.myshop.member.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlockMemberService {
    private final MemberRepository memberRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public void block(String memberId) {
        Member member = memberRepository.findById(new MemberId(memberId))
                .orElseThrow(NoMemberException::new);

        member.block();
    }
}
