package com.liyanxing.websiterecommend.controller;

import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import com.liyanxing.util.PageBean;
import com.liyanxing.websiterecommend.pojo.Website;
import com.liyanxing.websiterecommend.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("WebsiteRecommendCommonConmmonController")
public class ConmmonController
{
    @Autowired
    @Qualifier("websiteServiceImpl")
    private WebsiteService service;

    /**
     * 跳转到网站添加界面
     * @return
     */
    @GetMapping("/toAddWebsite")
    public String toAddWebsite()
    {
        return "websiteRecommend/addWebsite";
    }

    /**
     * 进入网站展示页面
     * @param currPage
     * @param model
     * @return
     */
    @GetMapping("/toShowWebsite")
    public String toShowWebsite(@RequestParam(name = "currPage") int currPage, Model model)
    {
        PageBean<Website> pageBean = service.selectApageData(currPage);

        /*-------------------要返回给前端的数据-------------------*/
        model.addAttribute("data", pageBean.getData()); //前端要展示的数据
        model.addAttribute("currPage", pageBean.getCurrPage()); //当前页
        model.addAttribute("totalPage", pageBean.getTotalPage()); //总页页数
        /*-----------------------------------------------------*/

        return "websiteRecommend/showWebsite";
    }

    /**
     * 跳转到网站修改页面
     * @return
     */
    @GetMapping("/toModAwebsite")
    public String toModAwebsite(@RequestParam(name = "id") int id, Model model)
    {
        Website website = service.selectById(id); //查询要修改的软件

        model.addAttribute("software", website);

        return "websiteRecommend/modWebsite";
    }

}