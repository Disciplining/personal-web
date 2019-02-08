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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    /**
     * 资源下载
     *
     * @param id
     * @param response
     * @return
     */
    @Override
    public void resourceDownload(int id, HttpServletResponse response)
    {
        //构建文件
        Resource resource = mapper.selectAbyId(id);
        File file = new File("" + resource.getPath());


        if (file.exists())
        {
            /*--------------------------------设置用户下载的文件名--------------------------------*/
            StringBuffer nameBuffer = new StringBuffer(resource.getName()); //文件名
            String format = Util.getFormar(file.getName()); //获得扩展名（如果有的话）
            String fileName = nameBuffer.append(format).toString(); //最终名字带扩展名
            /*-------------------------*/

            try
            {
                response.setContentType("multipart/form-data;charset=UTF-8");
                response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
            }
            catch (UnsupportedEncodingException e)
            {
                //不支持编码;
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            }

            try
            {
                /*-------------------获得文件输入流-------------------*/
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                /*--------------------------------------------------*/

                OutputStream  servletOutputStream = response.getOutputStream(); //获得用户下载所用的输入流

                /*-------------------从输入流中读取数据，写入输入流中-------------------*/
                byte[] buffer = new byte[1024]; //用于存储数据的数组
                int i = bufferedInputStream.read(buffer); //从输入流中读取一个数组大小的数据，并存入数组
                while (i != -1)
                {
                    servletOutputStream.write(buffer); //数将这一数组的数据写到输出流中，准备读取下一组
                    i = bufferedInputStream.read(buffer); //读取下一组的数据
                }
                /*----------------------------------------------------------------*/

                /*-------------------关闭流对象-------------------*/
                bufferedInputStream.close();
                fileInputStream.close();
                /*----------------------------------------------*/
            }
            catch (Exception e)
            {
            }
        }
    }
}