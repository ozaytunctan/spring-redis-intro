package com.otunctan.config;

import com.otunctan.interceptor.HibernateCustomInterceptor;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

//@Configuration
public class InterceptorRegistration implements HibernatePropertiesCustomizer {

    private final HibernateCustomInterceptor hibernateCustomInterceptor;

    public InterceptorRegistration(HibernateCustomInterceptor hibernateCustomInterceptor) {
        this.hibernateCustomInterceptor = hibernateCustomInterceptor;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.interceptor", hibernateCustomInterceptor);
    }

}
