package com.liyanxing.websiterecommend.mapper;

import com.liyanxing.websiterecommend.pojo.Website;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository("websiteMapper")
public interface WebsiteMapper
{
    /**
     * 根据id查询一个网站
     * @param id
     * @return
     */
    @Select("select * from `website` where id=#{id}")
    Website selectAbyId(int id);

    /**
     * 查询所有推荐网站的所有信息
     * @return
     */
    @Select("select * from `website`")
    List<Website> selectAll();

    /**
     * 插入一个网站
     * @param website
     */
    @Insert("insert into `website` (`pic`,`name`,`introduction`,`official_web`) values (#{pic},#{name},#{introduction},#{officialWeb})")
    void insertAwebsite(Website website);

    /**
     * 查询网站表中某部分的数据
     * @param map
     * @return
     */
    @Select("select * from `website` limit #{begin},#{num}")
    List<Website> selectPage(Map map);

    /**
     * 查询网站的个数
     * @return
     */
    @Select("select count(*) from `website`")
    int selectCount();

    /**
     * 根据id删除一个网站
     * @param id
     */
    @Delete("delete from `website` where id=#{id}")
    void deleteAbyId(int id);

    /**
     * 修改一个网站的信息
     * @param website
     */
    @Update("update `website` set `name`=#{name}, `introduction`=#{introduction}, `official_web`=#{officialWeb} where `id`=#{id}")
    void modifyWebsite(Website website);
}