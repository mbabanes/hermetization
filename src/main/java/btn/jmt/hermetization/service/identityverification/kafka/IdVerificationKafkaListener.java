package btn.jmt.hermetization.service.identityverification.kafka;

import btn.jmt.hermetization.service.identityverification.IdentityVerificationService;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
    name = IdVerificationListenerConfiguration.KAFKA_ENABLED_PROPERTY,
    havingValue = "true",
    matchIfMissing = true)
class IdVerificationKafkaListener {

  private final IdentityVerificationService identityVerificationService;

  @KafkaListener(
      topics = "#{'${id-verification.kafka.topic}'}",
      containerFactory = "idVerificationContainerFactory")
  void consume(@Payload IdVerificationMessage message) {
    if ("SUCCESS".equals(message.getState())) {
      identityVerificationService.confirmCustomerIdentity(
          new ProcessNumber(message.getProcessNumber()));
    }
  }
}
