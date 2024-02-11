package org.apathinternational.faithpathrestful.model.request;

import org.apathinternational.faithpathrestful.model.entityDTO.StudentFlightInfoDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateStudentFlightInfoRequest {


    @NotNull
    private StudentFlightInfoDTO studentFlightInfo;

    public UpdateStudentFlightInfoRequest() {
    }

    public UpdateStudentFlightInfoRequest(StudentFlightInfoDTO studentFlightInfo) {
        this.studentFlightInfo = studentFlightInfo;
    }

    public StudentFlightInfoDTO getStudentFlightInfo() {
        return studentFlightInfo;
    }

    public void setStudentFlightInfo(StudentFlightInfoDTO studentFlightInfo) {
        this.studentFlightInfo = studentFlightInfo;
    }


}
