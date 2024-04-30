package org.sopt.karrot.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import org.sopt.karrot.exception.CommonException;
import org.sopt.karrot.exception.ErrorCode;
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

    public static ResponseDto<?> fail(final CommonException e) {
        return new ResponseDto<>(e.getErrorCode().getHttpStatus(), false, null, ExceptionDto.from(e.getErrorCode()));
    }

    public static ResponseDto<?> fail(final IllegalArgumentException e) {
        return new ResponseDto<>(HttpStatus.BAD_REQUEST, false, null, ExceptionDto.from(ErrorCode.BAD_REQUEST));
    }
}
