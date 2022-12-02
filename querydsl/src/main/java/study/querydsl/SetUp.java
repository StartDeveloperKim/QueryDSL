package study.querydsl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.member.domain.Member;
import study.querydsl.member.repository.MemberRepository;
import study.querydsl.team.domain.Team;
import study.querydsl.team.repository.TeamRepository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
//@Component
@Transactional
public class SetUp implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("commandLineRunner");
        for (int i = 1; i < 6; i++) {
            teamRepository.save(new Team("팀" + i));
        }
        saveMembers();
    }

    private void saveMembers() {
        for (int i = 1; i < 101; i++) {
            Long age = (long) (int) (Math.random() * 100);
            int teamId = (int) (Math.random() * 5 + 1);
            Team team = teamRepository.findById((long) teamId)
                    .orElseThrow(() -> new EntityNotFoundException("팀 없음"));

            Member member = new Member("사람" + i, age, team);
            memberRepository.save(member);
        }
    }
}
