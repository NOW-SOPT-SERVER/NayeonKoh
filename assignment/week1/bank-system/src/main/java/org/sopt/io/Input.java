package org.sopt.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static String readCustomerName() {
        System.out.print("이름을 입력하세요: ");
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String readSelectedMenu() {
        System.out.println("1. 계좌 생성");
        System.out.println("2. 계좌 목록 조회");
        System.out.println("3. 계좌 이체");
        System.out.println("4. 입금");
        System.out.println("5. 출금");
        System.out.println("6. 종료");

        System.out.print("메뉴를 선택하세요: ");
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer readSelectedAccount() {
        System.out.print("거래를 원하는 계좌를 숫자로 입력하세요: ");
        try {
            return Integer.parseInt(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readAccountNumber() {
        System.out.println("계좌 번호를 입력하세요(XXXX-XXX-XXXXXX): ");
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readAccountNumberForRemittance() {
        System.out.println("이체하려는 계좌의 번호를 입력하세요(XXXX-XXX-XXXXXX): ");
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer readAmount() {
        System.out.print("금액을 입력하세요: ");
        try {
            return Integer.parseInt(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
