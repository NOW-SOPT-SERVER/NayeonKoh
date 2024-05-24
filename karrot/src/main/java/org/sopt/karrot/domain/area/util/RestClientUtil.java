package org.sopt.karrot.domain.area.util;

import java.net.URI;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.sopt.karrot.domain.area.dto.BeopjeongdongDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
public class RestClientUtil {

    private final RestClient restClient = RestClient.create();

    @Value("${public-api.url}")
    private String url;

    @Value("${public-api.authorization}")
    private String authorization;

    @Value("${public-api.serviceKey}")
    private String serviceKey;

    public List<Map<String, ?>> getArea(final int page) {
//        log.info("send POST request to get area data");
//        log.info("url: {}", url);

        // total data : 49861
        int perPage = 50;
        String uri = url + "?serviceKey=" + serviceKey
                + "&page=" + page
                + "&perPage=" + perPage;
        Object response = restClient.get()
                .uri(URI.create(uri))
                .header("Authorization", authorization)
                .retrieve()
                .toEntity(Map.class)
                .getBody()
                .get("data");
//        log.info("response: {}", response);

        return (List<Map<String, ?>>) response;
    }
}
