package org.sopt.practice.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.practice.common.ErrorMessage;
import org.sopt.practice.externel.S3Service;
import org.sopt.practice.service.dto.BlogTitleUpdateRequest;
import org.sopt.practice.domain.Blog;
import org.sopt.practice.domain.Member;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.BlogRepository;
import org.sopt.practice.service.dto.BlogCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    /* TODO: service -> service 호출 단점? */
    private final MemberService memberService;
    private final S3Service s3Service;

    private static final String BLOG_S3_UPLOAD_FOLER = "blog/";

    @Transactional
    public String create(final Long memberId, final BlogCreateRequest createRequest) {
        Member member = memberService.findMemberById(memberId);
        try {
            Blog blog = blogRepository.save(Blog.create(member, createRequest.title(), createRequest.description(),
                    s3Service.uploadImage(BLOG_S3_UPLOAD_FOLER, createRequest.image())));
            return blog.getId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void updateTitle(final Long blogId, final BlogTitleUpdateRequest blogTitleUpdateRequest) {
        Blog blog = findBlogById(blogId);
        blog.updateTitle(blogTitleUpdateRequest.title());
        /* 왜 save가 없어도 update가 되나 ? blogRepository.save(blog); -> entity manager 자동 save(merge) query */
        /* save()를 사용할 수도 있는데, 이런 경우 save()에는 @Transactional 선언이 되어 있어서 메소드에는 어노테이션 안해줘도 됨 */
    }

    @Transactional(readOnly = true)
    protected Blog findBlogById(final Long blogId) {
        return blogRepository.findById(blogId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND));
    }
}
