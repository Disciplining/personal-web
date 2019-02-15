package com.liyanxing.resourcedownload.controller;

import com.liyanxing.resourcedownload.pojo.Resource;
import com.liyanxing.resourcedownload.service.ResourceService;
import com.liyanxing.util.SplitPage.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller("resourceDownloadCommonController")
public class CommonController
{
    @Autowired
    @Qualifier("resourceServiceImpl")
    private ResourceService service;

    /**
     * 跳转到资源添加页面
     * @return
     */
    @GetMapping("/toAddResourcePage")
    public String toAddResourcePage()
    {
        return "resourceDownload/addAresource";
    }

    /**
     * 进入资源展示页面
     * @param currPage
     * @param model
     * @return
     */
    @GetMapping("/toShowResourcePage")
    public String toShowResourcePage(@RequestParam(name = "currPage", defaultValue = "1") int currPage, Model model)
    {
        PageBean<Resource> pageBean = service.selectApageData(currPage);

        /*-------------------要返回给前端的数据-------------------*/
        model.addAttribute("data", pageBean.getData()); //前端要展示的数据
        model.addAttribute("currPage", pageBean.getCurrPage()); //当前页
        model.addAttribute("totalPage", pageBean.getTotalPage()); //总页页数
        /*-----------------------------------------------------*/

        return "resourceDownload/showResource";
    }

    /**
     * 跳转到资源修改页面
     * @return
     */
    @GetMapping("/toModResourcePage")
    public String toModResourcePage(@RequestParam(name = "id") int id, Model model)
    {
        Resource resource = service.selectAbyId(id); //查询要修改的软件

        model.addAttribute("software", resource);

        return "resourceDownload/modAresource";
    }

    /**
     * 资源下载
     * @param id
     * @param response
     */
    @GetMapping("/download")
    public void download(@RequestParam(name = "id") int id, HttpServletResponse response)
    {
        service.resourceDownload(id, response);
    }
}