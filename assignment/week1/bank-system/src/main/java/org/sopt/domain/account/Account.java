package org.sopt.domain.account;

import org.sopt.common.Constant;
import org.sopt.common.ErrorMessage;
import org.sopt.domain.account.validator.AccountValidator;
import org.sopt.domain.customer.Customer;

public class Account {

    private Customer accountHolder;
    private String accountNumber;
    private int balance;

    public Account(final Customer accountHolder, final String accountNumber, final int balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public static Account create(final Customer accountHolder, final String accountNumber, final int balance) {
        // 계좌번호 형식 검증
        AccountValidator.validateAccountNumber(accountNumber);

        // 계좌 생성 및 반환
        return new AccountBuilder()
                .accountHolder(accountHolder)
                .accountNumber(accountNumber)
                .balance(balance)
                .build();
    }

    public void deposit(final int amount) {
        // 입금
        if (amount < Constant.ZERO.value()) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_AMOUNT.getMessage());
        }
        if (amount > Constant.MAX_INT.value()) {
            throw new IllegalArgumentException(ErrorMessage.OVERFLOW_AMOUNT.getMessage());
        }
        balance += amount;
    }

    public void withdraw(final int amount) {
        // 출금
        if (amount < Constant.ZERO.value()) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_AMOUNT.getMessage());
        }
        if (amount > Constant.MAX_INT.value()) {
            throw new IllegalArgumentException(ErrorMessage.OVERFLOW_AMOUNT.getMessage());
        }
        if (amount > balance) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ENOUGH_BALANCE.getMessage());
        }
        balance -= amount;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }
}
