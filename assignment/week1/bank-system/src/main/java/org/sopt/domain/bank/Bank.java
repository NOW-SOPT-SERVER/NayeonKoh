package org.sopt.domain.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.sopt.common.ErrorMessage;
import org.sopt.domain.account.Account;
import org.sopt.domain.customer.Customer;
import org.sopt.io.Input;
import org.sopt.io.Output;

// 은행 서비스
public class Bank {

    private List<Customer> customers = new ArrayList<>();

    public void run() {
        // 고객 이름 입력
        String name = Input.readCustomerName();
        Customer customer = null;

        // 이미 존재하는 고객인지 확인 - 항상 존재하지 않음 (DB x)
        for (Customer cstm : customers) {
            if (cstm.getName().equals(name)) {
                customer = cstm;
            }
        }

        // 고객 생성 및 저장
        // 한 번만 검사되는 조건문 -> 성능보다는 가독성을 고려하여 isNull로 대체
        if (Objects.isNull(customer)) { // 항상 true
            customer = new Customer(name);
            customers.add(customer);
        }

        while (true) {
            // 사용자로부터 이용하려는 서비스 입력
            String customerOption = Input.readSelectedMenu();
            if (customerOption.contains("1")) {
                // 계좌 생성
                String accountNumber = Input.readAccountNumber();
                Account account = Account.create(customer, accountNumber, 0);
                customer.getAccounts().add(account);
                Output.printAccountCreation(account);
            } else if (customerOption.contains("2")) {
                // 계좌 목록 조회
                Output.printAccountList(customer);
            } else if (customerOption.contains("3")) {
                // 계좌 이체

                // 이체 시 돈을 출금하는 계좌 선택
                Output.printAccountList(customer);
                Integer selectedAccount = Input.readSelectedAccount();
                Account currentAccount = customer.getAccounts().get(selectedAccount - 1);

                // 이체하려는 계좌 번호 입력
                String accountNumber = Input.readAccountNumberForRemittance();
                Account targetAccount = null;
                for (Customer _customer : customers) {
                    for (Account account : _customer.getAccounts()) {
                        // 이체하려는 계좌가 존재하는 경우
                        if (account.getAccountNumber().equals(accountNumber)) {
                            targetAccount = account;
                            int amount = Input.readAmount();
                            currentAccount.withdraw(amount);
                            targetAccount.deposit(amount);

                            Output.printBalance(currentAccount);
                            break;
                        }
                    }
                }
                // 이체하려는 계좌가 없는 경우
                if (targetAccount == null) {
                    throw new IllegalArgumentException(ErrorMessage.NO_TARGET_ACCOUNT.getMessage());
                }
            } else if (customerOption.contains("4")) {
                // 입금
                Integer amount = Input.readAmount();

                Output.printAccountList(customer);
                Integer selectedAccount = Input.readSelectedAccount();
                Account currentAccount = customer.getAccounts().get(selectedAccount - 1);
                currentAccount.deposit(amount);

                Output.printBalance(currentAccount);
            } else if (customerOption.contains("5")) {
                // 출금
                Integer amount = Input.readAmount();

                Output.printAccountList(customer);
                Integer selectedAccount = Input.readSelectedAccount();
                Account currentAccount = customer.getAccounts().get(selectedAccount - 1);
                currentAccount.withdraw(amount);

                Output.printBalance(currentAccount);
            } else if (customerOption.contains("6")) {
                // 종료
                break;
            } else {
                throw new IllegalArgumentException(ErrorMessage.ILLEGAL_USER_INPUT.getMessage());
            }
        }
    }
}
