package org.sopt.karrot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.sopt.karrot.domain.area.domain.EmdArea;
import org.sopt.karrot.domain.type.ItemCategory;
import org.sopt.karrot.domain.type.ItemStatus;
import org.sopt.karrot.domain.type.TradingMethod;
import org.sopt.karrot.dto.request.ItemRegisterDto;

@Entity
@Getter
@DynamicInsert
@Table(name = "items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "trading_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private TradingMethod tradingMethod;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    /* 가격 제안 가능 여부 */
    @Column(name = "price_suggestion", nullable = false)
    private boolean priceSuggestion;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    /* 상품 판매 상태 */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private EmdArea location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member seller;

    @Column(nullable = false)
    private Integer likes = 0;

    public static Item from(final ItemRegisterDto registerDto, final Member seller, final EmdArea location) {
        return Item.builder()
                .seller(seller)
                .title(registerDto.title())
                .tradingMethod(registerDto.tradingMethod())
                .category(registerDto.category())
                .priceSuggestion(registerDto.priceSuggestion())
                .price(registerDto.price())
                .content(registerDto.content())
                .status(ItemStatus.SALE)
                .location(location)
                .build();
    }

    @Builder
    public Item(final String title, final TradingMethod tradingMethod, final ItemCategory category, final boolean priceSuggestion, final Integer price, final String content, final EmdArea location, final Member seller, final ItemStatus status) {
        this.title = title;
        this.tradingMethod = tradingMethod;
        this.category = category;
        this.priceSuggestion = priceSuggestion;
        this.price = price;
        this.content = content;
        this.status = status;
        this.location = location;
        this.seller = seller;
    }
}
