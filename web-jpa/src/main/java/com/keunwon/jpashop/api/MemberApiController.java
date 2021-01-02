package com.keunwon.jpashop.api;

import com.keunwon.jpashop.domain.Member;
import com.keunwon.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;


    @GetMapping(path = "/api/v1/members")
    public List<Member> memberV1() {
        return memberService.findMembers();
    }

    @GetMapping(path = "/api/v2/members")
    public Result memberV2() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(toList());

        return new Result(collect);
    }

    @AllArgsConstructor
    @Data
    static class Result<T> {
        private T data;
    }

    @AllArgsConstructor
    @Data
    static class MemberDto {
        private String name;
    }

    @PostMapping(path = "/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        return new CreateMemberResponse(memberService.join(member));
    }

    @PostMapping(path = "/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        return new CreateMemberResponse(memberService.join(member));
    }

    @PutMapping(path = "/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable Long id, @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }


    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @AllArgsConstructor
    @Data
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @AllArgsConstructor
    @ Data
    static class CreateMemberResponse {
        private Long id;
    }
}
