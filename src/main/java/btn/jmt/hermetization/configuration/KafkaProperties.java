package btn.jmt.hermetization.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties("kafka")
public record  KafkaProperties(
        String bootstrapServers,
        String securityProtocol,
        String saslMechanism,
        String saslJaasConfig
) {
    @ConstructorBinding
    public KafkaProperties {}
}
