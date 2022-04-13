package com.myshop.member.ui;

import com.myshop.common.user.LoginUser;
import com.myshop.member.command.application.ChangePasswordRequest;
import com.myshop.member.command.application.ChangePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MemberPasswordController {
    private final ChangePasswordService changePasswordService;

    @PostMapping(value = "/member/changePassword")
    public String submit(@LoginUser User user, ChangePasswordRequest request) {
        request.setMemberId(user.getUsername());
        changePasswordService.changePassword(request);
        return "redirect:/home";
    }
}
