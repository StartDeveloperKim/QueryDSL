package study.querydsl.member.repository;

import study.querydsl.member.domain.Member;

import java.util.List;

public interface CustomMemberRepository {

    List<Member> findByNameAndAge(String name, Long age);

    List<Member> findMemberOrderByAge();

    List<Member> findByTeamId(Long id);

    List<Member> searchMemberByAge(Long age);
}
