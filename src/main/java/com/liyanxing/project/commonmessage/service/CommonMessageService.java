package com.liyanxing.project.commonmessage.service;

import com.liyanxing.project.commonmessage.pojo.CommonMessage;

import java.util.List;

public interface CommonMessageService
{
    /**
     * 查询所有评论的所有信息
     * @return
     */
    List<CommonMessage> selectAllMessage();
}