package com.myshop.member.command.domain.model;

import com.myshop.common.event.Events;
import com.myshop.common.jpa.converter.EmailSetConverter;
import com.myshop.common.model.Email;
import com.myshop.common.model.EmailSet;
import com.myshop.member.command.domain.event.MemberBlockedEvent;
import com.myshop.member.command.domain.event.MemberUnblockedEvent;
import com.myshop.member.command.domain.event.PasswordChangeEvent;
import com.myshop.member.command.domain.exception.IdPasswordNotMatchingException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "member")
public class Member {
    @EmbeddedId
    private MemberId id;

    private String name;

    @Embedded
    private Password password;

    private boolean blocked;

    @Column(name = "emails")
    @Convert(converter = EmailSetConverter.class)
    private EmailSet emails;

    public void initializePassword() {
        String newPassword = generateRandomPassword();
        this.password = new Password(newPassword);
        Events.raise(new PasswordChangeEvent(id.getId(), newPassword));
    }

    public String generateRandomPassword() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int number = random.nextInt();
        return Integer.toHexString(number);
    }

    public void changeEmails(Set<Email> emails) {
        this.emails = new EmailSet(emails);
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (password.match(oldPassword)) {
            throw new IdPasswordNotMatchingException();
        }

        this.password = new Password(newPassword);
        Events.raise(new PasswordChangeEvent(id.getId(), newPassword));
    }

    public void block() {
        this.blocked = true;
        Events.raise(new MemberBlockedEvent(id.getId()));
    }

    public void unblock() {
        this.blocked = false;
        Events.raise(new MemberUnblockedEvent(id.getId()));
    }
}
