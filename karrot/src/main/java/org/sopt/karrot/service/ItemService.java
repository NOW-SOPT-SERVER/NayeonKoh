package org.sopt.karrot.service;

import lombok.RequiredArgsConstructor;
import org.sopt.karrot.domain.Item;
import org.sopt.karrot.domain.Member;
import org.sopt.karrot.dto.request.ItemRegisterDto;
import org.sopt.karrot.repository.ItemRepository;
import org.sopt.karrot.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public void registerItem(final Long memberId, final ItemRegisterDto registerDto) {
        Member seller = memberRepository.findByIdOrThrow(memberId);
        Item item = Item.from(registerDto, seller);
        itemRepository.save(item);
    }
}
