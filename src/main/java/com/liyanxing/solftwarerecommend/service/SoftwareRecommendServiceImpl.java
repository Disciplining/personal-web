package com.liyanxing.solftwarerecommend.service;

import com.liyanxing.solftwarerecommend.mapper.SoftwareRecommendMapper;
import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("softwareRecommendServiceImpl")
public class SoftwareRecommendServiceImpl implements SoftwareRecommendService
{
    @Autowired
    @Qualifier("softwareRecommendMapper")
    private SoftwareRecommendMapper mapper;

    /**
     * 插入一个软件
     * @param software
     */
    @Override
    public void insertAsoftware(SoftwareRecommend software)
    {
        mapper.insertAsoftware(software);
    }
}