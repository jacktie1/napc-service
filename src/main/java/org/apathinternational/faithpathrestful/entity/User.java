package org.apathinternational.faithpathrestful.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user")
public class User extends AuditableEntity {

    @Id
    @Column(name = "user_id", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_address", unique = true, nullable = false)
    private String emailAddress;

    @Column(name = "gender")
    private String gender;

    @Column(name = "enabled", nullable = false)
    @ColumnDefault("true") // "false" is the default value for "enabled"
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<UserSecurityQuestion> securityQuestions;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Student student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Volunteer volunteer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Administrator administrator;

    // Constructors, getters, and setters
    public User() {
    }

    public Long getId () {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getGender() {
        return gender;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Role getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public List<UserSecurityQuestion> getSecurityQuestions() {
        return securityQuestions;
    }

    public Student getStudent() {
        return student;
    }
    
    public Volunteer getVolunteer() {
        return volunteer;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setUsername(String username) {
    	this.username = username;
    }

    public void setPassword(String password) {
    	this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
    	this.emailAddress = emailAddress;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEnabled(Boolean enabled) {
    	this.enabled = enabled;
    }

    public void setRole(Role role) {
    	this.role = role;
    }

    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }

    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }

    public void setSecurityQuestions(List<UserSecurityQuestion> securityQuestions) {
        this.securityQuestions = securityQuestions;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    // Custom methods
    public boolean isStudent() {
        return role.getName().equals("Student");
    }

    public boolean isVolunteer() {
        return role.getName().equals("Volunteer");
    }

    public boolean isAdmin() {
        return role.getName().equals("Admin");
    }

    // toString, hashCode, equals...
}