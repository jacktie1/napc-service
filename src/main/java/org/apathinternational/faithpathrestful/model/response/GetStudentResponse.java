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

        this.student.setUserAccount(student);
    }

    public void setStudentProfile(Student student) {
        if(this.student == null)
        {
            this.student = new StudentDTO();
        }

        this.student.setStudentProfile(student);
    }

    public void setStudentFlightInfo(Student student) {
        if(this.student == null)
        {
            this.student = new StudentDTO();
        }

        this.student.setStudentFlightInfo(student);
    }

    public void setStudentTempHousing(Student student) {
        if(this.student == null)
        {
            this.student = new StudentDTO();
        }

        this.student.setStudentTempHousing(student);
    }

    public void setStudentComment(Student student) {
        if(this.student == null)
        {
            this.student = new StudentDTO();
        }

        this.student.setStudentComment(student);
    }
    
}
