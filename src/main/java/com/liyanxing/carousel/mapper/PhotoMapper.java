package com.liyanxing.carousel.mapper;

import com.liyanxing.carousel.pojo.Photo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("photoMapper")
public interface PhotoMapper
{
    /**
     * 查询所的照片
     * @return
     */
    @Select("select * from `photo`")
    List<Photo> selectAllPhoto();

    /**
     * 通过id查询一张照片
     * @param id
     * @return
     */
    @Select("select * from `photo` where id=#{id}")
    Photo selectAbyId(int id);

    /**
     * 插入一张照片
     * @param photo
     */
    @Insert("insert into `photo` (`pic`,`title`,`description`) values (#{pic},#{title},#{description})")
    void insertAphoto(Photo photo);

    /**
     * 根据id删除一张照片
     * @param id
     */
    @Delete("delete from `photo` where id=#{id}")
    void deleteAbyId(int id);

    /**
     * 修改一张照片的信息
     * @param photo
     */
    @Update("update `photo` set `title`=#{title}, `description`=#{description} where `id`=#{id}")
    void modifyPhoto(Photo photo);
}