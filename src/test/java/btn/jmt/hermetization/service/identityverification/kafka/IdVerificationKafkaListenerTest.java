package btn.jmt.hermetization.service.identityverification.kafka;

import btn.jmt.hermetization.service.identityverification.IdentityVerificationService;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class IdVerificationKafkaListenerTest {

  private final IdentityVerificationService identityVerificationService =
      mock(IdentityVerificationService.class);

  private final IdVerificationKafkaListener idVerificationKafkaListener =
      new IdVerificationKafkaListener(identityVerificationService);

  @Test
  void shouldConfirmCustomerIdWhenStateIsSuccess() {
    // given
    final var message = new IdVerificationMessage();
    message.setState("SUCCESS");
    message.setProcessNumber("prNumber");

    // when
    idVerificationKafkaListener.consume(message);

    // then
    verify(identityVerificationService)
        .confirmCustomerIdentity(new ProcessNumber(message.getProcessNumber()));
  }
}
