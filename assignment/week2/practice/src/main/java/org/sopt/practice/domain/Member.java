package org.sopt.practice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Part part;

    private int age;

    @Builder
    private Member(final String name, final Part part, final int age) {
        this.name = name;
        this.part = part;
        this.age = age;
    }

    public static Member create(final String name, final Part part, final int age) {
        return Member.builder()
                .name(name)
                .part(part)
                .age(age)
                .build();
    }
}
