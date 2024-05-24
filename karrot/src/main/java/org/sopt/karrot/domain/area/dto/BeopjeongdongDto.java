package org.sopt.karrot.domain.area.dto;

import java.util.Map;
import org.sopt.karrot.exception.CommonException;
import org.sopt.karrot.exception.ErrorCode;

public record BeopjeongdongDto(
        Long code,
        String sigunguName,
        String sidoName,
        String emdName
) {
    public static BeopjeongdongDto from(Map<String, ?> area) {
        try {
            return new BeopjeongdongDto(
                    (Long) area.get("법정동코드"),
                    (String) area.get("시군구명"),
                    (String) area.get("시도명"),
                    (String) area.get("읍면동명")
            );
        } catch (Exception e) {
            return new BeopjeongdongDto(
                    (long)(int) area.get("법정동코드"),
                    (String) area.get("시군구명"),
                    (String) area.get("시도명"),
                    (String) area.get("행정동명")
            );
        }
    }
}