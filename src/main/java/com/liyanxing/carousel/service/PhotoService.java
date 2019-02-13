package com.liyanxing.carousel.service;

import com.liyanxing.carousel.pojo.Photo;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService
{
    /**
     * 存储一张照片
     * @param photo 对象数据，存入数据库
     * @param pic 图片数据，存入磁盘
     */
    public void saveAphoto(Photo photo, MultipartFile pic);

    /**
     * 根据id删除一张照片
     * @param id
     */
    void deleteById(int id);

    /**
     * 根据id查询一张照片
     * @param id
     * @return
     */
    Photo selectById(int id);

    /**
     * 修改一张照片的信息
     * @param photo
     */
    void modifyPhoto(Photo photo);
}