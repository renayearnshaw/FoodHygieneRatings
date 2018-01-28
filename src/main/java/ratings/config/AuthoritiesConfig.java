package ratings.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ratings.service.AuthoritiesService;
import ratings.service.AuthoritiesServiceImpl;

@Configuration
public class AuthoritiesConfig {
    @Value(value = "${authorities.url}")
    private String uri;
    @Value(value = "${headers.api_version.key}")
    private String apiVersionKey;
    @Value(value = "${headers.api_version.value}")
    private String apiVersionValue;

    @Bean
    AuthoritiesService getAuthoritiesService() {
        return new AuthoritiesServiceImpl(apiVersionKey, apiVersionValue, uri);
    }
}
