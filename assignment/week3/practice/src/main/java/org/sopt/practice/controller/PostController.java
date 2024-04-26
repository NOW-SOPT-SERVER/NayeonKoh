package org.sopt.practice.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.SuccessMessage;
import org.sopt.practice.common.dto.SuccessResponse;
import org.sopt.practice.service.PostService;
import org.sopt.practice.service.dto.PostCreateRequest;
import org.sopt.practice.service.dto.PostFindAllResponse;
import org.sopt.practice.service.dto.PostFindResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public SuccessResponse<?> createPost(
            @RequestHeader(name = "memberId") final Long memberId,
            @Valid @RequestBody final PostCreateRequest postCreateRequest,
            HttpServletResponse response
    ){
        String postId = postService.createPost(memberId, postCreateRequest);

        /* Location field -> 새로운 리소스에 대한 Uri endpoint
        * Post-ID -> 새로 생성된 post id
        */
        response.setHeader("Location", "/api/v1/post/" + postId);
        response.setHeader("Post-ID", postId);
        return SuccessResponse.of(SuccessMessage.OK, null);
    }

    @GetMapping("/posts/{postId}")
    public SuccessResponse<PostFindResponse> getPost(
            @PathVariable final Long postId
    ){
        return SuccessResponse.of(SuccessMessage.OK, postService.findPost(postId));
    }

    @GetMapping("/posts")
    public SuccessResponse<List<PostFindAllResponse>> getAllPost() {
        return SuccessResponse.of(SuccessMessage.OK, postService.findAllPost());
    }
}
