package com.otunctan.dto.base;

import java.io.Serializable;
import java.time.LocalDate;

public  abstract class BaseDto<ID extends Number> implements Serializable {

    private Long id;

    private LocalDate createdOn;

    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
