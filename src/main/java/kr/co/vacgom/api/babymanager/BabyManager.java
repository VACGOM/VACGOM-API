package kr.co.vacgom.api.babymanager;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.vacgom.api.baby.Baby;
import kr.co.vacgom.api.global.entity.BaseEntity;
import kr.co.vacgom.api.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_BABY_MANAGER")
public class BabyManager extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "baby_manager_id",
            nullable = false,
            updatable = false
    )
    @Comment("[Not Null] 아기 매니저 id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    @Comment("[Not Null] 아기 매니저 id")
    private User manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baby_id", nullable = false)
    @Comment("[Not Null] 아기 id")
    private Baby baby;

    @Column(nullable = false)
    @Comment("[Not Null] 대표 돌보미 여부")
    private Boolean isAdmin;

    public BabyManager(User manager, Baby baby, Boolean isAdmin) {
        this.manager = manager;
        this.baby = baby;
        this.isAdmin = isAdmin;
    }
}