package study.querydsl.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.Member;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class QueryDslMemberRepositoryImplTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    QueryDslMemberRepository queryDslMemberRepository;
    @Autowired
    MemberRepository memberRepository;

    Member savedMember;
    @BeforeEach
    void setUp() {
        Member member = new Member("김민우", 20L);
        savedMember = memberRepository.save(member);
    }

    @Test
    void 멤버ID를_통해_찾아오기() {
        // given, when
        Member findMember = queryDslMemberRepository.findById(savedMember.getId());
        //then
        checkSaveMemberAndFindMember(findMember, savedMember);
    }

    @Test
    void 이름과나이로_검색하기() {
        //given, when
        List<Member> findMembers = queryDslMemberRepository.findByNameAndAge("김민우", 20L);
        Member findMember = findMembers.get(0);
        //then
        checkSaveMemberAndFindMember(findMember, savedMember);
    }

    @Test
    void 나이순으로_정렬해서_조회하기() {
        //given
        Member savedMember1 = memberRepository.save(new Member("어른1", 120L));
        Member savedMember2 = memberRepository.save(new Member("어른2", 125L));
        //when
        List<Member> findMembers = queryDslMemberRepository.findMemberOrderByAge();
        //then
        checkSaveMemberAndFindMember(findMembers.get(1), savedMember1);
        checkSaveMemberAndFindMember(findMembers.get(0), savedMember2);
    }

    private void checkSaveMemberAndFindMember(Member findMember, Member savedMember) {
        assertThat(savedMember.getId()).isEqualTo(findMember.getId());
        assertThat(savedMember.getName()).isEqualTo(findMember.getName());
    }
}