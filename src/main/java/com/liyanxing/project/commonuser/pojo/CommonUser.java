package com.liyanxing.project.commonuser.pojo;

public class CommonUser
{
    private Integer commonUserId;

    private String name;

    private String password;

    public Integer getCommonUserId()
    {
        return commonUserId;
    }

    public void setCommonUserId(Integer commonUserId)
    {
        this.commonUserId = commonUserId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password == null ? null : password.trim();
    }
}