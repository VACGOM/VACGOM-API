package kr.co.vacgom.persistence.member.entity;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import kr.co.vacgom.persistence.member.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table(name = "TB_MEMBER")
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MemberEntity extends BaseEntity {

    @Id
    @Column(name = "MEMBER_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
    )
    @JoinColumn(name = "BABY_ID")
    private BabyEntity baby;

    private String name;
}
