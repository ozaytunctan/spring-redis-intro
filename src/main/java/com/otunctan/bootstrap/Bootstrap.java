package com.otunctan.bootstrap;

import com.otunctan.entity.Employee;
import com.otunctan.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Bootstrap implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public Bootstrap(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Employee employee = new Employee();
        employee.setPhone("05380110467");
        employee.setLastName("tunçtan");
        employee.setFirstName("özay");

        employeeRepository.save(employee);

    }
}
