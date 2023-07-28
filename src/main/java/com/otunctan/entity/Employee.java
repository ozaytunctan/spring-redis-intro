package com.otunctan.entity;


import com.otunctan.annotation.EncryptedString;
import com.otunctan.config.EmployeeCallbackListener;
import com.otunctan.convert.IdentityNumberConverter;
import com.otunctan.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@SequenceGenerator(name = "employeeIdGenerator", sequenceName = "SQ_EMPLOYEE", allocationSize = 1)
@EntityListeners(EmployeeCallbackListener.class)
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(generator = "employeeIdGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    //    @EncryptedString
    @Convert(converter = IdentityNumberConverter.class)
    @Column(name = "identity_number")
    private String identityNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }
}
