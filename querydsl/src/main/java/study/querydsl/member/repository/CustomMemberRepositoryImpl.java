package study.querydsl.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import study.querydsl.member.domain.Member;
import study.querydsl.member.domain.QMember;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    private final JPAQueryFactory query;

    public CustomMemberRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Member> findByNameAndAge(String name, Long age) {
        QMember member = QMember.member;
        log.info("findByNameAndAge");

        return query
                .select(member)
                .from(member)
                .where(member.name.eq(name).and(member.age.eq(age)))
                .fetch();
    }

    @Override
    public List<Member> findMemberOrderByAge() {
        QMember member = QMember.member;
        log.info("findMemberOrderByAge");
        return query
                .select(member)
                .from(member)
                .orderBy(member.age.desc())
                .fetch();
    }

    @Override
    public List<Member> findByTeamId(Long id) {
        QMember member = QMember.member;
        log.info("findByTeamId");
        return query
                .select(member)
                .from(member)
                .where(member.team.id.eq(id))
                .fetch();
    }

    @Override
    public List<Member> searchMemberByAge(Long age) {
        QMember member = QMember.member;
        return query
                .selectFrom(member)
                .where(member.age.loe(age))
                .offset(10).limit(20)
                .fetch();
    }
}
