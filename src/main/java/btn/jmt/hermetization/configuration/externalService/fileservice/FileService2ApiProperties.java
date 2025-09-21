package btn.jmt.hermetization.configuration.externalService.fileservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("external-system.file-service2")
@Data
class FileService2ApiProperties {

    private String address = "http://file-service.pl/";
    private int requestTimeoutInSeconds = 30;
    private int connectionTimeoutInSeconds = 30;

}

