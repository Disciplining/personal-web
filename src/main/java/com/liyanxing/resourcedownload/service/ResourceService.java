package com.liyanxing.resourcedownload.service;

import com.liyanxing.resourcedownload.pojo.Resource;
import com.liyanxing.util.SplitPage.PageBean;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface ResourceService
{
    /**
     * 插入一个资源
     * @param resource
     * @param pic 图片数据
     */
    void insertAresource(Resource resource, MultipartFile pic, MultipartFile file);

    /**
     * 通过id查询一个资源
     * @param id
     * @return
     */
    Resource selectAbyId(int id);

    /**
     * 查询资源表中某一页的数据
     * @param currPage 要查询第几页
     * @return
     */
    PageBean<Resource> selectApageData(int currPage);

    /**
     * 根据id删除一个资源
     * @param id
     */
    void deleteAbyId(int id);

    /**
     * 修改一个资源的信息
     * @param software
     */
    void modifyResource(Resource resource);

    /**
     * 资源下载
     * @param id
     * @param response
     * @return
     */
    public void resourceDownload(int id, HttpServletResponse response);
}