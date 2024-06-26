package org.sopt.karrot.item;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.sopt.karrot.exception.CommonException;
import org.sopt.karrot.exception.ErrorCode;
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
    private final static Item ITEM_RESULT = Item.builder()
            .title("item title")
            .content("item content")
            .category(ItemCategory.BOOK)
            .tradingMethod(TradingMethod.SELL)
            .status(ItemStatus.SALE)
            .price(10000)
            .priceSuggestion(true)
            .seller(Member.builder().name("seller").soldItems(new ArrayList<>()).build())
            .location(EmdArea.builder().name("seoul").code(10000L).build())
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
//        System.out.println(resultActions.andReturn().getResponse().getContentAsString());

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.category").value(ItemCategory.BOOK.name()))
                .andExpect(jsonPath("$.data.content").value("item content"))
                .andExpect(jsonPath("$.data.location.name").value("seoul"))
                .andExpect(jsonPath("$.data.price").value(10000))
                .andExpect(jsonPath("$.data.priceSuggestion").value(true))
                .andExpect(jsonPath("$.data.seller.name").value("seller"))
                .andExpect(jsonPath("$.data.tradingMethod").value(TradingMethod.SELL.name()))
                .andExpect(jsonPath("$.data.title").value("item title"));
    }

    @DisplayName("특정 아이템을 id 값으로 조회 - 존재하지 않는 아이템")
    @Test
    public void getItemById_NotFound() throws Exception {
        Mockito.when(itemService.findItemById(100L))
                .thenThrow(new CommonException(ErrorCode.NOT_FOUND_ITEM));

        ResultActions resultActions = whenGet(ITEM_BASE_URL + ITEM_BY_ID_URL, 100L);
//        System.out.println(resultActions.andReturn().getResponse().getContentAsString());

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.status").value(ErrorCode.NOT_FOUND_ITEM.getStatus()))
                .andExpect(jsonPath("$.error.message").value(ErrorCode.NOT_FOUND_ITEM.getMessage()));
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
