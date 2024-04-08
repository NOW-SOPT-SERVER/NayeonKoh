package org.sopt.common;

public enum Constant {
    ZERO(0),
    MAX_INT(2_000_000_000),
    ;

    private int value;
    private Constant(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
