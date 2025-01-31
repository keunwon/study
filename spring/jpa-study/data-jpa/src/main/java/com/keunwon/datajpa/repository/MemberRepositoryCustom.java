package com.keunwon.datajpa.repository;

import com.keunwon.datajpa.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
