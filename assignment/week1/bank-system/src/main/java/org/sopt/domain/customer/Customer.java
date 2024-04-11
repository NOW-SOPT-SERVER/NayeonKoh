package org.sopt.domain.customer;

import java.util.ArrayList;
import java.util.List;
import org.sopt.domain.account.Account;
import org.sopt.io.Input;
import org.sopt.io.Output;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(final String name, final List<Account> accounts) {
        this.name = name;
        this.accounts = accounts;
    }

    public void createAccount() {
        String accountNumber = Input.readAccountNumber();
        Account account = Account.create(this, accountNumber, 0);
        this.getAccounts().add(account);
        Output.printAccountCreation(account);
    }

    public Customer(final String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }
}
