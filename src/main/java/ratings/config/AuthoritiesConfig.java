package ratings.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ratings.services.AuthoritiesService;
import ratings.services.AuthoritiesServiceImpl;

@Configuration
public class AuthoritiesConfig {
    @Value(value = "${authorities.url}")
    private String uri;
    @Value(value = "${headers.api_version.key}")
    private String apiVersionKey;
    @Value(value = "${headers.api_version.value}")
    private String apiVersionValue;
    @Value(value = "${authorities.pageNumber}")
    private int pageNumber;
    @Value(value = "${authorities.pageSize}")
    private int pageSize;

    @Bean
    AuthoritiesService authoritiesService(RestTemplate restTemplate) {
        return new AuthoritiesServiceImpl(apiVersionKey, apiVersionValue, pageNumber, pageSize, uri, restTemplate);
    }
}
