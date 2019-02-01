package com.liyanxing.tables.commonmessage.controller;

import com.liyanxing.tables.commonmessage.pojo.CommonMessage;
import com.liyanxing.tables.commonmessage.service.CommonMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
