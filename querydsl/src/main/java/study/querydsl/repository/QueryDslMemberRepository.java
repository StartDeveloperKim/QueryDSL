package study.querydsl.repository;

import study.querydsl.domain.Member;

import java.util.List;

public interface QueryDslMemberRepository {

    Member findById(Long id);

    List<Member> findByNameAndAge(String name, Long age);

    List<Member> findMemberOrderByAge();
}
