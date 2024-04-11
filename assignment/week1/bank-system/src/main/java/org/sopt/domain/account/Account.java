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
        AccountValidator.validateAmountRange(amount);
        this.balance += amount;
    }

    public void withdraw(final int amount) {
        // 출금
        AccountValidator.validateAmountRange(amount);
        AccountValidator.validateAmountSufficiency(amount, this.balance);
        this.balance -= amount;
    }

    public Customer getAccountHolder() {
        return this.accountHolder;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public int getBalance() {
        return this.balance;
    }
}
