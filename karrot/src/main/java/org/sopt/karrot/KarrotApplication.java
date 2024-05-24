package org.sopt.karrot;

import lombok.RequiredArgsConstructor;
import org.sopt.karrot.domain.area.service.AreaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KarrotApplication {

    public static void main(String[] args) {
        SpringApplication.run(KarrotApplication.class, args);
    }

}
