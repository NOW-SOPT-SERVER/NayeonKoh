package org.sopt.domain.account.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.sopt.common.Constant;
import org.sopt.common.ErrorMessage;
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

    public static void validateAmountRange(final int amount) {
        if (amount < Constant.ZERO.value()) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_AMOUNT.getMessage());
        }
        if (amount > Constant.MAX_INT.value()) {
            throw new IllegalArgumentException(ErrorMessage.OVERFLOW_AMOUNT.getMessage());
        }
    }

    public static void validateAmountSufficiency(final int amount, final int balance) {
        if (amount > balance) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ENOUGH_BALANCE.getMessage());
        }
    }
}
