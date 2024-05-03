package org.sopt.karrot.domain.area.repository;

import java.util.Optional;
import org.sopt.karrot.domain.area.domain.EmdArea;
import org.sopt.karrot.exception.CommonException;
import org.sopt.karrot.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmdAreaRepository extends JpaRepository<EmdArea, Long> {
    default EmdArea findByCodeOrThrow(final Long code) {
        return findFirstByCode(code).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_BEOPJEONGDONG));
    }

    Optional<EmdArea> findFirstByCode(final Long code);
}
