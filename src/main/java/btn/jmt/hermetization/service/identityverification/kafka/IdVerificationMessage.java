package btn.jmt.hermetization.service.identityverification.kafka;

import lombok.Data;

@Data
class IdVerificationMessage {
    private String processNumber;
    private String state;
}
