package com.otunctan.service;

import com.otunctan.constants.CacheNameConstant;
import com.otunctan.dto.CreateEmployeeRequestDto;
import com.otunctan.dto.CreateEmployeeResponseDto;
import com.otunctan.dto.EditEmployeeRequestDto;
import com.otunctan.dto.EditEmployeeResponseDto;
import com.otunctan.dto.EmployeeDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeService {

    @Cacheable(cacheNames = CacheNameConstant.EMPLOYEES_CACHE, key = "'all'")
    List<EmployeeDto> getAllEmployees();

    @CacheEvict(cacheNames = CacheNameConstant.EMPLOYEES_CACHE, key = "'all'")
    CreateEmployeeResponseDto createEmployee(CreateEmployeeRequestDto request);


    @CachePut(cacheNames = CacheNameConstant.EMPLOYEES_CACHE, key = "#id")
//    @Transactional
    EditEmployeeResponseDto editEmployeeById(EditEmployeeRequestDto request, Long id);


    @Caching(
            evict = {
                    @CacheEvict(value = CacheNameConstant.EMPLOYEES_CACHE, allEntries = true)
            }
    )
    boolean deleteEmployeeId(Long id);
}
