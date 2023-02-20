package com.github.keunwon.corejpa

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.AbstractAggregateRoot
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long,

    createDateTime: LocalDateTime = LocalDateTime.now(),
    modifiedDateTime: LocalDateTime = LocalDateTime.now(),
) {
    @CreatedDate
    var createdDateTime: LocalDateTime = createDateTime
        protected set

    @LastModifiedDate
    var modifiedLocalDateTime: LocalDateTime = modifiedDateTime
        protected set
}

abstract class BaseRootEntity<A : AbstractAggregateRoot<A>>(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long,

    createDateTime: LocalDateTime = LocalDateTime.now(),
    modifiedDateTime: LocalDateTime = LocalDateTime.now(),
) : AbstractAggregateRoot<A>() {
    @CreatedDate
    var createdDateTime: LocalDateTime = createDateTime
        protected set

    @LastModifiedDate
    var modifiedLocalDateTime: LocalDateTime = modifiedDateTime
        protected set
}
