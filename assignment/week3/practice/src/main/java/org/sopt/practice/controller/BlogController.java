package org.sopt.practice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.SuccessMessage;
import org.sopt.practice.common.dto.SuccessStatusResponse;
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
    public ResponseEntity<SuccessStatusResponse> createBlog(
            @RequestHeader(name = "memberId") Long memberId, /* Path variable보다 보안 상 안전 */
            @RequestBody BlogCreateRequest blogCreateRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).header(
                /* create 시 생성된 entity의 identifier를 Location Header로 반환 */
                "Location",
                        blogService.create(memberId, blogCreateRequest)
                )
                .body(SuccessStatusResponse.of(SuccessMessage.BLOG_CREATE_SUCCESS));
    }

    @PatchMapping("/blogs/{blogId}/title")
    public ResponseEntity updateBlogTitle(
            @PathVariable Long blogId,
            @Valid @RequestBody BlogTitleUpdateRequest blogTitleUpdateRequest
    ) {
        blogService.updateTitle(blogId, blogTitleUpdateRequest);
        return ResponseEntity.noContent().build();
    }
}
