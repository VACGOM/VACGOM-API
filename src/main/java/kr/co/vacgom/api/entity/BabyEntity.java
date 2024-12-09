package kr.co.vacgom.api.entity;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import kr.co.vacgom.api.entity.constants.Sex;
import kr.co.vacgom.api.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "TB_BABY")
@Getter
@NoArgsConstructor
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class BabyEntity extends BaseEntity {

    @Id
    @Column(
            name = "baby_id",
            nullable = false,
            updatable = false
    )
    @Comment("[Not Null] 유저 Id")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @Comment("[Not Null] 생년월일")
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String name;
}
