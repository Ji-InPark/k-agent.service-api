package com.example.militaryservicecompanychecker.common.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @CreatedDate
    var createdAt: LocalDateTime? = LocalDateTime.MIN

    @LastModifiedDate
    var updatedAt: LocalDateTime? = LocalDateTime.MIN
}