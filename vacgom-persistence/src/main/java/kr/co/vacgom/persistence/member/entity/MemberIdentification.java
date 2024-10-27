package kr.co.vacgom.persistence.member.entity;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.member.constants.ProviderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_MEMBER_IDENTIFICATION")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MemberIdentification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProviderType type;

    @Column(unique = true)
    private Long kakaoId;

    @Column(unique = true)
    private String sub;

    @Column(nullable = false)
    private String refreshToken;

    @OneToOne(mappedBy = "memberIdentification")
    private Member member;
}
