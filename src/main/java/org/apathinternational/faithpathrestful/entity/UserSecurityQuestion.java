package org.apathinternational.faithpathrestful.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user_security_question")
public class UserSecurityQuestion {
    @Id
    @Column(name = "user_security_question_id", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "security_question_reference_id", nullable = false)
    private Reference securityQuestionReference;

    @Column(name = "security_answer", nullable = false)
    private String securityAnswer;

    public UserSecurityQuestion() {
    }

    public UserSecurityQuestion(Long id, User user, Reference securityQuestionReference, String securityAnswer) {
        this.id = id;
        this.user = user;
        this.securityQuestionReference = securityQuestionReference;
        this.securityAnswer = securityAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reference getSecurityQuestionReference() {
        return securityQuestionReference;
    }

    public void setSecurityQuestionReference(Reference securityQuestionReference) {
        this.securityQuestionReference = securityQuestionReference;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    
}
