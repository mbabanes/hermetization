package btn.jmt.hermetization.service.employee;

import btn.jmt.hermetization.service.employee.dto.EmployeeData;
import btn.jmt.hermetization.service.employee.dto.SupervisorData;
import btn.jmt.hermetization.service.process.dto.EmployeeId;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
class EmployeeServiceImpl implements EmployeeService {
  @Override
  public EmployeeData findConsultant(EmployeeId employeeId) {

    return null;
  }

  @Override
  public Optional<EmployeeData> findActiveEmployeeDataById(EmployeeId employeeId) {
    return Optional.empty();
  }

  @Override
  public Optional<SupervisorData> findSupervisorData(EmployeeId employeeId) {
    return Optional.empty();
  }
}
