package btn.jmt.hermetization.configuration.externalService.fileservice;

import btn.jmt.hermetization.generated.fileservice2.ApiClient;
import btn.jmt.hermetization.generated.fileservice2.client.FilesApi;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
class FileService2ApiConfiguration {

  @Autowired private FileService2ApiProperties fileService2ApiProperties;

  @Bean
  FilesApi filesApi() {
    final var apiClient = new ApiClient(createRestTemplate());
    apiClient.setBasePath(fileService2ApiProperties.getAddress());
    return new FilesApi(apiClient);
  }

  private RestTemplate createRestTemplate() {
    var connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setDefaultConnectionConfig(
        ConnectionConfig.custom()
            .setConnectTimeout(
                Timeout.ofSeconds(fileService2ApiProperties.getConnectionTimeoutInSeconds()))
            .setSocketTimeout(
                Timeout.ofSeconds(fileService2ApiProperties.getConnectionTimeoutInSeconds()))
            .build());
    CloseableHttpClient httpClient =
        HttpClients.custom()
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(
                RequestConfig.custom()
                    .setConnectionRequestTimeout(
                        Timeout.ofSeconds(fileService2ApiProperties.getRequestTimeoutInSeconds()))
                    .build())
            .build();
    ClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory(httpClient);
    return new RestTemplate(requestFactory);
  }
}
