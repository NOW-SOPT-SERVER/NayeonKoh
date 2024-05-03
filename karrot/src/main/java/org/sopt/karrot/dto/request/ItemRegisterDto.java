package org.sopt.karrot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.NonNull;
import org.sopt.karrot.domain.type.ItemCategory;
import org.sopt.karrot.domain.type.TradingMethod;

public record ItemRegisterDto(
        @NonNull
        @NotBlank
        @Size(min = 1, max = 100)
        String title,
        @NonNull
        TradingMethod tradingMethod,
        @NonNull
        ItemCategory category,
        @NonNull
        boolean priceSuggestion,

        @NonNull
        @Positive
        Integer price,
        @NonNull
        String content,

        @NonNull
        Long locationCode
) {
}
