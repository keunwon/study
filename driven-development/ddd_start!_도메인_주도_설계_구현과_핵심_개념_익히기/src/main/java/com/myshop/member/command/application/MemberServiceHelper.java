package com.myshop.member.command.application;

import com.myshop.member.command.domain.MemberRepository;
import com.myshop.member.command.domain.model.Member;
import com.myshop.member.command.domain.model.MemberId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceHelper {

    public static Member findExistingMember(MemberRepository memberRepository, String memberId) {
        return memberRepository.findById(new MemberId(memberId))
                .orElseThrow(NoMemberException::new);
    }
}
