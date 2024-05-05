package org.sopt.karrot.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.karrot.dto.common.ResponseDto;
import org.sopt.karrot.dto.request.ItemRegisterDto;
import org.sopt.karrot.dto.response.ItemsDto;
import org.sopt.karrot.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    /* TODO: 토큰에서 멤버 아이디 가져오기 */
    @PostMapping("/members/{memberId}")
    public ResponseDto<?> registerItem(@PathVariable(name = "memberId") final Long memberId, @RequestBody @Valid final ItemRegisterDto registerDto) {
        itemService.registerItem(memberId, registerDto);
        return ResponseDto.success(null);
    }

    @GetMapping("/location/{locationId}")
    public ResponseDto<List<ItemsDto>> getItemsByLocation(@PathVariable(name = "locationId") final Long locationId) {
        return ResponseDto.success(itemService.findItemsByLocation(locationId));
    }
}
