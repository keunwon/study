package com.myshop.member.command.application;

import com.myshop.member.command.domain.MemberRepository;
import com.myshop.member.command.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChangePasswordService {
    private final MemberRepository memberRepository;

    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        Member member = MemberServiceHelper.findExistingMember(memberRepository, request.getMemberId());
        member.changePassword(request.getOldPassword(), request.getNewPassword());
    }
}
