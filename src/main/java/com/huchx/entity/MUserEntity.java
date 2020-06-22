package com.huchx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_user")
public class MUserEntity {
    private long id;
    @Column(name = "name")
    private String userName;
    private String password;
    private String salt;
    private String token;

    public MUserEntity() {
    }

    public MUserEntity(long id, String userName, String password, String salt,String token) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
