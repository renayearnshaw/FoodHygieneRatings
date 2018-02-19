package ratings.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ratings.services.EstablishmentsService;
import ratings.services.EstablishmentsServiceImpl;

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
