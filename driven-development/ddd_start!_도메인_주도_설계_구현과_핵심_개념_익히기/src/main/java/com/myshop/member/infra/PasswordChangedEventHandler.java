package com.myshop.member.infra;

import com.myshop.member.command.domain.event.PasswordChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PasswordChangedEventHandler {

    @EventListener(PasswordChangeEvent.class)
    public void handle(PasswordChangeEvent event) {
        log.info("이메일 전송: {}", event.getId());
    }
}
