package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.Reference;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ReferenceDTO {
    private Long id;
    private String referenceType;
    private String value;
    private String alternateValue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ReferenceDTO parentReference;

    public ReferenceDTO() {
    }

    public ReferenceDTO(Reference reference) {
        this.id = reference.getId();
        this.referenceType = reference.getReferenceType();
        this.value = reference.getValue();
        this.alternateValue = reference.getAlternateValue();

        if(reference.getParentReference() != null) {
            // non recursive
            Reference paReference = reference.getParentReference();
            this.parentReference = new ReferenceDTO(
                paReference.getId(),
                paReference.getReferenceType(),
                paReference.getValue(),
                paReference.getAlternateValue()
            );
        }
    }

    public ReferenceDTO(Long id, String referenceType, String value, String alternateValue) {
        this.id = id;
        this.referenceType = referenceType;
        this.value = value;
        this.alternateValue = alternateValue;
    }

    public ReferenceDTO(Long id, String referenceType, String value, String alternateValue, ReferenceDTO parentReference) {
        this.id = id;
        this.referenceType = referenceType;
        this.value = value;
        this.alternateValue = alternateValue;
        this.parentReference = parentReference;
    }

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

    public ReferenceDTO getParentReference() {
        return parentReference;
    }

    public void setParentReference(ReferenceDTO parentReference) {
        this.parentReference = parentReference;
    }
    
}
