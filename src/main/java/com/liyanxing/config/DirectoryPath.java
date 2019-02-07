package com.liyanxing.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DirectoryPath
{
    /**
     *存储用户上传图片的总目录
     */
    public static String MAIN_PICTURE_PICTURE;

    /**
     * 软件推荐的图片应该存入的子目录
     */
    public static final String SOFTWARE_RECOMMEND_CHIL = "softwareRecommend/";

    /**
     * 软件推荐图片应该存入的最终目录
     */
    public static String SOFTWARE_RECOMMEND_DIR;

    /**
     * 网站推荐的图片应该存入的子目录
     */
    public static final String WEBSITE_RECOMMEND_CHIL = "websiteRecommend/";

    /**
     * 网站推荐图片应该存入的最终目录
     */
    public static String WEBSITE_RECOMMEND_DIR;

    /**
     * 资源下载的图上 子目录
     */
    public static String RESOURCE_DOWNLOAD_CHIL = "resourceDownload/";

    /**
     * 资源下载的图片 最终目录
     */
    public static String RESOURCE_DOWNLOAD_DIR;

    /**
     * 资源下载的资源目录
     */
    public static final String RESOURCE_DOWNLOAD_FILE_DIR = "/media/root/MyDisk/material/事务/我的事务/项目-个人小网站/AllProject/upload/resourceDownload/";

    /*---------------------------------华丽的分割线---------------------------------*/

    /**
     * 设置目录
     * @param mainPicturePicture
     */
    @Value("${cbs.imagesPath}")
    public void setMainPicturePicture(String mainPicturePicture)
    {
        MAIN_PICTURE_PICTURE = mainPicturePicture;

        MAIN_PICTURE_PICTURE = MAIN_PICTURE_PICTURE.substring(MAIN_PICTURE_PICTURE.indexOf(':')+1); // 存储用户上传图片的总目录路径，最后有个“/”.

        SOFTWARE_RECOMMEND_DIR = MAIN_PICTURE_PICTURE + SOFTWARE_RECOMMEND_CHIL; //设置软件推荐图片应该存入的最终目录
        WEBSITE_RECOMMEND_DIR = MAIN_PICTURE_PICTURE + WEBSITE_RECOMMEND_CHIL; //设置网站推荐图片应该存入的最终目录
        RESOURCE_DOWNLOAD_DIR = MAIN_PICTURE_PICTURE + RESOURCE_DOWNLOAD_CHIL; //设置软件图片 的 最终目录
    }
}