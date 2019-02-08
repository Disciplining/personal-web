package com.liyanxing.users.commonmessage.controller;

import com.liyanxing.users.commonmessage.pojo.CommonMessage;
import com.liyanxing.users.commonmessage.service.CommonMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommonMessageController
{
    @Autowired
    @Qualifier("commonMessageServiceImpl")
    private CommonMessageService service;

    /**
     * 查询所有评论的所有信息
     *
     * @return
     */
    @GetMapping("/selectAllMessage")
    @ResponseBody
    public List<CommonMessage> selectAllMessage()
    {
        return service.selectAllMessage();
    }

    /**
     * 跳转到留言界面
     * @return
     */
    @GetMapping("/toMessagePage")
    public String toMessagePage()
    {
        return "message";
    }


    /**
     * 存入用户的留言
     * @param message 用户的留言内容
     * @return
     */
    @PostMapping("/saveOneUserMessage")
    public String saveOneUserMessage(String messageText)
    {
        service.saveOneUserMessage(messageText);
        return "index";
    }
}
