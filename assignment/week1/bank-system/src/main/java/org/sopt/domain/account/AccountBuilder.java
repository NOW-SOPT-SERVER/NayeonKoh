package org.sopt.domain.account;

import org.sopt.domain.customer.Customer;

// 계좌 객체 생성을 담당하는 빌더 클래스
public class AccountBuilder {
    private Customer accountHolder;
    private String accountNumber;
    private int balance;

    public AccountBuilder accountHolder(final Customer accountHolder) {
        this.accountHolder = accountHolder;
        return this;
    }

    public AccountBuilder accountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountBuilder balance(final int balance) {
        this.balance = balance;
        return this;
    }

    public Account build() {
        return new Account(accountHolder, accountNumber, balance);
    }
}
