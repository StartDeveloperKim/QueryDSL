package study.querydsl.member.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private String name;
    private Long age;

    public MemberDto(String name, Long age) {
        this.name = name;
        this.age = age;
    }
}
