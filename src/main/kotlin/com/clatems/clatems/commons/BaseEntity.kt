package com.clatems.clatems.commons

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*


@MappedSuperclass
@JsonIgnoreProperties(
    value = [
        "createdAt, updatedAt"
    ], allowGetters = true
)
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    var id: Long? = null

    @CreatedDate
    @Column
    val createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column
    val updatedAt: LocalDateTime = LocalDateTime.now()
}
