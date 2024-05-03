package org.sopt.karrot.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.karrot.domain.area.service.AreaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/areas")
public class MockAreaController {
    private final AreaService areaService;

    @PostMapping("/save")
    public void saveEmdArea() {
        areaService.saveEmdArea();
    }
}
