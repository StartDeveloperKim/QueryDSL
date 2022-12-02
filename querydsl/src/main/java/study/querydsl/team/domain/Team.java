package study.querydsl.team.domain;

import lombok.Getter;
import study.querydsl.member.domain.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    List<Member> members = new ArrayList<>();

    protected Team() {
    }

    public Team(String name) {
        this.name = name;
    }
}
