package com.github.keunwon.user.passwordhistory

import com.github.keunwon.user.memeber.Password
import org.springframework.data.annotation.CreatedBy
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class UserPasswordHistory(
    @Column(name = "users_id")
    val userId: Long,

    @Column(name = "password")
    val password: Password,

    @CreatedBy
    @Column(name = "created_at")
    val createdDateTime: LocalDateTime = LocalDateTime.now(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_password_id")
    val id: Long = 0L,
)
