package org.sopt.karrot.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import org.sopt.karrot.exception.CommonException;
import org.springframework.http.HttpStatus;
import jakarta.validation.constraints.NotNull;

public record ResponseDto<T>(
        @JsonIgnore HttpStatus httpStatus,
        @NotNull Boolean success,
        @NotNull T data,
        @Nullable ExceptionDto error
        ) {

    public static <T> ResponseDto<T> success(@Nullable final T data) {
        return new ResponseDto<>(HttpStatus.OK, true, data, null);
    }

    public static ResponseDto<Object> fail(final CommonException e) {
        return new ResponseDto<>(e.getErrorCode().getHttpStatus(), false, null, ExceptionDto.from(e.getErrorCode()));
    }
}
