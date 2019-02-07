package com.liyanxing.solftwarerecommend.service;


import com.liyanxing.solftwarerecommend.mapper.SoftwareRecommendMapper;
import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import com.liyanxing.config.DirectoryPath;
import com.liyanxing.util.SplitPage.PageBean;
import com.liyanxing.util.SplitPage.PageSize;
import com.liyanxing.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("softwareRecommendServiceImpl")
public class SoftwareRecommendServiceImpl implements SoftwareRecommendService
{
    @Autowired
    @Qualifier("softwareRecommendMapper")
    private SoftwareRecommendMapper mapper;

    /*----------------------------------------------------------------*/

    /**
     * 插入一个软件
     * @param software 对象，存入数据库
     * @param pic 图片数据，存入磁盘
     */
    @Override
    public void insertAsoftware(SoftwareRecommend software, MultipartFile pic)
    {
        //存入磁盘
        String picName = Util.save(pic, DirectoryPath.SOFTWARE_RECOMMEND_DIR);

        //存入数据库
        software.setPic(DirectoryPath.SOFTWARE_RECOMMEND_CHIL + picName); //只将不包括总目录的部分存入数据库，这样，取出来返回给前端即可用，不需要再处理。
        mapper.insertAsoftware(software);
    }

    /**
     * 通过id查询一个软件
     *
     * @param id
     * @return
     */
    @Override
    public SoftwareRecommend selectAbyId(int id)
    {
        return mapper.selectAbyId(id);
    }

    /**
     * 查询软件表中某一页的数据
     *
     * @param currPage 要查询第几页
     * @return
     */
    @Override
    public PageBean<SoftwareRecommend> selectApageData(int currPage)
    {
        //创建当前页的分页对象，计算四个参数
        int pageSize = PageSize.SOFTWARE_RECOMMEND; //设置每一页显示的条数
        PageBean<SoftwareRecommend> softwarePageBean = new PageBean(currPage, pageSize, mapper.selectCount());

        /*-------------------向数据库中查询当前页的数据-------------------*/
        Map<String, Integer> parameter = new HashMap<>(2);
        parameter.put("begin", softwarePageBean.getCurrPage() * softwarePageBean.getPageSize() - softwarePageBean.getPageSize());
        parameter.put("num", softwarePageBean.getPageSize());
        List<SoftwareRecommend> data = mapper.selectPage(parameter);
        /*-----------------------------------------------------------*/

        softwarePageBean.setData(data);

        return softwarePageBean;
    }

    /**
     * 根据id删除一个软件
     *
     * @param id
     */
    @Override
    public void deleteAbyId(int id)
    {
        //获得图片文件
        SoftwareRecommend software = mapper.selectAbyId(id);
        File picture = new File(DirectoryPath.MAIN_PICTURE_PICTURE + software.getPic());

        if (picture.exists())
        {
            picture.delete(); //从磁盘中删除图片文件
        }

        mapper.deleteAbyId(id); //从数据库中删除
    }

    /**
     * 修改一个软件的信息
     *
     * @param software
     */
    @Override
    public void modifySoftware(SoftwareRecommend software)
    {
        mapper.modifySoftware(software);
    }
}