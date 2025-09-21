package btn.jmt.hermetization.service.process;

import btn.jmt.hermetization.service.process.dto.ProcessState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

interface ProcessRepository extends JpaRepository<ProcessEntity, String> {

  @Modifying
  @Transactional
  @Query("UPDATE ProcessEntity SET state = :state WHERE processNumber = :processNumber")
  void updateStateByProcessNumber(String processNumber, ProcessState state);

  ProcessState getProcessStateByProcessNumber(String processNumber);
}
