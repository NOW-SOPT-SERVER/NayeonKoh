package org.sopt.karrot.domain.area.repository;

import java.util.Optional;
import lombok.NonNull;
import org.sopt.karrot.domain.area.domain.EmdArea;
import org.sopt.karrot.exception.CommonException;
import org.sopt.karrot.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EmdAreaRepository extends CrudRepository<EmdArea, Long> {
    default EmdArea findByIdOrThrow(final Long code) {
        return findById(code).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_BEOPJEONGDONG));
    }

    Optional<EmdArea> findById(@NonNull final Long locationId);
}
