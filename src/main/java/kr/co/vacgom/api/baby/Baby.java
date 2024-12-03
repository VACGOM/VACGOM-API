package kr.co.vacgom.api.baby;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import kr.co.vacgom.api.user.domain.enums.Gender;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_BABY")
public class Baby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "baby_id",
            nullable = false,
            updatable = false
    )
    @Comment("[Not Null] 아기 id")
    private Long id;

    @Column(nullable = false)
    @Comment("[Not Null] 아기 이름")
    private String name;

    @Comment("[Not Null] 아기 프로필 이미지 url")
    private String profileImg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("[Not Null] 아기 성별")
    private Gender gender;

    @Column(nullable = false)
    @Comment("[Not Null] 아기 생년월일")
    private LocalDate birthday;
}
