package com.liyanxing.resourcedownload.controller;

import com.liyanxing.resourcedownload.pojo.Resource;
import com.liyanxing.resourcedownload.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 资源的 添加、删除、修改
 */
@Controller("resourceAddDelMod")
public class ResourceAddDelMod
{
    @Autowired
    @Qualifier("resourceServiceImpl")
    private ResourceService service;

    /*----------------------------------------------------------------------------*/

    /**
     * 添加一个资源
     * 接收表单数据，处理后存入数据库
     * @param pic
     * @param name
     * @param introduction
     * @param path
     * @return
     */
    @PostMapping("/addAresource")
    @ResponseBody
    public String addAsoftware(MultipartFile pic, String name, String introduction, MultipartFile file)
    {
        Resource resource = new Resource();
        resource.setName(name);
        resource.setIntroduction(introduction);

        service.insertAresource(resource, pic, file);

        return "ok";
    }
}