package com.liyanxing.solftwarerecommend.mapper;

import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 通过id查询一个软件
     * @param id
     * @return
     */
    @Select("select * from software_recommend where id=#{id}")
    SoftwareRecommend selectAbyId(int id);

    /**
     * 查询软件的个数
     * @return
     */
    @Select("select count(*) from software_recommend")
    int selectCount();

    /**
     * 查询软件表中某部分的数据
     * @param map
     * @return
     */
    @Select("select * from `software_recommend` limit #{begin},#{num}")
    List<SoftwareRecommend> selectPage(Map map);
}