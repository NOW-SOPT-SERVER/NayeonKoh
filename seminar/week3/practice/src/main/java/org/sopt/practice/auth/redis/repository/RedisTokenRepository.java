package org.sopt.practice.auth.redis.repository;

import java.util.Optional;
import org.sopt.practice.auth.redis.domain.Token;
import org.sopt.practice.common.ErrorMessage;
import org.sopt.practice.exception.NotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisTokenRepository extends CrudRepository<Token, Long> {

    default Token findByRefreshTokenOrElseThrow(final String refreshToken) {
        return findByRefreshToken(refreshToken).orElseThrow(() -> new NotFoundException(ErrorMessage.REFRESH_TOKEN_NOT_FOUND));
    }

    default Token findByIdOrElseThrow(final Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.REFRESH_TOKEN_NOT_FOUND));
    }

    Optional<Token> findByRefreshToken(final String refreshToken);
    Optional<Token> findById(final Long id);
}
