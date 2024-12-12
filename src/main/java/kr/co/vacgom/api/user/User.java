package kr.co.vacgom.api.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import java.util.regex.Pattern;
import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider;
import kr.co.vacgom.api.global.entity.BaseEntity;
import kr.co.vacgom.api.user.domain.enums.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_USER")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "user_id",
            nullable = false,
            updatable = false
    )
    @Comment("[Not Null] 유저 id")
    private Long id;

    @Column(nullable = false)
    @Comment("[Not Null] 유저 닉네임")
    private String nickname;

    @Column(nullable = false)
    @Comment("[Not Null] 소셜 id")
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("[Not Null] 소셜 로그인 Provider")
    private SocialLoginProvider provider;

    @Enumerated(EnumType.STRING)
    @Comment("[Not Null] 유저 권한")
    private UserRole role;

    public User(String nickname, String socialId, SocialLoginProvider provider, UserRole role) {
        String nickNameRegex = "^[가-힣a-z0-9!@#$%^&*(),.?\":{}|<>_\\-+=\\[\\]\\\\/'~` ]*$";
        int sizeLimit = 2;

        if (nickname.length() < sizeLimit || !Pattern.matches(nickNameRegex, nickname)) {
            throw new IllegalArgumentException();
        }

        this.nickname = nickname;
        this.socialId = socialId;
        this.provider = provider;
        this.role = role;
    }
}
