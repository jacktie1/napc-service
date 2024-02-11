package org.apathinternational.faithpathrestful.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reference")
public class Reference extends AuditableEntity {

    @Id
    @Column(name = "reference_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference_type", nullable = false)
    private String referenceType;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "alternate_value")
    private String alternateValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_reference_id")
    private Reference parentReference;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAlternateValue() {
        return alternateValue;
    }

    public void setAlternateValue(String alternateValue) {
        this.alternateValue = alternateValue;
    }

    public Reference getParentReference() {
        return parentReference;
    }

    public void setParentReferenceId(Reference parentReference) {
        this.parentReference = parentReference;
    }

}
