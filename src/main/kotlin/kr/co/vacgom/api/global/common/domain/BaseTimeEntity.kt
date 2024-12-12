package kr.co.vacgom.api.global.common.domain

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseTimeEntity(
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    @Column(
        nullable = false,
        insertable = false,
        updatable = false,
        columnDefinition = "datetime default CURRENT_TIMESTAMP"
    )
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd a HH:mm")
    @Comment("[Not Null] 생성 일시 시각")
    var createdAt: LocalDateTime = createdAt
        protected set

    @Column(
        nullable = false,
        insertable = false,
        columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"
    )
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd a HH:mm")
    @Comment("[Not Null] 수정 일시 시각")
    var updatedAt: LocalDateTime = updatedAt
        protected set
}
