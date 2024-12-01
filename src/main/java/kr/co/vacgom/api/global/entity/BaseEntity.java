package kr.co.vacgom.api.global.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(
            nullable = false,
            insertable = false,
            updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP"
    )
    @CreatedDate
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd a HH:mm")
    @Comment("[Not Null] 생성 일시 시각")
    private LocalDateTime createdAt;

    @Column(
            nullable = false,
            insertable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"
    )
    @LastModifiedDate
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd a HH:mm")
    @Comment("[Not Null] 수정 일시 시각")
    private LocalDateTime updatedAt;
}
