package org.apathinternational.faithpathrestful.model.entityDTO;

import java.util.Date;

import org.apathinternational.faithpathrestful.entity.Student;
import org.apathinternational.faithpathrestful.entity.UserLogin;

import com.fasterxml.jackson.annotation.JsonInclude;

public class StudentDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long studentId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserAccountDTO userAccount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentProfileDTO studentProfile;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentFlightInfoDTO studentFlightInfo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentTempHousingDTO studentTempHousing;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentCommentDTO studentComment;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date modifiedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date lastLoginTime;

    // Lazy fields
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AirportPickupAssignmentDTO airportPickupAssignment;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TempHousingAssignmentDTO tempHousingAssignment;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        this.studentId = student.getId();
        this.userAccount = new UserAccountDTO(student.getUser());
        this.studentProfile = new StudentProfileDTO(student);
        this.studentFlightInfo = new StudentFlightInfoDTO(student);
        this.studentTempHousing = new StudentTempHousingDTO(student);
        this.studentComment = new StudentCommentDTO(student);

        Date studentModifiedAt = student.getModifiedAt();
        Date userModifiedAt = student.getUser().getModifiedAt();

        // Set the modifiedAt to the latest modifiedAt
        if (studentModifiedAt.after(userModifiedAt)) {
            this.modifiedAt = studentModifiedAt;
        } else {
            this.modifiedAt = userModifiedAt;
        }

        UserLogin userLogin = student.getUser().getUserLogin();

        if (userLogin != null) {
            this.lastLoginTime = userLogin.getLoginTime();
        }
    }

    public Long getStudentId() {
        return studentId;
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

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public AirportPickupAssignmentDTO getAirportPickupAssignment() {
        return airportPickupAssignment;
    }

    public TempHousingAssignmentDTO getTempHousingAssignment() {
        return tempHousingAssignment;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setAirportPickupAssignment(AirportPickupAssignmentDTO airportPickupAssignment) {
        this.airportPickupAssignment = airportPickupAssignment;
    }

    public void setTempHousingAssignment(TempHousingAssignmentDTO tempHousingAssignment) {
        this.tempHousingAssignment = tempHousingAssignment;
    }

    public void setUserAccountFromStudentEntity(Student student) {
        this.setUserAccount(new UserAccountDTO(student.getUser()));
    }

    public void setStudentProfileFromStudentEntity(Student student) {
        this.setStudentProfile(new StudentProfileDTO(student));
    }

    public void setStudentFlightInfoFromStudentEntity(Student student) {
        this.setStudentFlightInfo(new StudentFlightInfoDTO(student));
    }

    public void setStudentTempHousingFromStudentEntity(Student student) {
        this.setStudentTempHousing(new StudentTempHousingDTO(student));
    }

    public void setStudentCommentFromStudentEntity(Student student) {
        this.setStudentComment(new StudentCommentDTO(student));
    }

    public void setStudentIdFromStudentEntity(Student student) {
        this.setStudentId(student.getId());
    }

    public void setAirportPickupAssignmentFromStudentEntity(Student student) {
        if (student.getAirportPickupAssignment() != null) {
            this.setAirportPickupAssignment(new AirportPickupAssignmentDTO(student.getAirportPickupAssignment()));
        } else {
            AirportPickupAssignmentDTO emptyAirportPickupAssignment = new AirportPickupAssignmentDTO();
            this.setAirportPickupAssignment(emptyAirportPickupAssignment);
        }
    }

    public void setTempHousingAssignmentFromStudentEntity(Student student) {
        if (student.getTempHousingAssignment() != null) {
            this.setTempHousingAssignment(new TempHousingAssignmentDTO(student.getTempHousingAssignment()));
        } else {
            TempHousingAssignmentDTO emptyTempHousingAssignment = new TempHousingAssignmentDTO();
            this.setTempHousingAssignment(emptyTempHousingAssignment);
        }
    }

}
