package org.apathinternational.faithpathrestful.config.listener;

import java.util.Date;

import org.apathinternational.faithpathrestful.entity.AuditableEntity;
import org.apathinternational.faithpathrestful.service.SessionService;
import org.apathinternational.faithpathrestful.service.UserService;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.FlushModeType;


/*
 * This class is used to listen to the pre-insert and pre-update events of the hibernate
 * and set the created and modified by and at fields of the entity.
 */
@Component
public class AuditListener implements PreInsertEventListener, PreUpdateEventListener {

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        Long executorId = sessionService.getAuthedUserId();

        if(executorId == null) {
            executorId = -1L;
        }

        if (event.getEntity() instanceof AuditableEntity) {
            AuditableEntity entity = (AuditableEntity) event.getEntity();
            entity.setCreatedAt(new Date());
            entity.setCreatedBy(executorId);
            entity.setModifiedAt(new Date());
            entity.setModifiedBy(executorId);
        }

        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        Long executorId = sessionService.getAuthedUserId();
        EntityPersister persister = event.getPersister();
        
        if(executorId == null) {
            executorId = -1L;
        }

        // Somehow setting the modifiedBy and modifiedAt fields of the entity is not working
        if (event.getEntity() instanceof AuditableEntity) {
            String tableName = persister.getMappedTableDetails().getTableName();

            event.getSession().createNativeQuery("UPDATE " + tableName + " SET modified_at = :modifiedAt, modified_by = :modifiedBy WHERE " + tableName + "_id = :id", Void.class)
                .setParameter("modifiedAt", new Date())
                .setParameter("modifiedBy", executorId)
                .setParameter("id", event.getId())
                .setFlushMode(FlushModeType.COMMIT)
                .executeUpdate();
        }

        return false;
    }
}
