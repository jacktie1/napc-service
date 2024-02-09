package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.Reference;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ReferenceDTO {
    private Long referenceId;
    private String referenceType;
    private String value;
    private String alternateValue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ReferenceDTO parentReference;

    public ReferenceDTO() {
    }

    public ReferenceDTO(Reference reference) {
        this.referenceId = reference.getId();
        this.referenceType = reference.getReferenceType();
        this.value = reference.getValue();
        this.alternateValue = reference.getAlternateValue();

        if(reference.getParentReference() != null) {
            // non recursive
            Reference parentReference = reference.getParentReference();
            this.parentReference = new ReferenceDTO(
                parentReference.getId(),
                parentReference.getReferenceType(),
                parentReference.getValue(),
                parentReference.getAlternateValue()
            );
        }
    }

    public ReferenceDTO(Long referenceId, String referenceType, String value, String alternateValue) {
        this.referenceId = referenceId;
        this.referenceType = referenceType;
        this.value = value;
        this.alternateValue = alternateValue;
    }

    public ReferenceDTO(Long referenceId, String referenceType, String value, String alternateValue, ReferenceDTO parentReference) {
        this.referenceId = referenceId;
        this.referenceType = referenceType;
        this.value = value;
        this.alternateValue = alternateValue;
        this.parentReference = parentReference;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
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

    public ReferenceDTO getParentReference() {
        return parentReference;
    }

    public void setParentReference(ReferenceDTO parentReference) {
        this.parentReference = parentReference;
    }
    
}
