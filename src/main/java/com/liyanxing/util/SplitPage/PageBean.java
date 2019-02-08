package com.liyanxing.util.SplitPage;

import java.util.List;

public class PageBean<T>
{
    private int currPage; //当前页数
    private int pageSize; //每页显示记录数
    private int totalCount; //总记录数
    private int totalPage; //总页数

    private List<T> data;

    /**
     * 构造函数
     * 四个参数中这三个需要手动注入，总页数是计算出来的
     * 只提供 getter 方法， 不提供 setter 方法 构造函数行使了 setter 方法的功能
     * @param currPagePara
     * @param pageSizePara
     * @param totalCountPara
     */
    public PageBean(int currPagePara, int pageSizePara, int totalCountPara)
    {
        totalCount = totalCountPara;

        if (totalCount == 0) //没有数据
        {
            currPage = 1;
            pageSize = pageSizePara;
            totalPage = 0;
        }
        else
        {
            currPage = currPagePara;
            pageSize = pageSizePara;

            if (totalCount % pageSize == 0)
            {
                totalPage = totalCount / pageSize;
            }
            else
            {
                totalPage = totalCount / pageSize + 1;
            }

            if (currPage <= 0)
            {
                currPage = 1;
            }
            if (currPage >= totalPage + 1)
            {
                currPage = totalPage;
            }
        }
    }

    public int getCurrPage()
    {
        return currPage;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public List<T> getData()
    {
        return data;
    }

    public void setData(List<T> data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "PageBean{" +
                "currPage=" + currPage +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", data=" + data +
                '}';
    }
}