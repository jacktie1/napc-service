package org.apathinternational.faithpathrestful.model.request;

import org.apathinternational.faithpathrestful.model.entityDTO.StudentCommentDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.StudentFlightInfoDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.StudentProfileDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.StudentTempHousingDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.UserAccountDTO;

import jakarta.validation.constraints.NotNull;

public class RegisterStudentRequest {

    @NotNull
    private UserAccountDTO userAccount;

    @NotNull
    private StudentProfileDTO studentProfile;

    @NotNull
    private StudentFlightInfoDTO studentFlightInfo;

    @NotNull
    private StudentTempHousingDTO studentTempHousing;

    @NotNull
    private StudentCommentDTO studentComment;

    public RegisterStudentRequest() {
    }

    public RegisterStudentRequest(UserAccountDTO userAccount, StudentProfileDTO studentProfile, StudentFlightInfoDTO studentFlightInfo, StudentTempHousingDTO studentTempHousing, StudentCommentDTO studentComment) {
        this.userAccount = userAccount;
        this.studentProfile = studentProfile;
        this.studentFlightInfo = studentFlightInfo;
        this.studentTempHousing = studentTempHousing;
        this.studentComment = studentComment;
    }

    public UserAccountDTO getUserAccount() {
        return userAccount;
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

    public void setUserAccount(UserAccountDTO userAccount) {
        this.userAccount = userAccount;
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
