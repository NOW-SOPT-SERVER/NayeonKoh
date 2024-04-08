package org.sopt.domain.account.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.sopt.common.Regex;

public class AccountValidator {
    // 계좌 번호 형식 검증
    public static void validateAccountNumber(String number) {
        Pattern pattern = Pattern.compile(Regex.ACCOUNT_NUMBER_FORMAT.getRegex());
        Matcher matcher = pattern.matcher(number);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid account number.");
        }
    }
}
