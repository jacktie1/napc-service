package org.apathinternational.faithpathrestful.config.listener;

import java.util.Date;

import org.apathinternational.faithpathrestful.entity.AuditableEntity;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.service.SessionService;
import org.apathinternational.faithpathrestful.service.UserService;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AuditListener implements PreInsertEventListener, PreUpdateEventListener {

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        User executor = sessionService.getAuthedUser();

        if(executor == null) {
            executor = userService.getUserById(-1L);
        }

        if (event.getEntity() instanceof User) {
            User entity = (User) event.getEntity();
            entity.setCreatedAt(new Date());
            entity.setCreatedBy(executor);
            entity.setModifiedAt(new Date());
            entity.setModifiedBy(executor);
        }
        else if (event.getEntity() instanceof AuditableEntity) {
            AuditableEntity entity = (AuditableEntity) event.getEntity();
            entity.setCreatedAt(new Date());
            entity.setCreatedBy(executor);
            entity.setModifiedAt(new Date());
            entity.setModifiedBy(executor);
        }

        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        User executor = sessionService.getAuthedUser();

        if(executor == null) {
            executor = userService.getUserById(-1L);
        }

        if (event.getEntity() instanceof AuditableEntity) {
            AuditableEntity entity = (AuditableEntity) event.getEntity();
            entity.setModifiedAt(new Date());
            entity.setModifiedBy(executor);
        }

        return false;
    }
}
