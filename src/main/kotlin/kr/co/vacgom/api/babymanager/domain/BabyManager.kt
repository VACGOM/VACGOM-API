package kr.co.vacgom.api.babymanager.domain

import jakarta.persistence.*
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.global.util.UuidCreator
import kr.co.vacgom.api.user.domain.User
import org.hibernate.annotations.Comment
import java.util.UUID

@Entity
@Table(name = "TB_BABY_MANAGER")
class BabyManager(
    id: UUID = UuidCreator.create(),
    manager: User,
    baby: Baby,
    isAdmin: Boolean = false,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baby_manager_id", nullable = false, updatable = false)
    @Comment("[Not Null] 아기 매니저 id")
    var id: UUID = id
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    @Comment("[Not Null] 아기 매니저 id")
    var manager: User = manager
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baby_id", nullable = false)
    @Comment("[Not Null] 아기 id")
    var baby: Baby = baby
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 대표 돌보미 여부")
    var isAdmin: Boolean = isAdmin
        protected set
}
