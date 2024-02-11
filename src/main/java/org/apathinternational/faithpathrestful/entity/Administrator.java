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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "administrator")
public class Administrator extends AuditableEntity{
    @Id
    @Column(name = "administrator_id", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "primary_phone_number", nullable = false)
    private String primaryPhoneNumber;

    @Column(name = "affiliation", nullable = false)
    private String affiliation;

    public Administrator() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }
}
