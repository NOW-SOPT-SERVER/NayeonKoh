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
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.karrot.domain.type.ItemCategory;
import org.sopt.karrot.domain.type.ItemStatus;
import org.sopt.karrot.domain.type.TradingMethod;

@Entity
@Getter
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
    private ItemStatus status;

    /* TODO: 이후 로케이션 클래스 분리 */
    @Column(name = "location", nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member seller;
}
