package org.sopt.karrot.repository;

import org.sopt.karrot.domain.Member;
import org.sopt.karrot.exception.CommonException;
import org.sopt.karrot.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member findByIdOrThrow(final Long memberId) {
        return findById(memberId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MEMBER));
    }
}
