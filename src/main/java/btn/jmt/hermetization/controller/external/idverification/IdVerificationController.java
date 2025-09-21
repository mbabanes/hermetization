package btn.jmt.hermetization.controller.external.idverification;

import btn.jmt.hermetization.controller.external.idverification.model.IdVerificationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class IdVerificationController {

    @GetMapping("x-service-data")
    IdVerificationResponse getData(@RequestParam String id) {
        return new IdVerificationResponse();
    }
}
