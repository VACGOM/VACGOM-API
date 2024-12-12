package kr.co.vacgom.api.user.domain

import jakarta.persistence.*
import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import kr.co.vacgom.api.global.util.UuidCreator
import kr.co.vacgom.api.user.domain.enums.UserRole
import org.hibernate.annotations.Comment
import org.springframework.security.core.GrantedAuthority
import java.util.*

@Entity
@Table(name = "TB_USER")
class User(
    id: UUID = UuidCreator.create(),
    nickname: String,
    socialId: String?,
    provider: SocialLoginProvider,
    role: UserRole,
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    @Comment("[Not Null] 유저 id")
    var id = id
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 유저 닉네임")
    var nickname: String = nickname
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 소셜 id")
    var socialId: String? = socialId
        protected set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("[Not Null] 소셜 로그인 Provider")
    var provider: SocialLoginProvider = provider
        protected set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("[Not Null] 유저 권한")
    var role: UserRole = role
        protected set

    init {
        require(nickname.length >= 2) { "닉네임 글자는 두 글자 이상이어야 합니다." }
        require(nickname.matches(nicknameRegex)) { "닉네임에는 영어 소문자를 사용할 수 없습니다." }
        //Todo: 중복 제한 체크 필요
    }

    companion object {
        val nicknameRegex = Regex("^[가-힣a-z0-9!@#\$%^&*(),.?\":{}|<>_\\-+=\\[\\]\\\\/'~` ]*\$")
    }
}
