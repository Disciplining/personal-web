package com.liyanxing.users.commonmessage.service;

import com.liyanxing.users.commonmessage.pojo.CommonMessage;
import com.liyanxing.users.commonmessage.pojo.MessageEntity;

import java.util.List;

public interface CommonMessageService
{
    /**
     * 查询所有评论的所有信息
     * @return
     */
    List<CommonMessage> selectAllMessage();

    /**
     * 保存一个用户的留言
     * @param message
     */
    public void saveOneUserMessage(String messageText);

    /**
     * 获得用户的留言
     * @return
     */
    List<MessageEntity> showMessage();
}