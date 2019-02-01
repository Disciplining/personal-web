package com.liyanxing.users.commonuser.pojo;

import java.sql.Date;

public class CommonUser
{
    private Integer commonUserId;

    private String name;

    private String password;

    private String salt;

    private Integer sex;

    private Date birthday;

    private String email;

    public Integer getCommonUserId() {
        return commonUserId;
    }

    public void setCommonUserId(Integer commonUserId) {
        this.commonUserId = commonUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    @Override
    public String toString()
    {
        return "CommonUser{" + "commonUserId=" + commonUserId + ", name='" + name + '\'' + ", password='" + password + '\'' + ", salt='" + salt + '\'' + ", sex=" + sex + ", birthday=" + birthday + ", email='" + email + '\'' + '}';
    }
}