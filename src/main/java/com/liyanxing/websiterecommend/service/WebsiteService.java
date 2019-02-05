package com.liyanxing.websiterecommend.service;

import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import com.liyanxing.util.PageBean;
import com.liyanxing.websiterecommend.pojo.Website;
import org.springframework.web.multipart.MultipartFile;

public interface WebsiteService
{
    /**
     * 存入一个网站
     * @param website
     */
    public void insertAwebsite(Website website, MultipartFile pic);

    /**
     * 查询网站表中某一页的数据
     * @param currPage 要查询第几页
     * @return
     */
    PageBean<Website> selectApageData(int currPage);

    /**
     * 根据id删除一个软件
     * @param id
     */
    void deleteById(int id);

    /**
     * 根据id查询一个网站
     * @param id
     * @return
     */
    Website selectById(int id);

    /**
     * 修改一个网站
     * @param website
     */
    void modifyWebsite(Website website);
}