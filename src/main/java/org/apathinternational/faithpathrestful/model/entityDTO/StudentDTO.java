package org.apathinternational.faithpathrestful.model.entityDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

public class StudentDTO {
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentProfileDTO studentProfile;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentFlightInfoDTO studentFlightInfo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentTempHousingDTO studentTempHousing;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentCommentDTO studentComment;

    public StudentDTO() {
    }

    public StudentDTO(Long id, UserAccountDTO userAccount, StudentProfileDTO studentProfile, StudentFlightInfoDTO studentFlightInfo, StudentTempHousingDTO studentTempHousing, StudentCommentDTO studentComment) {
        this.id = id;
        this.studentProfile = studentProfile;
        this.studentFlightInfo = studentFlightInfo;
        this.studentTempHousing = studentTempHousing;
        this.studentComment = studentComment;
    }

    public Long getId() {
        return id;
    }

    public StudentProfileDTO getStudentProfile() {
        return studentProfile;
    }

    public StudentFlightInfoDTO getStudentFlightInfo() {
        return studentFlightInfo;
    }

    public StudentTempHousingDTO getStudentTempHousing() {
        return studentTempHousing;
    }

    public StudentCommentDTO getStudentComment() {
        return studentComment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentProfile(StudentProfileDTO studentProfile) {
        this.studentProfile = studentProfile;
    }

    public void setStudentFlightInfo(StudentFlightInfoDTO studentFlightInfo) {
        this.studentFlightInfo = studentFlightInfo;
    }

    public void setStudentTempHousing(StudentTempHousingDTO studentTempHousing) {
        this.studentTempHousing = studentTempHousing;
    }

    public void setStudentComment(StudentCommentDTO studentComment) {
        this.studentComment = studentComment;
    }
}
