package kr.co.vacgom.api.user.domain

import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import kr.co.vacgom.api.global.util.UuidCreator
import org.springframework.security.core.GrantedAuthority
import java.util.*

class User(
    val id: UUID = UuidCreator.create(),
    val nickname: String,
    private val babies: MutableSet<Baby> = mutableSetOf(),
    val socialId: String?,
    val provider: SocialLoginProvider,
    val roles: List<GrantedAuthority>,
): BaseTimeEntity() {
    fun addBabies(newBabies: Set<Baby>) {
        babies.addAll(newBabies)
    }

    init {
        require(nickname.length >= 2) { "닉네임 글자는 두 글자 이상이어야 합니다." }
        require(nickname.matches(nicknameRegex)) { "닉네임에는 영어 소문자를 사용할 수 없습니다." }
        //Todo: 중복 제한 체크 필요
    }

    companion object {
        val nicknameRegex = Regex("^[가-힣a-z0-9!@#\$%^&*(),.?\":{}|<>_\\-+=\\[\\]\\\\/'~` ]*\$")

        fun create(
            nickname: String,
            socialId: String?,
            provider: SocialLoginProvider,
            roles: List<GrantedAuthority>
        ): User{
            return User(
                nickname = nickname,
                socialId = socialId,
                provider = provider,
                roles = roles,
            )
        }
    }
}
