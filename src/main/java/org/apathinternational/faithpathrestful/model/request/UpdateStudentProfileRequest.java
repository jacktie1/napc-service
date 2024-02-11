package org.apathinternational.faithpathrestful.model.request;

import org.apathinternational.faithpathrestful.model.entityDTO.StudentProfileDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateStudentProfileRequest {


    @NotNull
    private StudentProfileDTO studentProfile;

    public UpdateStudentProfileRequest() {
    }

    public UpdateStudentProfileRequest(StudentProfileDTO studentProfile) {
        this.studentProfile = studentProfile;
    }

    public StudentProfileDTO getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfileDTO studentProfile) {
        this.studentProfile = studentProfile;
    }


}
