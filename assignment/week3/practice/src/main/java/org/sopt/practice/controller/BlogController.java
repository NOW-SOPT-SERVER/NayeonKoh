package org.sopt.practice.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.SuccessMessage;
import org.sopt.practice.common.dto.SuccessResponse;
import org.sopt.practice.service.dto.BlogTitleUpdateRequest;
import org.sopt.practice.service.BlogService;
import org.sopt.practice.service.dto.BlogCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/blogs")
    public SuccessResponse<?> createBlog(
            @RequestHeader(name = "memberId") Long memberId, /* Path variable보다 보안 상 안전 */
            @Valid @RequestBody BlogCreateRequest blogCreateRequest,
            HttpServletResponse response
    ) {
        String createdBlogId = blogService.create(memberId, blogCreateRequest);
        /*
         Location header field에 새로운 리소스에 대한 uri 엔드포인트 설정
         Blog-ID field에 생성된 blog의 id 설정
        */
        response.setHeader("Location", "/api/v1/blogs" + createdBlogId);
        response.setHeader("Blog-ID", createdBlogId);
        return SuccessResponse.of(SuccessMessage.BLOG_CREATE_SUCCESS, null);
    }

    @PatchMapping("/blogs/{blogId}/title")
    public SuccessResponse<?> updateBlogTitle(
            @PathVariable Long blogId,
            @Valid @RequestBody BlogTitleUpdateRequest blogTitleUpdateRequest
    ) {
        blogService.updateTitle(blogId, blogTitleUpdateRequest);
        return SuccessResponse.of(SuccessMessage.OK, null);
    }
}
