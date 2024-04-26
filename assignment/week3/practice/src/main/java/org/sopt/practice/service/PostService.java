package org.sopt.practice.service;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.ErrorMessage;
import org.sopt.practice.domain.Blog;
import org.sopt.practice.domain.Post;
import org.sopt.practice.exception.ForbiddenError;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.PostRepository;
import org.sopt.practice.service.dto.PostCreateRequest;
import org.sopt.practice.service.dto.PostFindAllResponse;
import org.sopt.practice.service.dto.PostFindResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    private final BlogService blogService;

    @Transactional
    public String createPost(final Long memberId, final PostCreateRequest postCreateRequest) {
        Blog blog = blogService.findBlogById(postCreateRequest.blogId());

        if (!blog.getMember().getId().equals(memberId)) {
            throw new ForbiddenError(ErrorMessage.ONLY_BLOG_OWNER_CAN_POST);
        }

        Post post = Post.create(postCreateRequest, blog);
        return postRepository.save(post).getId().toString();
    }

    @Transactional(readOnly = true)
    public PostFindResponse findPost(final Long postId) {
        Post post = findPostById(postId);
        return PostFindResponse.of(post);
    }

    @Transactional(readOnly = true)
    public List<PostFindAllResponse> findAllPost() {
        return PostFindAllResponse.listOf(postRepository.findAll());
    }

    protected Post findPostById(final Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.POST_NOT_FOUND));
    }
}
