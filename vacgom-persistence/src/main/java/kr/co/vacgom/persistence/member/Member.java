package kr.co.vacgom.persistence.member;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.BaseEntity;
import kr.co.vacgom.persistence.member.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "TB_MEMBER")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private MemberIdentification memberIdentification;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
    )
    @JoinColumn(name = "baby_id")
    private Baby baby;

    private Boolean masterStatus;

    private String invitationCode;

    private String name;

    public void updateBaby(Baby baby) {
        this.baby = baby;
    }

    public void updateRole(Role role) {
        this.role = role;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void initMemberToMaster() {
        this.masterStatus = true;
        this.invitationCode = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .toUpperCase()
                .substring(0, 10);
    }

    public void initMemberToInviter() {
        this.masterStatus = false;
    }
}
