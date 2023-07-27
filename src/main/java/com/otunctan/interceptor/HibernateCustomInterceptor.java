package com.otunctan.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class HibernateCustomInterceptor extends EmptyInterceptor {


    private final Logger logger = LoggerFactory.getLogger(HibernateCustomInterceptor.class);

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {

        logger.info(String.format("%s entity flush ",entity.getClass().getSimpleName()));
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

        logger.info(String.format("%s entity save  ",entity.getClass().getSimpleName()));
        return super.onSave(entity, id, state, propertyNames, types);
    }
}
