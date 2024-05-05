package org.sopt.karrot.dto.response;

import java.util.List;
import org.sopt.karrot.domain.Item;
import org.sopt.karrot.domain.area.domain.EmdArea;

public record ItemsDto(
        Long id,
        String title,
        String content,
        String tradingMethod,
        String category,
        Integer price,
        boolean priceSuggestion,
        Integer likes,
        EmdArea location,
        SellerDto seller

) {
    public static List<ItemsDto> listOf(List<Item> items) {
        return items.stream().map(item -> new ItemsDto(
                item.getId(),
                item.getTitle(),
                item.getContent(),
                item.getTradingMethod().name(),
                item.getCategory().name(),
                item.getPrice(),
                item.isPriceSuggestion(),
                item.getLikes(),
                item.getLocation(),
                new SellerDto(
                        item.getSeller().getId(),
                        item.getSeller().getName(),
                        item.getSeller().getTemperature()
                )
        )).toList();
    }

    private record SellerDto(
            Long id,
            String name,
            Double temperature
    ) {
    }
}
