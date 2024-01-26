package org.apathinternational.faithpathrestful.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    @ColumnDefault("true") // "false" is the default value for "enabled"
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // Constructors, getters, and setters
    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setUsername(String username) {
    	this.username = username;
    }

    public void setPassword(String password) {
    	this.password = password;
    }

    public void setEnabled(Boolean enabled) {
    	this.enabled = enabled;
    }

    public void setRole(Role role) {
    	this.role = role;
    }

    // toString, hashCode, equals...
}