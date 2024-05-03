package org.sopt.karrot.domain.area.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.sopt.karrot.domain.area.domain.EmdArea;
import org.sopt.karrot.domain.area.dto.BeopjeongdongDto;
import org.sopt.karrot.domain.area.repository.EmdAreaRepository;
import org.sopt.karrot.domain.area.util.RestClientUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AreaService {
    private final RestClientUtil restClientUtil;
    private final EmdAreaRepository emdAreaRepository;

    @Transactional
    public void saveEmdArea() {
        List<EmdArea> emdAreas = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            List<Map<String, ?>> areas = restClientUtil.getArea(i);
            for (Map<String, ?> area : areas) {
                BeopjeongdongDto beopjeongdongDto = BeopjeongdongDto.from(area);
                emdAreas.add(EmdArea.from(beopjeongdongDto));
            }
        }
        emdAreaRepository.saveAll(emdAreas);
    }
}
