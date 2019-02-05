package com.liyanxing.websiterecommend.pojo;

public class Website
{
    private Integer id;
    private String pic;
    private String name;
    private String introduction;
    private String officialWeb;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getPic()
    {
        return pic;
    }

    public void setPic(String pic)
    {
        this.pic = pic;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIntroduction()
    {
        return introduction;
    }

    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    public String getOfficialWeb()
    {
        return officialWeb;
    }

    public void setOfficialWeb(String officialWeb)
    {
        this.officialWeb = officialWeb;
    }

    @Override
    public String toString()
    {
        return "Website{" +
                "id=" + id +
                ", pic='" + pic + '\'' +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", officialWeb='" + officialWeb + '\'' +
                '}';
    }
}