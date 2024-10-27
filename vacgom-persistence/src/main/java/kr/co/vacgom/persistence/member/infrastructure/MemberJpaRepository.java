package kr.co.vacgom.persistence.member.infrastructure;

import kr.co.vacgom.persistence.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {
}
