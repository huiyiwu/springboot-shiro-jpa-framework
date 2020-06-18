package com.huchx.entity;

public class MUserEntity {
    private long id;
    private String userName;
    private String password;
    private String salt;

    public MUserEntity() {
    }

    public MUserEntity(long id, String userName, String password, String salt) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
    }

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
