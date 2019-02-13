package com.liyanxing.carousel.controller;

import com.liyanxing.carousel.pojo.Photo;
import com.liyanxing.carousel.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 照片的 添加、删除、修改
 */
@Controller("photoAddDelMod")
public class PhotoAddDelMod
{
    @Autowired
    @Qualifier("photoServiceImpl")
    private PhotoService service;

    /**
     * 添加一张照片
     * @param pic
     * @param title
     * @param description
     * @return
     */
    @PostMapping("/addAphoto")
    public String addAphoto(MultipartFile pic, String title, String description)
    {
        Photo photo = new Photo();
        photo.setTitle(title);
        photo.setDescription(description);
        service.saveAphoto(photo,pic);

        return "redirect:/toShowPhotoPage";
    }

    /**
     * 根据id删除一张照片
     * @param id
     * @return
     */
    @GetMapping("/deleteAphoto")
    public String deleteAphoto(@RequestParam(name = "id") int id)
    {
        service.deleteById(id);
        System.out.println("照片：" + id);

        return "redirect:/toShowPhotoPage";
    }
}