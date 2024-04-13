package org.sopt.common;

public enum ErrorMessage {
    OVERFLOW_AMOUNT("거래 금액은 20억원 이하여야 합니다."),
    NEGATIVE_AMOUNT("거래 금액은 0원 이상이어야 합니다."),
    ILLEGAL_USER_INPUT("잘못된 사용자 입력입니다."),
    NO_TARGET_ACCOUNT("이체하려는 계좌가 존재하지 않습니다."),
    NOT_ENOUGH_BALANCE("잔액이 부족합니다."),
    ;
    private final String message;
    private ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
