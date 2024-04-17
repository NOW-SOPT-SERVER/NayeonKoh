package org.sopt.karrot.domain.type;

public enum ItemCategory {

    CLOTHES("의류"),
    DIGITAL("디지털"),
    FURNITURE("가구"),
    LIVING("생활용품"),
    SPORTS("스포츠"),
    BOOK("도서"),
    FOOD("식품"),
    BEAUTY("뷰티"),
    ETC("기타"),
    ;

    private final String category;

    ItemCategory(String category) {
        this.category = category;
    }
}
