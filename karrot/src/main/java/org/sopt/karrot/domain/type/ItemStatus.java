package org.sopt.karrot.domain.type;

public enum ItemStatus {
    SALE("판매 중"),
    RESERVED("예약 중"),
    SOLD_OUT("판매 완료"),
    DELETED("삭제됨");

    private final String status;

    ItemStatus(final String status) {
        this.status = status;
    }
}
