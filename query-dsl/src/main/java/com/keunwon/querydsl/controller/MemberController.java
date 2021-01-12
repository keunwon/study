package com.keunwon.querydsl.controller;

import com.keunwon.querydsl.dto.MemberSearchCondition;
import com.keunwon.querydsl.dto.MemberTeamDto;
import com.keunwon.querydsl.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;


    @GetMapping(path = "/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
        return memberJpaRepository.search(condition);
    }
}
