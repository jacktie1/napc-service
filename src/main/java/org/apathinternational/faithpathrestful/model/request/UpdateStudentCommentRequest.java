package org.apathinternational.faithpathrestful.model.request;

import org.apathinternational.faithpathrestful.model.entityDTO.StudentCommentDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateStudentCommentRequest {


    @NotNull
    private StudentCommentDTO studentComment;

    public UpdateStudentCommentRequest() {
    }

    public UpdateStudentCommentRequest(StudentCommentDTO studentComment) {
        this.studentComment = studentComment;
    }

    public StudentCommentDTO getStudentComment() {
        return studentComment;
    }

    public void setStudentComment(StudentCommentDTO studentComment) {
        this.studentComment = studentComment;
    }


}
