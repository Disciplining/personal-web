package com.liyanxing.users.commonmessage.service;

import com.liyanxing.users.commonmessage.mapper.CommonMessageMapper;
import com.liyanxing.users.commonmessage.pojo.CommonMessage;
import com.liyanxing.users.commonmessage.pojo.MessageEntity;
import com.liyanxing.users.commonuser.pojo.CommonUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;


@Service("commonMessageServiceImpl")
public class CommonMessageServiceImpl implements CommonMessageService
{
    @Autowired
    @Qualifier("commonMessageMapper")
    private CommonMessageMapper mapper;

    /**
     * 查询所有评论的所有信息
     *
     * @return
     */
    @Override
    public List<CommonMessage> selectAllMessage()
    {
        return mapper.selectAllMessage();
    }

    /**
     * 保存一个用户的留言
     *
     * @param message
     */
    @Override
    public void saveOneUserMessage(String messageText)
    {
        CommonUser user = (CommonUser) SecurityUtils.getSubject().getPrincipal();

        CommonMessage message = new CommonMessage();
        message.setMessage(messageText);
        message.setCommonUserId(user.getCommonUserId());
        message.setTime(new Date(System.currentTimeMillis()));

        mapper.insertOneMessage(message);
    }

    /**
     * 获得用户的留言
     *
     * @return
     */
    @Override
    public List<MessageEntity> showMessage()
    {
        return mapper.showMessage();
    }
}