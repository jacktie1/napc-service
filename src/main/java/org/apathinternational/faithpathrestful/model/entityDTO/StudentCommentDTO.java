package org.apathinternational.faithpathrestful.model.entityDTO;

public class StudentCommentDTO {
    private String studentComment;

    private String adminComment;

    public StudentCommentDTO() {
    }

    public StudentCommentDTO(String studentComment, String adminComment) {
        this.studentComment = studentComment;
        this.adminComment = adminComment;
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
