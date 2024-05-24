package org.sopt.karrot.dto.response;

import java.util.List;
import org.sopt.karrot.domain.Item;
import org.sopt.karrot.domain.Member;
import org.sopt.karrot.domain.area.domain.EmdArea;

public record ItemDto(
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
    public static ItemDto from(Item item) {
        return new ItemDto(
                item.getId(),
                item.getTitle(),
                item.getContent(),
                item.getTradingMethod().name(),
                item.getCategory().name(),
                item.getPrice(),
                item.isPriceSuggestion(),
                item.getLikes(),
                item.getLocation(),
                SellerDto.from(item.getSeller())
        );
    }

    private record SellerDto(
            Long id,
            String name,
            Double temperature
    ) {
        private static SellerDto from(Member seller) {
            return new SellerDto(
                    seller.getId(),
                    seller.getName(),
                    seller.getTemperature()
            );
        }
    }
}
