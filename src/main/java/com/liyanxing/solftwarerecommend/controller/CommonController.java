package com.liyanxing.solftwarerecommend.controller;

import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import com.liyanxing.solftwarerecommend.service.SoftwareRecommendService;
import com.liyanxing.util.SplitPage.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("solftwareRecommendCommonController")
public class CommonController
{
    @Autowired
    @Qualifier("softwareRecommendServiceImpl")
    private SoftwareRecommendService service;

    /*--------------------------------------------------------------*/

    /**
     * 转到软件添加页面
     * @return
     */
    @GetMapping("/toAddAsoftware")
    public String toAddAsoftware()
    {
        return "softwareRecommend/addAsoftware";
    }

    /**
     * 跳转到软件修改页面
     * @return
     */
    @GetMapping("/toModAsoftware")
    public String toModAsoftware(@RequestParam(name = "id") int id, Model model)
    {
        SoftwareRecommend software = service.selectAbyId(id); //查询要修改的软件

        model.addAttribute("software", software);

        return "softwareRecommend/modAsoftware";
    }

    /**
     * 进入软件展示页面
     * @param currPage
     * @param model
     * @return
     */
    @GetMapping("/toShowSoftware")
    public String toShowSoftware(@RequestParam(name = "currPage", defaultValue = "1") int currPage, Model model)
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