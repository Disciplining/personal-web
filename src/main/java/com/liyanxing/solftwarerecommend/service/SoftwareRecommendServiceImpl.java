package com.liyanxing.solftwarerecommend.service;

import com.liyanxing.solftwarerecommend.mapper.SoftwareRecommendMapper;
import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import com.liyanxing.util.PageBean;
import com.liyanxing.util.PageSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        mapper.deleteAbyId(id);
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