package org.apathinternational.faithpathrestful.model.response;

import org.apathinternational.faithpathrestful.entity.Student;
import org.apathinternational.faithpathrestful.model.entityDTO.StudentDTO;


public class GetStudentResponse {
    private StudentDTO student;

    public GetStudentResponse() {
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public void setUserAccount(Student student) {
        if(this.student == null)
        {
            this.student = new StudentDTO();
        }

        this.student.setUserAccountFromStudentEntity(student);
    }

    public void setStudentProfile(Student student) {
        if(this.student == null)
        {
            this.student = new StudentDTO();
        }

        this.student.setStudentProfileFromStudentEntity(student);
    }

    public void setStudentFlightInfo(Student student) {
        if(this.student == null)
        {
            this.student = new StudentDTO();
        }

        this.student.setStudentFlightInfoFromStudentEntity(student);
    }

    public void setStudentTempHousing(Student student) {
        if(this.student == null)
        {
            this.student = new StudentDTO();
        }

        this.student.setStudentTempHousingFromStudentEntity(student);
    }

    public void setStudentComment(Student student) {
        if(this.student == null)
        {
            this.student = new StudentDTO();
        }

        this.student.setStudentCommentFromStudentEntity(student);
    }
    
}
