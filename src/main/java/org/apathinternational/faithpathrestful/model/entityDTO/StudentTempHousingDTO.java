package org.apathinternational.faithpathrestful.model.entityDTO;

import jakarta.validation.constraints.NotNull;

public class StudentTempHousingDTO {
    @NotNull
    private Boolean needsTempHousing;

    private Integer numNights;

    private Long apartmentReferenceId;

    private String customDestinationAddress;

    private String contactName;

    private String contactPhoneNumber;

    private String contactEmailAddress;

    public StudentTempHousingDTO() {
    }

    public StudentTempHousingDTO(Boolean needsTempHousing, Integer numNights, Long apartmentReferenceId, String customDestinationAddress, String contactName, String contactPhoneNumber, String contactEmailAddress) {
        this.needsTempHousing = needsTempHousing;
        this.numNights = numNights;
        this.apartmentReferenceId = apartmentReferenceId;
        this.customDestinationAddress = customDestinationAddress;
        this.contactName = contactName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactEmailAddress = contactEmailAddress;
    }

    public Boolean getNeedsTempHousing() {
        return needsTempHousing;
    }

    public Integer getNumNights() {
        return numNights;
    }

    public Long getApartmentReferenceId() {
        return apartmentReferenceId;
    }

    public String getCustomDestinationAddress() {
        return customDestinationAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public void setNeedsTempHousing(Boolean needsTempHousing) {
        this.needsTempHousing = needsTempHousing;
    }

    public void setNumNights(Integer numNights) {
        this.numNights = numNights;
    }

    public void setApartmentReferenceId(Long apartmentReferenceId) {
        this.apartmentReferenceId = apartmentReferenceId;
    }

    public void setCustomDestinationAddress(String customDestinationAddress) {
        this.customDestinationAddress = customDestinationAddress;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }
}
