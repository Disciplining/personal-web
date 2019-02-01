package com.liyanxing.solftwarerecommend.mapper;

import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("softwareRecommendMapper")
public interface SoftwareRecommendMapper
{
    /**
     * 插入一个软件
     * @param software
     */
    @Insert("insert into software_recommend (`pic`,`name`,`introduction`,`official_web`) values (#{pic},#{name},#{introduction},#{officialWeb})")
    void insertAsoftware(SoftwareRecommend software);
}