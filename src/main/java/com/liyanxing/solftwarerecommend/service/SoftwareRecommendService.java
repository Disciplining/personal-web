package com.liyanxing.solftwarerecommend.service;

import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import com.liyanxing.util.PageBean;
import org.springframework.web.multipart.MultipartFile;

public interface SoftwareRecommendService
{
    /**
     * 插入一个软件
     * @param software
     * @param pic 软片数据
     */
    void insertAsoftware(SoftwareRecommend software, MultipartFile pic);

    /**
     * 通过id查询一个软件
     * @param id
     * @return
     */
    SoftwareRecommend selectAbyId(int id);

    /**
     * 查询软件表中某一页的数据
     * @param currPage 要查询第几页
     * @return
     */
    PageBean<SoftwareRecommend> selectApageData(int currPage);

    /**
     * 根据id删除一个软件
     * @param id
     */
    void deleteAbyId(int id);

    /**
     * 修改一个软件的信息
     * @param software
     */
    void modifySoftware(SoftwareRecommend software);
}