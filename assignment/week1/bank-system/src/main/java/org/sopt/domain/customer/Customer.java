package org.sopt.domain.customer;

import java.util.ArrayList;
import java.util.List;
import org.sopt.domain.account.Account;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(final String name, final List<Account> accounts) {
        this.name = name;
        this.accounts = accounts;
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
