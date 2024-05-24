package org.sopt.karrot.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.karrot.domain.area.service.AreaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmdAreaRunner implements ApplicationRunner {

    private final AreaService areaService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Runner: save emd area data");
        areaService.saveEmdArea();
    }
}
