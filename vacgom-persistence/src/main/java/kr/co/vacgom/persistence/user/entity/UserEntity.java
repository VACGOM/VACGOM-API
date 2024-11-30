package kr.co.vacgom.persistence.user.entity;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import kr.co.vacgom.persistence.global.util.UuidBinaryConverter;
import kr.co.vacgom.persistence.global.util.UuidUtility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "TB_USER")
@Getter
@NoArgsConstructor
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class UserEntity extends BaseEntity {

    @Id
    @Convert(converter = UuidBinaryConverter.class)
    @Column(
            columnDefinition = "BINARY(16)",
            nullable = false,
            updatable = false
    )
    @Comment("[Not Null] 유저 Id")
    private UUID id = UuidUtility.generateRandomUUID();
}
