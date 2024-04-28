package org.sopt.karrot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.sopt.karrot.dto.request.MemberCreateDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    /* columnDefinition에 대해 */
    @Column(name = "temperature", nullable = false)
    private Double temperature;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Item> soldItems;

    @Builder
    private Member(final String name, final Double temperature, final List<Item> soldItems) {
        this.name = name;
        this.temperature = temperature;
        this.soldItems = soldItems;
    }

    public static Member from(final MemberCreateDto memberCreateDto) {
        return Member.builder()
                .name(memberCreateDto.name())
                .temperature(36.5)
                .soldItems(new ArrayList<>())
                .build();
    }
}
