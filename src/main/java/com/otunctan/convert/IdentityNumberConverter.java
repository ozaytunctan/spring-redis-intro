package com.otunctan.convert;

import com.otunctan.config.SpringContextHelper;
import com.otunctan.service.EncryptionService;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class IdentityNumberConverter implements AttributeConverter<String,String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return getEncryptionService().encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return getEncryptionService().decrypt(dbData);
    }

    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}
