package com.liyanxing.solftwarerecommend.controller;

import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import com.liyanxing.solftwarerecommend.service.SoftwareRecommendService;
import com.liyanxing.solftwarerecommend.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("solftwareRecommendCommonController")
public class CommonController
{
    @Autowired
    @Qualifier("softwareRecommendServiceImpl")
    private SoftwareRecommendService service;

    @GetMapping("/toAddAsoftware")
    public String toAddAsoftware()
    {
        return "softwareRecommend/addAsoftware";
    }

    @GetMapping("/toShowSoftware")
    public String toShowSoftware(@RequestParam(name = "currPage") int currPage, Model model)
    {
        PageBean<SoftwareRecommend> pageBean = service.selectApageData(currPage);

        /*-------------------要返回给前端的数据-------------------*/
        model.addAttribute("data", pageBean.getData()); //前端要展示的数据
        model.addAttribute("currPage", pageBean.getCurrPage()); //当前页
        model.addAttribute("totalPage", pageBean.getTotalPage()); //总页页数
        /*-----------------------------------------------------*/

        return "softwareRecommend/showSoftware";
    }
}