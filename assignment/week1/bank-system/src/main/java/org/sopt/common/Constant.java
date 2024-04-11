package org.sopt.common;

public enum Constant {
    ZERO(0),
    MAX_INT(2_000_000_000),

    MENU_OPTION_1(1),
    MENU_OPTION_2(2),
    MENU_OPTION_3(3),
    MENU_OPTION_4(4),
    MENU_OPTION_5(5),
    MENU_OPTION_6(6),
    ;

    private final int value;
    private Constant(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public String toString() { return String.valueOf(value); }
}
