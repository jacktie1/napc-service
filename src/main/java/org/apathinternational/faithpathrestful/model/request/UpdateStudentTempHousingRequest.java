package org.apathinternational.faithpathrestful.model.request;

import org.apathinternational.faithpathrestful.model.entityDTO.StudentTempHousingDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateStudentTempHousingRequest {


    @NotNull
    private StudentTempHousingDTO studentTempHousing;

    public UpdateStudentTempHousingRequest() {
    }

    public UpdateStudentTempHousingRequest(StudentTempHousingDTO studentTempHousing) {
        this.studentTempHousing = studentTempHousing;
    }

    public StudentTempHousingDTO getStudentTempHousing() {
        return studentTempHousing;
    }

    public void setStudentTempHousing(StudentTempHousingDTO studentTempHousing) {
        this.studentTempHousing = studentTempHousing;
    }


}
