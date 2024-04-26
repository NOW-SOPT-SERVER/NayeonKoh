package org.sopt.practice.service.dto;

import java.util.List;
import org.sopt.practice.domain.Post;

public record PostFindAllResponse(
        Long id,
        String title,
        String content) {
    public static List<PostFindAllResponse> listOf(
            final List<Post> posts
    ) {
        return posts
                .stream()
                .map(post -> new PostFindAllResponse(post.getId(), post.getTitle(), post.getContent()))
                .toList();
    }
}
