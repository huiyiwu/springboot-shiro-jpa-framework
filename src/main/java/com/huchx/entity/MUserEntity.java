package com.huchx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "m_user")
public class MUserEntity implements Serializable {
    private long id;
    private String name;
    private String password;
    private String salt;
    private String token;

    public MUserEntity() {
    }

    public MUserEntity(long id, String name, String password, String salt,String token) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
