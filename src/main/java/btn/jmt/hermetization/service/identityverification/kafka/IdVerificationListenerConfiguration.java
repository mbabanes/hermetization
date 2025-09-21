package btn.jmt.hermetization.service.identityverification.kafka;

import btn.jmt.hermetization.configuration.KafkaProperties;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
@EnableKafka
@ConditionalOnProperty(
    name = IdVerificationListenerConfiguration.KAFKA_ENABLED_PROPERTY,
    havingValue = "true",
    matchIfMissing = true)
class IdVerificationListenerConfiguration {

  static final String KAFKA_ENABLED_PROPERTY = "id-verification.kafka.enabled";

  @Value("${id-verification.kafka.group-id}")
  private String groupId;

  @Autowired private KafkaProperties kafkaProperties;

  @Bean
  ConcurrentKafkaListenerContainerFactory<Object, Object> idVerificationContainerFactory() {
    final var containerFactory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
    containerFactory.setConsumerFactory(createConsumerFactory());
    containerFactory.setRecordMessageConverter(new StringJsonMessageConverter());
    return containerFactory;
  }

  private ConsumerFactory<Object, Object> createConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put("bootstrap.servers", kafkaProperties.bootstrapServers());
    props.put("key.deserializer", StringDeserializer.class);
    props.put("value.deserializer", StringDeserializer.class);
    props.put("group.id", groupId);
    props.put("auto.offset.reset", "latest");
    props.put("security.protocol", kafkaProperties.securityProtocol());
    props.put("sasl.mechanism", kafkaProperties.saslMechanism());
    props.put("sasl.jaas.config", kafkaProperties.saslJaasConfig());

    return new DefaultKafkaConsumerFactory<>(props);
  }
}
