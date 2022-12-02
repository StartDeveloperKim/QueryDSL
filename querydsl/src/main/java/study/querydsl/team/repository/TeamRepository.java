package study.querydsl.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.team.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long>, CustomTeamRepository {
}
