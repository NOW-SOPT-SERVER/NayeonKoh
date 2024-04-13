package org.sopt.config;

import org.sopt.domain.bank.Bank;

// 프로그램 초기 설정
public class AppConfig {

    private static final AppConfig APP_CONFIG = new AppConfig();
    public final Bank bank;

    private AppConfig() {
        this.bank = initBank();
    }

    public static AppConfig getInstance() {
        return APP_CONFIG;
    }

    private Bank initBank() {
        return new Bank();
    }
}
