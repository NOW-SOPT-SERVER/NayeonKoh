package org.sopt;

import java.io.IOException;
import org.sopt.domain.bank.Bank;
import org.sopt.config.AppConfig;

public class Application {

    private final Bank bank;

    private Application(final AppConfig appConfig) {
        this.bank = appConfig.bank;
    }

    private void run() {
        bank.run();
    }

    public static void main(String[] args) {
        Application application = new Application(AppConfig.getInstance());
        application.run();
    }

}