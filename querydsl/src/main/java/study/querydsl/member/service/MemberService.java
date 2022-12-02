package study.querydsl.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.querydsl.member.domain.Member;
import study.querydsl.member.repository.MemberRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("멤버없음"));
    }

    public List<Member> getMembersByNameAndAge(String name, Long age) {
        return memberRepository.findByNameAndAge(name, age);
    }
}
