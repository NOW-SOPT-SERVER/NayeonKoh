package org.sopt.practice.common.dto;

import lombok.Getter;

public class Constant {
    public static final int ONE = 1;
    public static final int MAX_NAME_LENGTH = 20;
    public static final int MAX_TITLE_LENGTH = 200;
    public static final int MAX_AGE = 500;
    public static final int MAX_DESCRIPTION_LENGTH = 500;

    /* max content length - 2MB */
    public static final int MAX_CONTENT_LENGTH = 2 * 1024 * 1024;
}
