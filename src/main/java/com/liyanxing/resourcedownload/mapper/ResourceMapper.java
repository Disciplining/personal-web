package com.liyanxing.resourcedownload.mapper;

import com.liyanxing.resourcedownload.pojo.Resource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository("resourceMapper")
public interface ResourceMapper
{
    /**
     * 通过id查询一个资源
     * @param id
     * @return
     */
    @Select("select * from `resource` where id=#{id}")
    Resource selectAbyId(int id);

    /**
     * 插入一个资源
     * @param resource
     */
    @Insert("insert into `resource` (`pic`,`name`,`introduction`,`path`) values (#{pic},#{name},#{introduction},#{path})")
    void insertAresource(Resource resource);

    /**
     * 根据id删除一个资源
     * @param id
     */
    @Delete("delete from `resource` where id=#{id}")
    void deleteAbyId(int id);

    /**
     * 查询资源的个数
     * @return
     */
    @Select("select count(*) from `resource`")
    int selectCount();

    /**
     * 查询资源表中某部分的数据
     * @param map
     * @return
     */
    @Select("select * from `resource` limit #{begin},#{num}")
    List<Resource> selectPage(Map map);

    /**
     * 修改一个资源的信息
     * @param resource
     */
    @Update("update `resource` set `name`=#{name}, `introduction`=#{introduction}, `path`=#{path} where `id`=#{id}")
    void modifyResource(Resource resource);
}