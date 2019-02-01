package com.liyanxing.users.commonmessage.mapper;

import com.liyanxing.users.commonmessage.pojo.CommonMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("commonMessageMapper")
public interface CommonMessageMapper
{
    /**
     * 查询所有评论的所有信息
     * @return
     */
    @Select("select * from common_message")
    List<CommonMessage> selectAllMessage();
}