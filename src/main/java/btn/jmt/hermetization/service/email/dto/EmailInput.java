package btn.jmt.hermetization.service.email.dto;

import btn.jmt.hermetization.service.process.dto.EmailAddress;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;

public record EmailInput(
        ProcessNumber processNumber,
        EmailAddress emailAddress,
        String emailSubject,
        String emailSender,
        String emailText
) {}
