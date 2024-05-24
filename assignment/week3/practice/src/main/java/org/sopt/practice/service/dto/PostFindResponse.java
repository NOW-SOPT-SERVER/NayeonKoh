package org.sopt.practice.service.dto;

import org.sopt.practice.domain.Post;

public record PostFindResponse(
        Long id,
        String title,
        String content,
        Long blogId
) {
    public static PostFindResponse of(final Post post) {
        return new PostFindResponse(post.getId(), post.getTitle(), post.getContent(), post.getBlog().getId());
    }
}
