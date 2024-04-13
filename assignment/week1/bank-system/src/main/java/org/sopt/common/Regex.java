package org.sopt.common;

public enum Regex {
    ACCOUNT_NUMBER_FORMAT("^[0-9]{4}-[0-9]{3}-[0-9]{6}$"),
    ;

    private final String regex;
    private Regex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
