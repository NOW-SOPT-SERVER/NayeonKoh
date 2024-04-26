package org.sopt.practice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.practice.service.dto.BlogCreateRequest;

@Entity
@Getter
@NoArgsConstructor
public class Blog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    /* 영속성 컨텍스트에서 연관 관계 조회 시 실제 그 테이블에 접근할 때까지 조회 시기 지연 */
    /* 실제 사용 시기까지 늦춰서 불필요한 조회 X */
    @Column(nullable = false, length = 200)
    private String title;


    private String description;

    public void updateTitle(final String title) {
        this.title = title;
    }

    @Builder
    private Blog(final Member member, final String title, final String description) {
        this.member = member;
        this.title = title;
        this.description = description;
    }

    public static Blog create(final Member member, final BlogCreateRequest blogCreateRequest) {
        return Blog.builder()
                .member(member)
                .title(blogCreateRequest.title())
                .description(blogCreateRequest.description())
                .build();
    }
}
