package btn.jmt.hermetization.service.process;

import btn.jmt.hermetization.service.common.AuditableEntity;
import btn.jmt.hermetization.service.process.dto.ProcessDetails;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import btn.jmt.hermetization.service.process.dto.ProcessState;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
class ProcessEntity extends AuditableEntity {

  @Id private String id;
  private String processNumber;
  private ProcessState state;

  static ProcessEntity createNew(ProcessNumber processNumber) {
    final var processEntity = new ProcessEntity();
    processEntity.setId(UUID.randomUUID().toString());
    processEntity.setProcessNumber(processNumber.value());
    processEntity.setState(ProcessState.NEW);
    return processEntity;
  }

  ProcessDetails toDto() {
    return new ProcessDetails(new ProcessNumber(getProcessNumber()), getState());
  }
}
