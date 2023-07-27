package com.otunctan.config;

import com.otunctan.entity.Employee;
import com.otunctan.entity.base.BaseEntity;
import com.otunctan.service.EncryptionService;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Jpa Callback class
 */
public class EmployeeCallbackListener {


    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(Employee entity) {
        System.out.println("Pre Insert Or Update");
        entity.setIdentityNumber(getEncryptionService().encrypt(entity.getIdentityNumber()));
    }

    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(Employee entity) {
        System.out.println("Post Load was called...");
        entity.setIdentityNumber(getEncryptionService().decrypt(entity.getIdentityNumber()));
    }

    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}
