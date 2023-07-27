package com.otunctan.config;

import com.otunctan.listeners.PostLoadListener;
import com.otunctan.listeners.PreInsertListener;
import com.otunctan.listeners.PreUpdateListener;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

//@Component
public class ListenerRegistration implements BeanPostProcessor {


    private final PreInsertListener preInsertListener;

    private final PreUpdateListener preUpdateListener;

    private final PostLoadListener postLoadListener;

    public ListenerRegistration(PreInsertListener preInsertListener, PreUpdateListener preUpdateListener, PostLoadListener postLoadListener) {
        this.preInsertListener = preInsertListener;
        this.preUpdateListener = preUpdateListener;
        this.postLoadListener = postLoadListener;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {


        if (bean instanceof LocalContainerEntityManagerFactoryBean) {
            LocalContainerEntityManagerFactoryBean lcemf = (LocalContainerEntityManagerFactoryBean) bean;
            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) lcemf.getNativeEntityManagerFactory();
            EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
            registry.appendListeners(EventType.POST_LOAD, postLoadListener);
            registry.appendListeners(EventType.PRE_INSERT, preInsertListener);
            registry.appendListeners(EventType.PRE_UPDATE, preUpdateListener);
        }


        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
