package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.Student;

public class StudentCommentDTO {
    private String studentComment;

    private String adminComment;

    public StudentCommentDTO() {
    }

    public StudentCommentDTO(Student student) {
        this.studentComment = student.getStudentComment();
        this.adminComment = student.getAdminComment();
    }

    public String getStudentComment() {
        return studentComment;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }
}
