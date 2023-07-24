package com.otunctan.mapper;

import com.otunctan.dto.EmployeeDto;
import com.otunctan.entity.Employee;

public final class ValueMapper {

    public static EmployeeDto mapToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setActive(employee.isActive());
        employeeDto.setCreatedOn(employee.getCreatedOn());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setPhone(employee.getPhone());
        return employeeDto;
    }
}
