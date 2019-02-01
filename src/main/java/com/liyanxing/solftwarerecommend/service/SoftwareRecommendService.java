package com.liyanxing.solftwarerecommend.service;

import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;

public interface SoftwareRecommendService
{
    /**
     * 插入一个软件
     * @param software
     */
    void insertAsoftware(SoftwareRecommend software);
}