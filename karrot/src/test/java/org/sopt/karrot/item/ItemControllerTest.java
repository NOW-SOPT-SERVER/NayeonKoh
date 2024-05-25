package org.sopt.karrot.item;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.sopt.karrot.util.MockMVCUtils;
import org.sopt.karrot.controller.ItemController;
import org.sopt.karrot.domain.Item;
import org.sopt.karrot.domain.Member;
import org.sopt.karrot.domain.area.domain.EmdArea;
import org.sopt.karrot.domain.type.ItemCategory;
import org.sopt.karrot.domain.type.ItemStatus;
import org.sopt.karrot.domain.type.TradingMethod;
import org.sopt.karrot.dto.response.ItemDto;
import org.sopt.karrot.service.ItemService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ItemControllerTest extends MockMVCUtils {

    private final static String ITEM_BASE_URL = "/api/v1/items";
    private final static String ITEM_BY_LOCATION_URL = "/location/{locationId}";
    private final static String ITEM_BY_ID_URL = "/{itemId}";

    private final ItemService itemService = Mockito.mock(ItemService.class);
    private final ItemController itemController = new ItemController(itemService);

    private final static Long ITEM_ID = 1L;
    private final static Long LOCATION_ID = 1L;
    private final static Item ITEM_RESULT = Item.builder()
            .title("아이템 제목")
            .content("아이템 내용")
            .category(ItemCategory.BOOK)
            .tradingMethod(TradingMethod.SELL)
            .status(ItemStatus.SALE)
            .price(10000)
            .priceSuggestion(true)
            .seller(Member.builder().name("판매자").soldItems(new ArrayList<>()).build())
            .location(EmdArea.builder().name("서울시 강남구").code(10000L).build())
            .build();

    @BeforeEach
    public void setUp() {
        mockMvc = mockController(itemController);
    }

    @DisplayName("특정 아이템을 id 값으로 조회")
    @Test
    public void getItemById() throws Exception {
        Mockito.when(itemService.findItemById(1L))
                .thenReturn(ItemDto.from(ITEM_RESULT));

        ResultActions resultActions = whenGet(ITEM_BASE_URL + ITEM_BY_ID_URL, ITEM_ID);

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(toJson(ItemDto.from(ITEM_RESULT))));
    }

    @DisplayName("특정 아이템을 지역(location) 값으로 조회")
    @Test
    public void getItemsByLocation() {
        // TODO: Implement
    }

    @DisplayName("")
    @Test
    public void getItems() {
        // TODO: Implement
    }
}
