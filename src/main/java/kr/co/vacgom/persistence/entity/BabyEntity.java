package kr.co.vacgom.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.vacgom.persistence.entity.constants.Sex;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import kr.co.vacgom.persistence.global.util.UuidBinaryConverter;
import kr.co.vacgom.persistence.global.util.UuidUtility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "TB_BABY")
@Getter
@NoArgsConstructor
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class BabyEntity extends BaseEntity {

    @Id
    @Convert(converter = UuidBinaryConverter.class)
    @Column(
            columnDefinition = "BINARY(16)",
            nullable = false,
            updatable = false
    )
    @Comment("[Not Null] 유저 Id")
    private UUID id = UuidUtility.generateRandomUUID();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @Comment("[Not Null] 생년월일")
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String name;
}
