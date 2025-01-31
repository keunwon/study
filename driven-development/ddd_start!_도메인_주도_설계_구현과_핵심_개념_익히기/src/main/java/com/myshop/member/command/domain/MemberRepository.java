package com.myshop.member.command.domain;

import com.myshop.member.command.domain.model.Member;
import com.myshop.member.command.domain.model.MemberId;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MemberRepository extends Repository<Member, MemberId> {
    Optional<Member> findById(MemberId memberId);

}
