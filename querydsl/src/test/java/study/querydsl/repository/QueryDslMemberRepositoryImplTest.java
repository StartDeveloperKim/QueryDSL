package study.querydsl.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.member.domain.Member;
import study.querydsl.member.repository.MemberRepository;
import study.querydsl.team.domain.Team;
import study.querydsl.team.repository.TeamRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class QueryDslMemberRepositoryImplTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

    Member savedMember;
    Team team;
    @BeforeEach
    void setUp() {
        team = teamRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        Member member = new Member("김민우", 20L, team);
        savedMember = memberRepository.save(member);
    }

    @Test
    void 멤버ID를_통해_찾아오기() {
        // given, when
        Member findMember = memberRepository.findById(savedMember.getId())
                .orElseThrow(EntityNotFoundException::new);
        //then
        checkSaveMemberAndFindMember(findMember, savedMember);
    }

    @Test
    void 이름과나이로_검색하기() {
        //given, when
        List<Member> findMembers = memberRepository.findByNameAndAge("김민우", 20L);
        Member findMember = findMembers.get(0);
        //then
        checkSaveMemberAndFindMember(findMember, savedMember);
    }

    @Test
    void 나이순으로_정렬해서_조회하기() {
        //given
        Member savedMember1 = memberRepository.save(new Member("어른1", 120L, team));
        Member savedMember2 = memberRepository.save(new Member("어른2", 125L, team));
        //when
        List<Member> findMembers = memberRepository.findMemberOrderByAge();
        //then
        checkSaveMemberAndFindMember(findMembers.get(1), savedMember1);
        checkSaveMemberAndFindMember(findMembers.get(0), savedMember2);
    }
    
    @Test
    void 특정나이보다_적은_멤버_조회하기() {
        //given, when
        List<Member> members = memberRepository.searchMemberByAge(30L);
        //then
        for (Member member : members) {
            System.out.println("member.getName() = " + member.getName());
        }
    }

    private void checkSaveMemberAndFindMember(Member findMember, Member savedMember) {
        assertThat(savedMember.getId()).isEqualTo(findMember.getId());
        assertThat(savedMember.getName()).isEqualTo(findMember.getName());
    }
}