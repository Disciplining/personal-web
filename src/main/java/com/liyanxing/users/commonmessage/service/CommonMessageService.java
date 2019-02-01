package com.liyanxing.users.commonmessage.service;

import com.liyanxing.users.commonmessage.pojo.CommonMessage;

import java.util.List;

public interface CommonMessageService
{
    /**
     * 查询所有评论的所有信息
     * @return
     */
    List<CommonMessage> selectAllMessage();
}