package btn.jmt.hermetization;

import btn.jmt.hermetization.configuration.KafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(KafkaProperties.class)
public class HermetizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HermetizationApplication.class, args);
	}

}
