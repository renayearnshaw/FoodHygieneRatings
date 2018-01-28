package ratings.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ratings.service.EstablishmentsService;
import ratings.service.EstablishmentsServiceImpl;

@Configuration
public class EstablishmentsConfig {
    @Value(value = "${establishments.url}")
    private String uri;
    @Value(value = "${headers.api_version.key}")
    private String apiVersionKey;
    @Value(value = "${headers.api_version.value}")
    private String apiVersionValue;

    @Bean
    EstablishmentsService getEstablishmentsService() {
        return new EstablishmentsServiceImpl(apiVersionKey, apiVersionValue, uri);
    }
}
