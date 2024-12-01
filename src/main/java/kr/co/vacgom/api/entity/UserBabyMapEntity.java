package kr.co.vacgom.api.entity;

import jakarta.persistence.*;
import kr.co.vacgom.api.entity.constants.UserBabyAccessPermission;
import kr.co.vacgom.api.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "TB_USER_BABY_MAP")
@Getter
@NoArgsConstructor
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class UserBabyMapEntity extends BaseEntity {

    @Id
    @Column(
            nullable = false,
            updatable = false
    )
    @GeneratedValue(strategy = IDENTITY)
    @Comment("[Not Null] 유저-아기 엔티티 Id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    @Comment("[Not Null] 백신 Id")
    private UserEntity user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "BABY_ID")
    @Comment("[Not Null] 예방 가능한 질병 Id")
    private BabyEntity baby;

    @Enumerated(STRING)
    private UserBabyAccessPermission accessPermission;
}
