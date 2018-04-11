package ratings.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ratings.services.EstablishmentsService;
import ratings.services.EstablishmentsServiceImpl;

@Configuration
public class EstablishmentsConfig {
    @Value(value = "${headers.api_version.key}")
    private String apiVersionKey;
    @Value(value = "${headers.api_version.value}")
    private String apiVersionValue;
    @Value(value = "${uri.scheme}")
    private String scheme;
    @Value(value = "${uri.host}")
    private String host;
    @Value(value = "${establishments.uri.path}")
    private String path;
    @Value(value = "${establishments.uri.idQueryParam}")
    private String idQueryParam;
    @Value(value = "${establishments.uri.pageSizeQueryParam}")
    private String pageSizeQueryParam;

    @Bean
    EstablishmentsService establishmentsService(RestTemplate restTemplate) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme(scheme)
                .host(host)
                .path(path)
                .query(idQueryParam)
                .query(pageSizeQueryParam);

        return new EstablishmentsServiceImpl(apiVersionKey, apiVersionValue, builder, restTemplate);
    }
}
