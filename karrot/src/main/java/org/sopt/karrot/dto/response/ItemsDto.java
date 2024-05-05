package org.sopt.karrot.dto.response;

import java.util.List;
import org.sopt.karrot.domain.Item;
import org.sopt.karrot.domain.area.domain.EmdArea;

public record ItemsDto(
        Long id,
        String title,
        String category,
        Integer price,
        Integer likes,
        EmdArea location

) {
    public static List<ItemsDto> listOf(List<Item> items) {
        return items.stream().map(item -> new ItemsDto(
                item.getId(),
                item.getTitle(),
                item.getCategory().name(),
                item.getPrice(),
                item.getLikes(),
                item.getLocation()
        )).toList();
    }
}
