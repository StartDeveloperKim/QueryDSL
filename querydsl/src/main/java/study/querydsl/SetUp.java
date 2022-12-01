package study.querydsl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.querydsl.domain.Member;
import study.querydsl.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
//@Component
public class SetUp {

    private final MemberRepository memberRepository;

    //@PostConstruct
    void setUp() {
        for (int i = 1; i < 101; i++) {
            Long age = (long) (int) (Math.random() * 100);
            Member member = new Member("사람" + i, age);
            memberRepository.save(member);
        }
    }
}
