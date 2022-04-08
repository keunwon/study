package com.myshop.member.domain;

import com.myshop.infra.EmailSet;
import com.myshop.infra.EmailSetConverter;
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
    }

    public String generateRandomPassword() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int number = random.nextInt();
        return Integer.toHexString(number);
    }

    public void changePassword(String currentPassword, String newPassword) {
        if (!password.match(newPassword)) {
            throw new PasswordNotMatchException();
        }
        this.password = new Password(newPassword);
    }

    public void block() {
        this.blocked = true;
    }
}
