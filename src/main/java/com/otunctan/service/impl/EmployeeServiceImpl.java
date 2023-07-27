package com.otunctan.service.impl;

import com.otunctan.dto.CreateEmployeeRequestDto;
import com.otunctan.dto.CreateEmployeeResponseDto;
import com.otunctan.dto.EditEmployeeRequestDto;
import com.otunctan.dto.EditEmployeeResponseDto;
import com.otunctan.dto.EmployeeDto;
import com.otunctan.entity.Employee;
import com.otunctan.exception.CreateEmployeeException;
import com.otunctan.mapper.ValueMapper;
import com.otunctan.repository.EmployeeRepository;
import com.otunctan.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<EmployeeDto> getAllEmployees() {
        return this.employeeRepository.findAll()//
                .stream().map(ValueMapper::mapToDto)//
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CreateEmployeeResponseDto createEmployee(CreateEmployeeRequestDto request) {

        if (Objects.isNull(request)) {
            throw new CreateEmployeeException("Müşteri oluşturulma hatası. Lütfen müşteri bilgilerini giriniz.");
        }

        Employee employeeEntity = new Employee();
        employeeEntity.setFirstName(request.getFirstName());
        employeeEntity.setLastName(request.getLastName());
        employeeEntity.setPhone(request.getPhone());

        this.employeeRepository.save(employeeEntity);

        CreateEmployeeResponseDto response = new CreateEmployeeResponseDto();
        response.setId(employeeEntity.getId());
        response.setFirstName(employeeEntity.getFirstName());
        response.setLastName(employeeEntity.getLastName());
        response.setPhone(employeeEntity.getPhone());
        response.setActive(employeeEntity.isActive());
        response.setCreatedOn(employeeEntity.getCreatedOn());
        return response;
    }

    @Override
    public EditEmployeeResponseDto editEmployeeById(EditEmployeeRequestDto request, Long id) {

        Employee employeeEntity = this.employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Müşteri Bilgisi Bulunamadı.! "));

        employeeEntity.setFirstName(request.getFirstName());
        employeeEntity.setLastName(request.getLastName());
        employeeEntity.setPhone(request.getLastName());

        Employee editEmployee = this.employeeRepository.save(employeeEntity);

        EditEmployeeResponseDto editEmployeeResponse = new EditEmployeeResponseDto();
        editEmployeeResponse.setId(editEmployee.getId());
        editEmployeeResponse.setFirstName(editEmployee.getFirstName());
        editEmployeeResponse.setLastName(editEmployee.getLastName());
        editEmployeeResponse.setPhone(editEmployee.getPhone());
        editEmployeeResponse.setActive(editEmployee.isActive());
        editEmployeeResponse.setCreatedOn(editEmployee.getCreatedOn());


        if (Objects.nonNull(editEmployee.getId())) {
            throw new IllegalArgumentException("employee update error");
        }

        return editEmployeeResponse;
    }

    @Override
    public boolean deleteEmployeeId(Long id) {
        this.employeeRepository.deleteById(id);
        return true;
    }
}
