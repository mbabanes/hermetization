package btn.jmt.hermetization.service.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
class EmailServiceConfiguration {

    @Bean
    @Profile("local")
    EmailService localhostEmailService() {
        log.info("LocalEmailService bean is created");
        return new LocalhostEmailService();
    }


    @Bean
    @Profile("prd")
    EmailService productionEmailService() {
        log.info("ProductionEmailService bean is created");
        return new ProductionEmailService();
    }
}
