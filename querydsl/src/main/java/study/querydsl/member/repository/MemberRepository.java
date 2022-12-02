package study.querydsl.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {
}
