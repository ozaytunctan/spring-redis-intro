package com.otunctan.controller;


import com.otunctan.dto.CreateEmployeeRequestDto;
import com.otunctan.dto.CreateEmployeeResponseDto;
import com.otunctan.dto.EditEmployeeRequestDto;
import com.otunctan.dto.EditEmployeeResponseDto;
import com.otunctan.dto.EmployeeDto;
import com.otunctan.service.EmployeeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/api/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "")
    public List<EmployeeDto> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }


    @PostMapping(path = "/create")
    public CreateEmployeeResponseDto getAllEmployees(@RequestBody CreateEmployeeRequestDto request) {
        return this.employeeService.createEmployee(request);
    }

    @PutMapping(path = "/edit/{id}")
    public EditEmployeeResponseDto editEmployee(@RequestBody EditEmployeeRequestDto request, @PathVariable(name = "id") Long id) {
        try {
            EditEmployeeResponseDto employee = this.employeeService.editEmployeeById(request, id);

            return employee;

        } catch (EntityNotFoundException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    @DeleteMapping(path = "/delete/{id}")
    public boolean delete(@PathVariable(name = "id") Long id) {
        return this.employeeService.deleteEmployeeId(id);
    }


}
