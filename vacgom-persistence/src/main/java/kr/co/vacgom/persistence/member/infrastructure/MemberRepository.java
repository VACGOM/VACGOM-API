package kr.co.vacgom.persistence.member.infrastructure;

import kr.co.vacgom.persistence.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
