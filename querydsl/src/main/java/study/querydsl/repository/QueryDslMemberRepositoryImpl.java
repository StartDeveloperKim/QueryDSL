package study.querydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import study.querydsl.domain.Member;
import study.querydsl.domain.QMember;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
public class QueryDslMemberRepositoryImpl implements QueryDslMemberRepository{

    private final JPAQueryFactory query;

    public QueryDslMemberRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Member findById(Long id) {
        QMember qMember = QMember.member;
        log.info("findById");

        return query.select(qMember)
                .from(qMember)
                .where(qMember.id.eq(id))
                .fetchFirst();
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
}
