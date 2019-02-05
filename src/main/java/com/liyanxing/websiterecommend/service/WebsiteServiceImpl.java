package com.liyanxing.websiterecommend.service;

import com.liyanxing.util.PageBean;
import com.liyanxing.util.PageSize;
import com.liyanxing.util.SavePicture;
import com.liyanxing.websiterecommend.mapper.WebsiteMapper;
import com.liyanxing.websiterecommend.pojo.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("websiteServiceImpl")
public class WebsiteServiceImpl implements WebsiteService
{
    /**
     *存储用户上传图片的总目录
     */
    @Value("${cbs.imagesPath}")
    private  String mainPicturePath;

    /**
     * 软件推荐的图片应该存入的子目录
     */
    private String websiteRecommendDir = "websiteRecommend/";

    @Autowired
    @Qualifier("websiteMapper")
    private WebsiteMapper mapper;

    /*--------------------------------------------------------------------------------------------------*/

    /**
     * 修改一个网站
     *
     * @param id
     */
    @Override
    public void modifyWebsite(Website website)
    {
        mapper.modifyWebsite(website);
    }

    /**
     * 根据id查询一个网站
     *
     * @param id
     * @return
     */
    @Override
    public Website selectById(int id)
    {
        return mapper.selectAbyId(id);
    }

    /**
     * 存入一个网站
     *
     * @param website 图片对象  存入数据库
     * @param pic 图片数据 存入磁盘
     */
    @Override
    public void insertAwebsite(Website website, MultipartFile pic)
    {
        //存入磁盘
        mainPicturePath = mainPicturePath.substring(mainPicturePath.indexOf(':')+1); // 存储用户上传图片的总目录路径，最后有个“/”.
        String childDirPath = mainPicturePath + websiteRecommendDir; //软件推荐的图片应该存入的子目录路径,最后有个“/”.
        String picName = SavePicture.save(pic, childDirPath);

        //存入数据库
        website.setPic(websiteRecommendDir + picName);
        mapper.insertAwebsite(website);
    }

    /**
     * 查询网站表中某一页的数据
     *
     * @param currPage 要查询第几页
     * @return
     */
    @Override
    public PageBean<Website> selectApageData(int currPage)
    {
        //创建当前页的分页对象，计算四个参数
        int pageSize = PageSize.WEBSITE; //设置每一页显示的条数
        PageBean<Website> websitePageBean = new PageBean(currPage, pageSize, mapper.selectCount());

        /*-------------------向数据库中查询当前页的数据-------------------*/
        Map<String, Integer> parameter = new HashMap<>(2);
        parameter.put("begin", websitePageBean.getCurrPage() * websitePageBean.getPageSize() - websitePageBean.getPageSize());
        parameter.put("num", websitePageBean.getPageSize());
        List<Website> data = mapper.selectPage(parameter);
        /*-----------------------------------------------------------*/

        websitePageBean.setData(data);

        return websitePageBean;
    }

    /**
     * 根据id删除一个软件
     *
     * @param id
     */
    @Override
    public void deleteById(int id)
    {
        Website website = mapper.selectAbyId(id);
        mainPicturePath = mainPicturePath.substring(mainPicturePath.indexOf(':')+1); // 存储用户上传图片的总目录路径，最后有个“/”.
        File picture = new File(mainPicturePath + website.getPic());

        if (picture.exists())
        {
            picture.delete();
        }

        mapper.deleteAbyId(id);
    }
}