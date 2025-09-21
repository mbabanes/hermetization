package btn.jmt.hermetization.service.employee.dto;

import btn.jmt.hermetization.service.process.dto.EmployeeId;
import btn.jmt.hermetization.service.process.dto.EmailAddress;

public record EmployeeData(
        EmployeeId employeeId, EmailAddress emailAddress, String firstName, String lastName) {}
