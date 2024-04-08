package org.sopt.io;

import java.util.List;
import org.sopt.domain.account.Account;
import org.sopt.domain.customer.Customer;

public class Output {

    public static void printAccountCreation(Account account) {
        System.out.println(account.getAccountHolder() + "님의 계좌가 개설되었습니다.");
        System.out.println("계좌번호: " + account.getAccountNumber());
        System.out.println("잔액: " + account.getBalance() + "\n");
    }

    public static void printAccountList(Customer customer) {
        List<Account> accounts = customer.getAccounts();
        if (accounts.isEmpty()) {
            System.out.println("아직 개설된 계좌가 없습니다." + "\n");
            return;
        }

        int idx = 0;
        for (Account acc: accounts) {
            System.out.println(++idx + "번째 계좌");
            System.out.println("계좌번호: " + acc.getAccountNumber());
            System.out.println("잔액: " + acc.getBalance() + "\n");
        }
    }

    public static void printBalance(Account account) {
        System.out.println("계좌번호: " + account.getAccountNumber());
        System.out.println("잔액: " + account.getBalance() + "\n");
    }
}
