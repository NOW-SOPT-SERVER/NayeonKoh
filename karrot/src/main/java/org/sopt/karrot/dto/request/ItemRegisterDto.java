package org.sopt.karrot.dto.request;

import org.sopt.karrot.domain.type.ItemCategory;
import org.sopt.karrot.domain.type.TradingMethod;

public record ItemRegisterDto(
        String title,
        TradingMethod tradingMethod,
        ItemCategory category,
        boolean priceSuggestion,
        Integer price,
        String content,
        String location
) {
}
