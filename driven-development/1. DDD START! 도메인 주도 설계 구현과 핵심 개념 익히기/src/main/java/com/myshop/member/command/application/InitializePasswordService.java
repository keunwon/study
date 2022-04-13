package com.myshop.member.command.application;

import com.myshop.member.command.domain.MemberRepository;
import com.myshop.member.command.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InitializePasswordService {
    private final MemberRepository memberRepository;

    @Transactional
    public void initialize(String memberId) {
        Member member = MemberServiceHelper.findExistingMember(memberRepository, memberId);
        member.initializePassword();
    }
}
