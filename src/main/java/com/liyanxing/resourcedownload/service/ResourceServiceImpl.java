package com.liyanxing.resourcedownload.service;

import com.liyanxing.config.DirectoryPath;
import com.liyanxing.resourcedownload.mapper.ResourceMapper;
import com.liyanxing.resourcedownload.pojo.Resource;
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

@Service("resourceServiceImpl")
public class ResourceServiceImpl implements ResourceService
{
    @Autowired
    @Qualifier("resourceMapper")
    private ResourceMapper mapper;

    /*----------------------------------------------------------------*/

    /**
     * 插入一个资源
     * @param resource 对象，存入数据库。
     * @param pic      图片数据，存入磁盘。
     * @param file     资源数据，存入磁盘。
     */
    @Override
    public void insertAresource(Resource resource, MultipartFile pic, MultipartFile file)
    {
        //图片存入磁盘
        String picName = Util.save(pic, DirectoryPath.RESOURCE_DOWNLOAD_DIR);

        //资源存入磁盘
        String fileName = Util.save(file, DirectoryPath.RESOURCE_DOWNLOAD_FILE_DIR);

        //存入数据库
        resource.setPic(DirectoryPath.RESOURCE_DOWNLOAD_CHIL + picName); //只将不包括总目录的部分存入数据库，这样，取出来返回给前端即可用，不需要再处理。
        resource.setPath(DirectoryPath.RESOURCE_DOWNLOAD_FILE_DIR + fileName);
        mapper.insertAresource(resource);
    }

    /**
     * 通过id查询一个资源
     *
     * @param id
     * @return
     */
    @Override
    public Resource selectAbyId(int id)
    {
        return mapper.selectAbyId(id);
    }

    /**
     * 查询资源表中某一页的数据
     *
     * @param currPage 要查询第几页
     * @return
     */
    @Override
    public PageBean<Resource> selectApageData(int currPage)
    {
        //创建当前页的分页对象，计算四个参数
        int pageSize = PageSize.RESOURCE_DONELOAD; //设置每一页显示的条数
        PageBean<Resource> resourcePageBean = new PageBean(currPage, pageSize, mapper.selectCount());

        Map<String, Integer> parameter = new HashMap<>(2);
        parameter.put("begin", resourcePageBean.getCurrPage() * resourcePageBean.getPageSize() - resourcePageBean.getPageSize());
        parameter.put("num", resourcePageBean.getPageSize());
        List<Resource> data = mapper.selectPage(parameter);

        resourcePageBean.setData(data);

        return resourcePageBean;
    }

    /**
     * 根据id删除一个资源
     *
     * @param id
     */
    @Override
    public void deleteAbyId(int id)
    {
        Resource resource = mapper.selectAbyId(id);

        File pic = new File(DirectoryPath.MAIN_PICTURE_PICTURE + resource.getPic());
        File file = new File("" + resource.getPath());

        if (pic.exists())
        {
            pic.delete();
        }
        if (file.exists())
        {
            file.delete();
        }

        mapper.deleteAbyId(id);
    }

    /**
     * 修改一个资源的信息
     *
     * @param resource
     */
    @Override
    public void modifyResource(Resource resource)
    {
        mapper.modifyResource(resource);
    }
}