package btn.jmt.hermetization.service.employee;

import btn.jmt.hermetization.service.employee.dto.EmployeeData;
import btn.jmt.hermetization.service.employee.dto.SupervisorData;
import btn.jmt.hermetization.service.process.dto.EmployeeId;

import java.util.Optional;

public interface EmployeeService {

  EmployeeData findConsultant(EmployeeId employeeId);

  Optional<EmployeeData> findActiveEmployeeDataById(EmployeeId employeeId);

  Optional<SupervisorData> findSupervisorData(EmployeeId employeeId);
}
