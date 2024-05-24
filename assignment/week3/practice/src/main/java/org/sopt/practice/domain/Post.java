package org.sopt.practice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.practice.service.dto.PostCreateRequest;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    @Builder
    private Post(final Long id, final String title, final String content, final Blog blog) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.blog = blog;
    }

    public static Post create(final PostCreateRequest postCreateRequest, final Blog blog) {
        return Post.builder()
                .title(postCreateRequest.title())
                .content(postCreateRequest.content())
                .blog(blog)
                .build();
    }
}
