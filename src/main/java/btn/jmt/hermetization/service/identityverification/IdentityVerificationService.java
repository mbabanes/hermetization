package btn.jmt.hermetization.service.identityverification;

import btn.jmt.hermetization.service.process.ProcessService;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import btn.jmt.hermetization.service.process.dto.ProcessState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdentityVerificationService {

    private final ProcessService processService;

    public void confirmCustomerIdentity(ProcessNumber processNumber) {
        processService.updateProcessState(processNumber, ProcessState.ID_VERIFIED);
    }

}
