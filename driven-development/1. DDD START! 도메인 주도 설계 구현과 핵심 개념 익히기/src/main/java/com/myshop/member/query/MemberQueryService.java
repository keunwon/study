package com.myshop.member.query;

import com.myshop.member.command.application.NoMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberQueryService {
    private final MemberDataDao memberDataDao;

    public MemberData getMemberData(String memberId) {
        MemberData memberData = memberDataDao.findById(memberId);
        if (memberData == null) {
            throw new NoMemberException();
        }
        return memberData;
    }
}
