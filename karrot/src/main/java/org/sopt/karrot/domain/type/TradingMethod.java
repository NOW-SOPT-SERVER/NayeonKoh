package org.sopt.karrot.domain.type;

public enum TradingMethod {
    SHARE("나눔"),
    SELL("판매"),
    ;

    private final String method;

    TradingMethod(final String method) {
        this.method = method;
    }
}
