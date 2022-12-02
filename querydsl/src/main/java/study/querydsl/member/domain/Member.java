package study.querydsl.member.domain;

import lombok.Getter;
import study.querydsl.team.domain.Team;

import javax.persistence.*;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public Member() {
    }

    public Member(String name, Long age, Team team) {
        this.name = name;
        this.age = age;
        setTeam(team);
    }

    private void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
