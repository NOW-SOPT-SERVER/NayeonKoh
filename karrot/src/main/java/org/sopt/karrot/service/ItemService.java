package org.sopt.karrot.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.karrot.domain.Item;
import org.sopt.karrot.domain.Member;
import org.sopt.karrot.domain.area.domain.EmdArea;
import org.sopt.karrot.domain.area.repository.EmdAreaRepository;
import org.sopt.karrot.dto.request.ItemRegisterDto;
import org.sopt.karrot.dto.response.ItemDto;
import org.sopt.karrot.dto.response.ItemsDto;
import org.sopt.karrot.repository.ItemRepository;
import org.sopt.karrot.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final EmdAreaRepository emdAreaRepository;

    @Transactional
    public void registerItem(final Long memberId, final ItemRegisterDto registerDto) {
        Member seller = memberRepository.findByIdOrThrow(memberId);
        EmdArea location = emdAreaRepository.findByIdOrThrow(registerDto.locationId());
        Item item = Item.from(registerDto, seller, location);
        itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public List<ItemsDto> findItemsByLocation(final Long locationId) {
        return ItemsDto.listOf(itemRepository.findByLocationId(locationId));
    }

    @Transactional(readOnly = true)
    public ItemDto findItemById(final Long itemId) {
        return ItemDto.from(itemRepository.findByIdOrThrow(itemId));
    }
}
