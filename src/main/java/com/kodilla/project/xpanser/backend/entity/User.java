package com.kodilla.project.xpanser.backend.entity;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class User extends AbstractEntity {

    @NotEmpty
    private String username;

    @Email
    private String email;

    private String passwordHash;
    private String passwordSalt;

    private Role role;

    private static final int SALT_RAND = 32;

    public User() {
    }

    public User(String username, String email, String password, Role rol) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.passwordSalt = RandomStringUtils.random(SALT_RAND);
        this.passwordHash = DigestUtils.sha1Hex(password + passwordSalt);
    }

    public boolean comparePassword(String password) {
        return DigestUtils.sha1Hex(password + passwordSalt).equals(passwordHash);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
