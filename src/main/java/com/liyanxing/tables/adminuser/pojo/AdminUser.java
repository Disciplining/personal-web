package com.liyanxing.tables.adminuser.pojo;

public class AdminUser
{
    private Integer adminUserId;

    private String name;

    private String password;

    public Integer getAdminUserId()
    {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId)
    {
        this.adminUserId = adminUserId;
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

    @Override
    public String toString()
    {
        return "AdminUser{" +
                "adminUserId=" + adminUserId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}