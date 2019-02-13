package com.liyanxing.carousel.service;

import com.liyanxing.carousel.mapper.PhotoMapper;
import com.liyanxing.carousel.pojo.Photo;
import com.liyanxing.config.DirectoryPath;
import com.liyanxing.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Transactional
@Service("photoServiceImpl")
public class PhotoServiceImpl implements PhotoService
{
    @Autowired
    @Qualifier("photoMapper")
    private PhotoMapper mapper;

    /**
     * 存储一张照片
     *
     * @param photo 对象数据，存入数据库
     * @param pic   图片数据，存入磁盘
     */
    @Override
    public void saveAphoto(Photo photo, MultipartFile pic)
    {
        String photoName = Util.save(pic, DirectoryPath.PHOTO_DIR); //存入磁盘

        //存入数据库
        photo.setPic(DirectoryPath.PHOTO_CHIL + photoName);
        mapper.insertAphoto(photo);
    }

    /**
     * 根据id删除一张照片
     *
     * @param id
     */
    @Override
    public void deleteById(int id)
    {
        Photo photo = mapper.selectAbyId(id);
        File photoPic = new File(DirectoryPath.MAIN_PICTURE_PICTURE + photo.getPic());

        if (photoPic.exists())
        {
            photoPic.delete();
        }

        mapper.deleteAbyId(id);
    }

    /**
     * 根据id查询一张照片
     *
     * @param id
     * @return
     */
    @Override
    public Photo selectById(int id)
    {
        return null;
    }

    /**
     * 修改一张照片的信息
     *
     * @param photo
     */
    @Override
    public void modifyPhoto(Photo photo)
    {

    }

    /**
     * 查找所有的照片
     *
     * @return
     */
    @Override
    public List<Photo> selectAllPhoto()
    {
        return mapper.selectAllPhoto();
    }
}