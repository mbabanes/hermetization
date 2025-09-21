package btn.jmt.hermetization.exception;

import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import lombok.Getter;

@Getter
public abstract class NotFoundException extends RuntimeException {

  private final ProcessNumber processNumber;

  public NotFoundException(ProcessNumber processNumber, String message) {
    super(message);
    this.processNumber = processNumber;
  }
}
