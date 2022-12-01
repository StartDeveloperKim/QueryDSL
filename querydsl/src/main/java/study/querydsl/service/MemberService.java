package study.querydsl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.querydsl.domain.Member;
import study.querydsl.repository.QueryDslMemberRepository;
import study.querydsl.repository.QueryDslMemberRepositoryImpl;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final QueryDslMemberRepository memberRepository;

    public Member getMember(Long id) {
        return memberRepository.findById(id);
    }
}
