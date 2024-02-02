package org.apathinternational.faithpathrestful.entity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public abstract class AuditableEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User created_by;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private Date modified_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by")
    private User modified_by;

    // getters and setters
    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public User getCreatedBy() {
        return created_by;
    }

    public void setCreatedBy(User created_by) {
        this.created_by = created_by;
    }

    public Date getModifiedAt() {
        return modified_at;
    }

    public void setModifiedAt(Date modified_at) {
        this.modified_at = modified_at;
    }

    public User getModifiedBy() {
        return modified_by;
    }

    public void setModifiedBy(User modified_by) {
        this.modified_by = modified_by;
    }
}