package com.liyanxing.resourcedownload.pojo;

public class Resource
{
    private Integer id;
    private String pic;
    private String name;
    private String introduction;
    private String path;

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

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    @Override
    public String toString()
    {
        return "Resource{" +
                "id=" + id +
                ", pic='" + pic + '\'' +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}